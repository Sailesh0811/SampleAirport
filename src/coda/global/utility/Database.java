package coda.global.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String url = "jdbc:mysql://localhost:3306/airportdb";
	private static final String username = "root";
	private static final String password = "sai12345";
	
	private static Connection connection;
	public Connection getConnection() {
		System.out.println("Connecting database...");		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
			
			connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		 catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		return connection;
	}
	public  void closeConnection() {
		System.out.println("Close connection!!");
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}

