package cz.zcu.print.demoWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import cz.zcu.print.demoWeb.service.FileService;

@Controller
@SessionScope
public class FileListController {
	
	@Autowired
	FileService fileService;
	
	@GetMapping("/files")
	public String files(Model model) {

		model.addAttribute("files", fileService.getFiles());
		
		return "files";
	}

}