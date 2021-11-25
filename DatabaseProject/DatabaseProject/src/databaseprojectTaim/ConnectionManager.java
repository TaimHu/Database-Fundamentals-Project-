package databaseprojectTaim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionManager {
	
	private static String dburl="DATABASE URL";
	private static String user="USERNAME";
	private static String pass="PASSWORD";
	
	private static Connection connection;
	
	public static Connection getConnection(){
	
	try {
		connection = DriverManager.getConnection(dburl, user, pass);
		
		
	}
	catch (SQLException e){
		e.printStackTrace();
	}
	return connection;	
	}
	
	

}
