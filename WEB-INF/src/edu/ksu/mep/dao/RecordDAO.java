package edu.ksu.mep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ksu.mep.bean.Record;
import framework.persistence.DataSource;

public class RecordDAO {
	
	//新增Record
	public boolean addRecord(Record record) {
		// TODO Auto-generated method stub
		boolean resulr = false;
		Connection connection = null;
		connection = DataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "Insert into record (userid,title,recordname,species,latitude,longitude,dtime,viewers) values ("
						+ "'"
						+ record.getUserid()
						+ "',"
						+ "'"
						+ record.getTitle()
						+ "',"
						+ "'"
						+ record.getRecordname()
						+ "',"
						+ "'"
						+ record.getSpecies()
						+ "',"
						+ "'"
						+ record.getLatitude()
						+ "',"
						+ "'"
						+ record.getLongitude()
						+ "',"
						+ "'"
						+ record.getDatetime()
						+ "',"
						+ record.getViewers()
						+ ")";
			System.out.print(sql);
			int row = statement.executeUpdate(sql);
			if (row >= 1) {
				resulr = true;
			}
			statement.close();
			connection.close();
			connection = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resulr;
	}
	
	//我的Record列表
	public ArrayList getMyRecordList(Record record) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select record.id , record.userid , record.title , record.recordname , record.species , record.latitude , record.longitude , record.dtime , account.uname from record , account where account.id = record.userid and record.userid = " + record.getUserid() + " order by record.id desc";
		Connection connection = null;
		connection = DataSource.getConnection();
		try {
			Statement stmtStatement = connection.createStatement();
			ResultSet rs = stmtStatement.executeQuery(sql);
			
			while (rs.next()) {
				Record data = new Record();
				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setRecordname(rs.getString("recordname"));
				data.setSpecies(rs.getString("species"));
				data.setLatitude(rs.getString("latitude"));
				data.setLongitude(rs.getString("longitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setUname(rs.getString("uname"));
				data.setUserid(rs.getString("userid"));
				result.add(data);
			}
			
			if (rs != null)
				rs.close();
			if (stmtStatement != null)
				stmtStatement.close();
			if (connection != null)
				connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//最新Record列表
	public ArrayList getNewRecordList(Record record) {
		// TODO Auto-generated method stub
		
		ArrayList result = new ArrayList();
		String sql = "select record.id , record.userid , record.title , record.recordname , record.species , record.latitude , record.longitude , record.dtime , account.uname from record , account where account.id = record.userid " + " order by record.id desc";
		Connection connection = null;
		connection = DataSource.getConnection();
		try {
			Statement stmtStatement = connection.createStatement();
			ResultSet rs = stmtStatement.executeQuery(sql);
			
			while (rs.next()) {
				Record data = new Record();
				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setRecordname(rs.getString("recordname"));
				data.setSpecies(rs.getString("species"));
				data.setLatitude(rs.getString("latitude"));
				data.setLongitude(rs.getString("longitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setUname(rs.getString("uname"));
				data.setUserid(rs.getString("userid"));
				result.add(data);
			}
			
			if (rs != null)
				rs.close();
			if (stmtStatement != null)
				stmtStatement.close();
			if (connection != null)
				connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//熱門Record列表
	public ArrayList getHotRecordList(Record record) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select record.id , record.userid , record.title , record.recordname , record.species , record.latitude , record.longitude , record.dtime , account.uname from record , account where account.id = record.userid " + " order by record.viewers desc";
		Connection connection = null;
		connection = DataSource.getConnection();
		try {
			Statement stmtStatement = connection.createStatement();
			ResultSet rs = stmtStatement.executeQuery(sql);
			
			while (rs.next()) {
				Record data = new Record();
				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setRecordname(rs.getString("recordname"));
				data.setSpecies(rs.getString("species"));
				data.setLatitude(rs.getString("latitude"));
				data.setLongitude(rs.getString("longitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setUname(rs.getString("uname"));
				data.setUserid(rs.getString("userid"));
				result.add(data);
			}
			
			if (rs != null)
				rs.close();
			if (stmtStatement != null)
				stmtStatement.close();
			if (connection != null)
				connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//刪除Record
	public boolean delRecord(Record record) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete * from record where id = " + record.getId();
		Connection connection = null;
		connection = DataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			int row = statement.executeUpdate(sql);
			if(row >= 1){
				result = true;
			}
			statement.close();
			connection.close();
			connection = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	// 觀看人數+1
	public boolean addRecordViewers(Record record) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "update record set viewers=viewers+1 where id = " + record.getId();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int row = stmt.executeUpdate(sql);
			if(row >= 1){
				result = true;
			}
			stmt.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

}
