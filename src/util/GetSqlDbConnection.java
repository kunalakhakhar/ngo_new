package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetSqlDbConnection {

	private static Connection conn = null;

	public GetSqlDbConnection() {
		
	}
	
	public static Connection getDbConnection(){
		if (conn == null) {
			connectToMySql();
		}
		return conn;
	}
	
	
	private static void connectToMySql(){
		String url = "jdbc:mysql://127.0.0.1/";
		String dbName = "prempuri_ashram";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "lanuk";
		try {
		  Class.forName(driver).newInstance();
		  conn = DriverManager.getConnection(url+dbName,userName,password);
		  System.out.println("Connected to the database");
		  //conn.close();
		  System.out.println("Disconnected from database");
		} catch (Exception e) {
		    System.out.println("NO CONNECTION =(");
		}
	}
}
