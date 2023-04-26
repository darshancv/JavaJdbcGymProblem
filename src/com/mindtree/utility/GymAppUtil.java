package com.mindtree.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GymAppUtil {
	private static final String url = "jdbc:mysql://localhost:3306/gymprojectjdbc";
	private static final String userName = "root";
	private static final String password = "Welcome123";

	public static Connection getConnection()  {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(url, userName, password);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());

		}
		return connection;
	}

	public static void closeDB(Connection connection)  {
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static String closingResources(Statement statement)  {
		String result = "";
		try {
			statement.close();
			result = "Succcessfully closed the resources";

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static String closingResources(ResultSet rs)  {
		String result = "";
		try {
			if (rs != null) {
				rs.close();
				result = "Succcessfully closed the resources";

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
