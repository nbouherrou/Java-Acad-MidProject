package org.jacademie.projet1;

import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;
import org.jacademie.projet1.controller.MainControl;
import org.jacademie.projet1.utils.FileUtils;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		try {

			logger.info("Welcome to MUSIC App !!!!!!!!!!!!!!");

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

				});

			} else {
				logger.info("No correct files are found !!!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("GoodBye !");

	}

}