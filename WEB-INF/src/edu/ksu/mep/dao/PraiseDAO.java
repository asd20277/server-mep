package edu.ksu.mep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.ksu.mep.bean.Praise;
import framework.persistence.DataSource;

public class PraiseDAO {
	// 驗証
	public boolean Verification(Praise bean) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		String sql = "select * from praise where userid =" + bean.getUserid()
				+ " and articleid =" + bean.getArticleid();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
			rs.close();
			stmt.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// 加入讚
	public boolean addPriase(Praise bean) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		String sql = "insert into praise (userid , articleid , dtime) values ("
				+ bean.getUserid() + "," + bean.getArticleid() + "," + "'"
				+ bean.getDtime() + "')";
		try {
			Statement stmt = conn.createStatement();
			int row = stmt.executeUpdate(sql);
			if (row >= 1) {
				result = true;
			} else {
				result = false;
			}
			stmt.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// 收回讚
	public boolean delPraise(Praise bean) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		String sql = "delete * from praise where userid =" + bean.getUserid()
				+ "and articleid =" + bean.getArticleid();
		try {
			Statement stmt = conn.createStatement();
			int row = stmt.executeUpdate(sql);
			if (row >= 1) {
				result = true;
			} else {
				result = false;
			}
			stmt.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

}
