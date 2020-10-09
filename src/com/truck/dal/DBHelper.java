package com.truck.dal;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	public static Connection getConnection() {
		 
		System.out.println("DBHelper: -------- PostgreSQL " + "JDBC Connection  ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("DBHelper: Check Where  your PostgreSQL JDBC Driver exist and " + "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TruckingCompany", "postgres", "ted");
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT VERSION()");

	            if (rs.next()) {
	                System.out.println("DBHelper: The Database Version is " + rs.getString(1));
	            }
 
		} catch (SQLException e) {
 
			System.out.println("DBHelper: Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: You have a database connection!");
		
		return connection;
	}
}
