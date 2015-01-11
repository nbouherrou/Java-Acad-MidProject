package org.jacademie.projet1.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;


public class FileUtils {

	private static Logger logger = LogManager.getLogger(FileUtils.class);

	public ArrayList<Path> getFilesInDirectoryWithExtension(String path,
			String extension) {

		logger.info("Begin getFilesInDirectoryWithExtension ....");

		logger.info("Given Directory SOURCE_PATH " + path);

		logger.info("Given Filtering EXTENSION " + extension);

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

			e.printStackTrace();

		}

		if (pathList.isEmpty()) {

			logger.info("No file found with extension" + extension);
		}

		logger.info("End getFilesInDirectoryWithExtension");

		return pathList;
	}


	/**
	 * @param pathList
	 * @return
	 */
	public ArrayList<Path> filterGoodFiles(ArrayList<Path> pathList) {

		logger.info("In filterGoodFiles function ......");

		ArrayList<Path> paths = pathList;

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(Constants.FILE_HEADER_MAPPING);
		
		

		pathList.stream().forEach( path -> {

			logger.info("Path " + path);

			Integer total_lignes = 0;

			Integer wrong_lines = 0;

			try {

				FileReader fileReader = new FileReader(path
						.toString());

				CSVParser csvFileParser = new CSVParser(
						fileReader, csvFileFormat);

				List<CSVRecord> csvRecords = csvFileParser
						.getRecords();

				total_lignes = csvRecords.size();

				for (CSVRecord record : csvRecords) {
					
					if (!record.isConsistent()) {
						
						wrong_lines++;

					}

				}
				
				if (total_lignes == wrong_lines) {
					
					pathList.remove(path);

				}
				

			} catch (Exception e1) {

				e1.printStackTrace();

			}

		});


		return pathList;
		
	}

	public Boolean moveFile(Path path) {

		logger.info("Begin moveFile ......");

		File file 	= new File(path.toString());

		File dir 	= new File(Constants.DEST_PATH);

		boolean success = file.renameTo(new File(dir, file.getName()));

		if (success) {

			logger.info("File moved : " + path);

		} else {

			logger.info("Pb when moving files !!!");
		}

		logger.info("End moveFile function ! \n");

		return success;
	}

}
