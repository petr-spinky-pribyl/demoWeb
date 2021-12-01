package cz.zcu.print.demoWeb.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

	public void savePartState(Long id, FileState state) {
		File rootDirectory = new File(rootDir);
		File idFile = new File(rootDirectory, Long.toString(id));
		try {
			idFile.createNewFile();
			
			FileWriter fileWriter = new FileWriter(idFile, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			printWriter.printf("%s: %s - %s\n", formatter.format(new Date()), state.getPart(), state.getState());
			
			printWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
