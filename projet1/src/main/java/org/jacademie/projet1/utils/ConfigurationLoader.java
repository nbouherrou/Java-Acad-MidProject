package org.jacademie.projet1.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ConfigurationLoader {
	
	private static Logger logger = LogManager.getLogger(ConfigurationLoader.class);
	
	
	public static void main(String[] args) {
		
		
		JSONObject json_object = FileUtils.loadConfigurationFile();
		
		logger.info(json_object.get("user"));
		
		logger.info(json_object.get("password"));
		
		logger.info(json_object.get("url"));
		

	}

}
