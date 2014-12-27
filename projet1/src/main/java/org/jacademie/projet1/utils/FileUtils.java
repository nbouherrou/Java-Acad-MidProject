package org.jacademie.projet1.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;

public class FileUtils {

	private static Logger logger = LogManager.getLogger(FileUtils.class);

	public ArrayList<Path> findFileInRepoWithGoodExtension(String path,
			String extension) {

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

	public List<String> getAllLine(Path path) {

		 List<String>  list = new ArrayList<String>() ;
		try {
			list = Files.readAllLines(path,Charset.defaultCharset());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	



}