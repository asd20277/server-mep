package edu.ksu.mep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.bean.ArticleMessage;
import framework.persistence.DataSource;

public class ArticleMessageDAO {

	// 新增留言
	public boolean addArticleMessage(ArticleMessage mes) {
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sq = "Insert into  message (articleid,userid,message,dtime) values ("
					+ "'"
					+ mes.getArticleid()
					+ "',"
					+ "'"
					+ mes.getUserid()
					+ "',"
					+ "'"
					+ mes.getMessage()
					+ "',"
					+ "'"
					+ mes.getDtime() + "')";
			stmt.executeUpdate(sq);

			result = true;
			stmt.close();
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 取得留言
	public ArrayList getArticleMessageList(ArticleMessage mes) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select message.id , account.uname , message.message , message.dtime from account , message where account.id = message.userid and articleid = "
				+ mes.getArticleid();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				ArticleMessage mess = new ArticleMessage();
				String s = rs.getString("id");
				int w = Integer.parseInt(s);
				mess.setId(w);
				mess.setUname(rs.getString("uname"));
				mess.setMessage(rs.getString("message"));
				mess.setDtime(rs.getString("dtime"));
				result.add(mess);
			}
			System.out.print(result);
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 刪除留言
	public boolean delArticleMessageToDb(ArticleMessage arm) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete * from message where id = " + arm.getId();
			System.out.println(sql);
			int row = stmt.executeUpdate(sql);

			if (row >= 1) {
				result = true;
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

	public ArrayList getNumberOfPosts(ArticleMessage mes) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select message.id , account.uname , message.message , message.dtime from account , message where account.id = message.userid and articleid = "
			+ mes.getArticleid();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int a = 0;
			while (rs.next()) {
				
				int i = ++a;
				result.add(i);
			}
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
