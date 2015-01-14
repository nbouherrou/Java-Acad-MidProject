package org.jacademie.projet1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;


/**
 * Classe qui gère l'interaction avec la BDD MySQL
 * @author jacademie-team
 *
 */
public class DatabaseUtils {

	/**
	 * Nom du Driver de la BDD
	 */
	public static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
	
	/**
	 * Nom de l'utilisateur MySQL
	 */
	public static String JDBC_USER = "root";

	
	/**
	 * Mot de passe de l'utilisateur MySQL
	 */
	public static String JDBC_PASSWORD = "root";
	
	/**
	 * Url de connexion du driver à la BDD MySQL
	 */
	public static String JDBC_URL = "jdbc:mysql://localhost:8889/musique";
	
	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(DatabaseUtils.class);

	
	/**
	 * Genere une connexion à la BDD MySQL.
	 * 
	 * @return		Connection
	 */
	public static Connection getConnection() {
		
		Connection connection = null;
		
		JSONObject json_object = FileUtils.loadConfigurationFile();
		
		if(!json_object.get("user").toString().isEmpty()){
			
			JDBC_USER = json_object.get("user").toString();
		}
		
		if(!json_object.get("password").toString().isEmpty()){
			
			JDBC_PASSWORD = json_object.get("password").toString();
		}
		
		if(!json_object.get("url").toString().isEmpty()){
			
			JDBC_URL = json_object.get("url").toString();
		}
		

//		logger.info(" JDBC_USER " + JDBC_USER);
//		
//		logger.info(" JDBC_PASSWORD " + JDBC_PASSWORD);
//		
//		logger.info(" JDBC_URL " + JDBC_URL);
		
		
		try {
			
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USER,  JDBC_PASSWORD);
			
		} catch (SQLException e) {
			logger.error("Unable to get JDBC Connection");
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return connection;
	}
	
	/**
	 * Ferme la connexion à la BDD MySQL.
	 * 
	 * @param Connection connection : objet Connection
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} 
			catch (SQLException e) {
				logger.error("Unable to close JDBC Connection");
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	public static void main(String[] args) {
		
		getConnection();
		
	}
}
