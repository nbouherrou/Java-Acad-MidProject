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

	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		try {

			logger.info("Welcome to MUSIC App !!!!!!!!!!!!!! \n");
			
			ArtisteDao artisteDao = new ArtisteDao();
			
			HibernateUtils.setUp();
			artisteDao.deleteAllArtistes();
			HibernateUtils.tearDown();

			FileUtils fileUtils = new FileUtils();
			MainControl ctrl = new MainControl();

			ArrayList<Path> pathList = new ArrayList<Path>();
			ArrayList<Path> goodPath = new ArrayList<Path>();

			pathList = fileUtils.findFileInRepoWithGoodExtension(
					Constants.pathSource, Constants.extension);

			goodPath = fileUtils.isGoodFile(pathList);

			if (!goodPath.isEmpty()) {

				goodPath.forEach(path -> {

					ctrl.mainControl(path);
					
					/*
					File file = new File(path.toString());
					
					File dir = new File(Constants.pathTarget);
					
					boolean success = file.renameTo(new File(dir, file.getName()));
					
					if (success) {
						
						logger.info("File moved -- : " + path);
					}else {
						
						logger.info("Pb when moving files !!!");
					}
					*/
					
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