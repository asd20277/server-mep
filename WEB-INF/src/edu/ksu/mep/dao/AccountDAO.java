package edu.ksu.mep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ksu.mep.bean.Account;
import framework.persistence.DataSource;

public class AccountDAO {

	// 登入web
	public boolean isValid(Account bean) {
		boolean result = false;
		try {
			Connection conn =DataSource.getConnection();
			// 直 接 使 用 JDBC 的 標 準 步 驟
			String query = "select * from account where username='"
				+ bean.getUsername() + "' and password='" + bean.getPassword()
				+ "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			if (rs.next()) {
				result = true;
				boolean a = rs.getBoolean("admin");
				bean.setAdmin(a);
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setUname(rs.getString("uname"));
				bean.setEmail(rs.getString("email"));
				bean.setBirthday(rs.getString("birthday"));
			}
			rs.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 登入android
	public Account login(Account acct) {
		// 直 接 使 用 JDBC 的 標 準 步 驟
		String query = "select * from account where username='"
				+ acct.getUsername() + "' and password='" + acct.getPassword()
				+ "'";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {

				boolean a = rs.getBoolean("admin");
				acct.setAdmin(a);
				acct.setId(rs.getInt("id"));
				acct.setUsername(rs.getString("username"));
				acct.setPassword(rs.getString("password"));
				acct.setUname(rs.getString("uname"));
				acct.setEmail(rs.getString("email"));
				acct.setBirthday(rs.getString("birthday"));
			}

			rs.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return acct;

	}

	// 註冊
	public boolean regis(Account regis) {
		boolean result = false;
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int rowsInserted = stmt.executeUpdate("Insert into  account (username,password,email,uname,birthday) values ("
							+ "'"
							+ regis.getUsername()
							+ "',"
							+ "'"
							+ regis.getPassword()
							+ "',"
							+ "'"
							+ regis.getEmail()
							+ "',"
							+ "'"
							+ regis.getUname()
							+ "',"
							+ "'"
							+ regis.getBirthday() + "')");
			if(rowsInserted >=1){
				result = true;
			}
			
			stmt.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	//修改資料TODB
	public boolean UpdateAccountToDB(Account UpdateAccountToDB) {
		boolean result = false;
		String updateSQL = "update account set " + "email='"
				+ UpdateAccountToDB.getEmail() + "'," + "uname='"
				+ UpdateAccountToDB.getUname() + "'," + "birthday='"
				+ UpdateAccountToDB.getBirthday() + "' where id="
				+ UpdateAccountToDB.getId();
		
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int rowsInserted = stmt.executeUpdate(updateSQL);
			if(rowsInserted >=1){
				result = true;
			}
			
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//關於我
	public Account getAccountData(Account ac) {
		// TODO Auto-generated method stub
		Account ad = new Account();
		String sql ="select username , uname , birthday , email from account where id =" + ac.getId(); 
		Connection conn = null;
		conn = DataSource.getConnection();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				ad.setUsername(rs.getString("username"));
				ad.setUname(rs.getString("uname"));
				ad.setBirthday(rs.getString("birthday"));
				ad.setEmail(rs.getString("email"));
				
			}
			
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ad;
	}
	
	//關於我
	public Account getAboutMe(Account acc) {  //關於我show資料
		
		Connection conn = null;//取資料庫連結
		conn = DataSource.getConnection();//取資料庫連結
		
		String query = "Select * from account where username='" 
		+ acc.username + "' and password='" + acc.password +"'";
		
		Object obj = null;		
		try{
			//建立一個空籃子裝資料
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				
				acc.setUsername(rs.getString("username"));
				acc.setPassword(rs.getString("password"));
				acc.setUname(rs.getString("uname"));
				acc.setEmail(rs.getString("email"));	
				acc.setBirthday(rs.getString("birthday"));											
			}
				
			rs.close();
			conn.close();
			conn = null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return acc;
		
	} 
	
	//修改會員資料
	public ArrayList getAccount(Account acc) {
			ArrayList result = new ArrayList();
			String selectSQL = "Select id , username , password , uname , email , birthday from account where id = "
					+ acc.getId();
			Connection conn = null;
			conn = DataSource.getConnection();
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectSQL);
				if (rs.next()) {
					Account update = new Account();
					update.setId(rs.getInt("id"));
					update.setUsername(rs.getString("username"));
					update.setPassword(rs.getString("password"));
					update.setUname(rs.getString("uname"));
					update.setEmail(rs.getString("email"));
					update.setBirthday(rs.getString("birthday"));
					result.add(update);
				}
				rs.close();
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	//驗證
	public boolean Verification(Account regis) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			Connection conn =DataSource.getConnection();
		// 直 接 使 用 JDBC 的 標 準 步 驟
		String query = "select * from account where username='"
				+ regis.getUsername() + "' and password='" + regis.getPassword()
				+ "'";
		// Object obj = null;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
			if (rs.next()) {
				result = true;
			}
			else{
				result = false;   
			}
			rs.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
