package org.jacademie.projet1.utils;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Classe qui gère les fonctions utiles à la manipulation des fichiers
 * @author jacademie-team
 *
 */
public class FileUtils {

	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(FileUtils.class);

	/**
	 * Récupère dans un dossier les chemins des fichiers qui ont une extension donnée.
	 * @param 	path			: chemin vers un répertoire
	 * @param 	extension		: extension des fichiers cibles
	 * @return	ArrayList<Path>	: liste de chemins vers les fichiers avec la bonne extension
	 */
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
	 * Filtre les fichiers selon format (CVS).
	 * Les chemins retenus sont relatifs aux fichiers sans erreurs (aucune ligne erroné)
	 * @param 		pathList		: liste de chemins 
	 * @return 		ArrayList<Path> : liste de chemins des fichiers valides
	 */
	public ArrayList<Path> filterGoodFiles(ArrayList<Path> pathList) {

		logger.info("In filterGoodFiles function ......");

		ArrayList<Path> paths = pathList;

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(Constants.FILE_HEADER_MAPPING);
		
		pathList.stream().forEach( path -> {

			logger.info("Path " + path);

			try {

				FileReader fileReader = new FileReader(path
						.toString());

				CSVParser csvFileParser = new CSVParser(
						fileReader, csvFileFormat);

				List<CSVRecord> csvRecords = csvFileParser
						.getRecords();

				for (CSVRecord record : csvRecords) {
					
					if (!record.isConsistent()) {
						
						pathList.remove(path);

					}

				}
				
			} catch (Exception e1) {

				e1.printStackTrace();

			}

		});


		return pathList;
		
	}

	/**
	 * Deplace un fichier vers le répertoire "src/ressources/precessed"
	 * @param 		path	: chemin du fichier
	 * @return		Boolean
	 */
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
	
	/**
	 * Lit le fichier de configuration (src/main/resources/Configuration.json) 
	 * et le retourne comme objet JSON.
	 * 
	 * @return	JSONObject
	 */
	public static JSONObject loadConfigurationFile(){
		
		Path path 		= Paths.get(Constants.RESSOURCE_DIR_PATH, "Configuration.json");
		
		logger.info(path.toString());
		
	    JSONParser parser = new JSONParser();
    
	    Object obj = null;
	    
		try {
			
			obj = parser.parse(new FileReader(path.toString()));
			
		} catch (IOException | ParseException e) {
			
			e.printStackTrace();
		}

		return (JSONObject)obj;
	}

}
