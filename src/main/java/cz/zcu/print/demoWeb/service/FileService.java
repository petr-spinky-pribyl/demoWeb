package cz.zcu.print.demoWeb.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class FileService {

	@Value("${root.folder}")
	private String rootDir;
	
	private List<String> selectedFiles = new ArrayList<String>();

	public List<String> getSelectedFiles() {
		return selectedFiles;
	}
	
	public List<String> getFiles() {
		File rootDirectory = new File(rootDir);
		
		List<String> files = Arrays.stream(rootDirectory.listFiles())
		        .map(file -> file.getAbsolutePath())
		        .collect(Collectors.toList());
		return files;
	}
}
