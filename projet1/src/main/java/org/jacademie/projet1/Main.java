package org.jacademie.projet1;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;
import org.jacademie.projet1.controller.MainControl;
import org.jacademie.projet1.dao.ArtisteDao;
import org.jacademie.projet1.utils.FileUtils;
import org.jacademie.projet1.utils.HibernateUtils;

public class Main {

	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(Main.class);

	
	
	public static void main(String[] args) {

		try {

			logger.info("Welcom to MUSE-APP");
			
			logger.info("Created by : N. BOUHERROU - O. MARIN - A. AHMED BACHA");
			
			// On vide la BDD des enregistrements précédents
			
			ArtisteDao artisteDao = new ArtisteDao();
			
			HibernateUtils.setUp();
			
			artisteDao.deleteAllArtistes();
			
			HibernateUtils.tearDown();
			

			FileUtils fileUtils 		= new FileUtils();
			
			MainControl ctrl 			= new MainControl();

			ArrayList<Path> pathList 	= new ArrayList<Path>();
			
			ArrayList<Path> goodPath 	= new ArrayList<Path>();
			
			// Récupération et filtrage des bons fichiers

			pathList 					= fileUtils.getFilesInDirectoryWithExtension(Constants.SOURCE_PATH, Constants.EXTENSION);

			goodPath 					= fileUtils.filterGoodFiles(pathList);
			


			if (!goodPath.isEmpty()) {

				goodPath.forEach(path -> {
					
					// Traitement du fichier puis déplacement dans le dossier "processed"

					ctrl.mainControl(path);

					
					fileUtils.moveFile(path);	
					
					
				});

			} else {
				logger.info("No correct files are found !!!  \n");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("GoodBye !");

	}

}