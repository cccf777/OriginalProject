package com.mvc.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			 String url = "jdbc:mysql://3.37.151.149:3306/project";
			 String id = "cccff";
			 String pass = "1234";
			 String driver = "com.mysql.jdbc.Driver";
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, id, pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//jdbc:mysql://localhost:3306/study?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
}
