package cz.zcu.print.demoWeb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cz.zcu.print.demoWeb.service.FileService;

@RestController
public class FileRestController {

	@Autowired
	FileService fileService;

	@GetMapping("/filesToPrint")
	List<FileToPrint> all() {
		int index = 0;
		List<FileToPrint> files = new ArrayList<FileToPrint>();

		for (String file : fileService.getFiles()) {
			files.add(new FileToPrint(index++, file));
		}
		return files;
	}

	@GetMapping("/filesToPrint/{id}")
	byte[] content(@PathVariable Long id) {
		List<String> files = fileService.getFiles();
		String fileName = files.get(id.intValue());

		File file = new File(fileName);
		byte[] bytes = new byte[(int) file.length()];

		try {
			bytes = Files.readAllBytes(Paths.get(fileName));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return bytes;
	}
}
