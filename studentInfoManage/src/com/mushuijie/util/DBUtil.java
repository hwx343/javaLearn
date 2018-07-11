package com.mushuijie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import com.mysql.jdbc.*;

public class DBUtil {
	

	public Connection openConnection() {
		try {
			Properties prop=new Properties();
			prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
			
			String driver=prop.getProperty("driver");
			String url=prop.getProperty("url");
			String username=prop.getProperty("username");
			String passwd=prop.getProperty("password");
			
			
			Class.forName(driver);
			return DriverManager.getConnection(url, username, passwd);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
