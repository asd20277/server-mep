package framework.persistence;

import java.sql.*;
import java.util.Properties;

import framework.utility.*;

public class DataSource {
	private static String url = DatabaseConstants.DB_URL;
	//private static String user=DatabaseConstants.DB_USER;
	//private static String passwd=DatabaseConstants.DB_PASSWORD;
	private static DataSource dataSource = null;
	
	
	private DataSource(){
	}
	
	public static DataSource getInstance(){
		if(dataSource == null){
			dataSource = new DataSource();
		}
		return dataSource;
	}
	
	 
	public static Connection getConnection(){
		Connection conn = null;
		Properties prop = new Properties();
		prop.put("charSet", "big5");
		try {
			Class.forName(DatabaseConstants.DB_DRIVER);
			conn = DriverManager.getConnection(url, prop); 		
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Just close for now. Should be ported to connection pool.
	 * @param conn
	 */
	public void returnConnection(Connection conn){
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
