package com.auction.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;

public class JdbcUtil {
	public Properties dbProp(){
		Properties aProps = new Properties();
		String filename ="database/db.properties";
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(filename);
			 
			if (inputStream != null) {
				aProps.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + filename + "' not found in the classpath");
			}
	}catch(Exception e){
		e.printStackTrace();
	}
		return aProps;
	}
	

	public static Connection getConnection() {
		Connection conn = null;
		Properties aProps = new JdbcUtil().dbProp();
		try{
			Class.forName(aProps.getProperty("driver"));
			conn =  DriverManager.getConnection(aProps.getProperty("url"), aProps.getProperty("username"),
					aProps.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static int getUserId() {
		String userid = "SELECT MAX(USERID) FROM USERDETAILS";
		int value = 0;
		Connection conn =getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(userid);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return value;

	}

	public static int getAddressId() {
		String addressid = "SELECT MAX(ADDRESSID) FROM ADDRESS";
		int value = 0;
		Connection conn =getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(addressid);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return value;

	}

	
	  public static void main(String [] a){
	 
	 int i = getUserId(); System.out.println("max value "+ i); }
	 

}
