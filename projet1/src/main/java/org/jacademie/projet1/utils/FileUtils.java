package org.jacademie.projet1.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtils {

	private static Logger logger = LogManager.getLogger(FileUtils.class);
	
	

	public ArrayList<Path> findFileInRepoWithGoodExtension(String path,String extension) {
		
		logger.info("In findFileInRepoWithGoodExtension ....");
		ArrayList<Path> pathList = new ArrayList<Path>();

		try {
			Files.walk(Paths.get(path)).forEach(
					filePath -> {
						
						if (Files.isRegularFile(filePath)) {
							
							String ext = FilenameUtils.getExtension(filePath
									.toString());
							if (ext.equals(extension)) {

								pathList.add(filePath);

								logger.info("File found : "
										+ filePath.toString());
							}
						}
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pathList.isEmpty()) {
			logger.info("No file found with extension" + extension);
		}
		
		logger.info("Bye findFileInRepoWithGoodExtension \n");
		return pathList;
	}
	

}
