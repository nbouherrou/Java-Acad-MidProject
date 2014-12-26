package org.jacademie.projet1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseUtils {

	public static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
	
	public static final String JDBC_PASSWORD = "root";

	public static final String JDBC_USER = "root";

	public static final String JDBC_URL = "jdbc:mysql://localhost:8889/musique";
	
	private static Logger logger = LogManager.getLogger(DatabaseUtils.class);
	/*
	static {
		try {
			Class.forName(DRIVER_CLASSNAME);
		} 
		catch (ClassNotFoundException e) {
			logger.error("Unable to load JDBC Driver");
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	*/
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USER,  JDBC_PASSWORD);
		} catch (SQLException e) {
			logger.error("Unable to get JDBC Connection");
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return connection;
	}
	
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