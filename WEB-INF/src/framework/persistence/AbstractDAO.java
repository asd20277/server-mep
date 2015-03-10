package framework.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public abstract class AbstractDAO {
	protected abstract String getInsertQuery();
	protected abstract String getUpdateQuery();
	protected abstract String getDeleteQuery();
	protected abstract String getRetrieveQuery();
	
//	public static void executeTransaction(Connection con) {
//		   try {
//		      //Switch to manual transaction mode by setting
//		      //autocommit to false. Note that this starts the first 
//		      //manual transaction.
//		      con.setAutoCommit(false);
//		      Statement stmt = con.createStatement();
//		      stmt.executeUpdate("INSERT INTO Production.ScrapReason(Name) VALUES('Wrong size')");
//		      stmt.executeUpdate("INSERT INTO Production.ScrapReason(Name) VALUES('Wrong color')");
//		      con.commit(); //This commits the transaction and starts a new one.
//		      stmt.close(); //This turns off the transaction.
//		      System.out.println("Transaction succeeded. Both records were written to the database.");
//		   }
//		   catch (SQLException ex) {
//		      ex.printStackTrace();
//		      try {
//		         System.out.println("Transaction failed.");
//		         con.rollback();
//		      }
//		      catch (SQLException se) {
//		         se.printStackTrace();
//		      }
//		   }
//		}

	
	/** 執行 Insert command */
	@SuppressWarnings("finally")
	protected int doInsert(String query) throws SQLException {
		Connection conn = null;
		int result = 0;
		try {
			conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("[SQL-Command-Error]-"+query);
			e.printStackTrace();
		} finally {
			conn.close();
			conn = null;
			return result;
		}
	}
	
	/** 執行 Update command*/
	@SuppressWarnings("finally")
	protected int doUpdate(String query) throws SQLException {
		Connection conn = null;
		int result = 0;
		try {
			conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("[SQL-Command-Error]-"+query);
			e.printStackTrace();
		} finally {
			conn.close();
			conn = null;
			return result;
		}
	}
	
	/** 執行 Delete command*/
	@SuppressWarnings("finally")
	protected int doDelete(String query) throws SQLException {
		Connection conn = null;
		int result = 0;
		try {
			conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("[SQL-Command-Error]-"+query);
			e.printStackTrace();
		} finally {
			conn.close();
			conn = null;
			return result;
		}
	}
	
	
	/** 執行 Select 回傳單一數值*/
	@SuppressWarnings("finally")
	protected int doSelectInteger(String query) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = Integer.parseInt(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("[SQL-Command-Error]-"+query);
			e.printStackTrace();
		} finally {
			conn.close();
			conn = null;
			return result;
		}
	}
	
	/** 執行 Select 回傳單一字串*/
	@SuppressWarnings("finally")
	protected String doSelectString(String query) throws SQLException {
		String result = null;
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getString(1);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("[SQL-Command-Error]-"+query);
			e.printStackTrace();
		} finally {
			conn.close();
			conn = null;
			return result;
		}
		
	}
	
	
	
	/** 將 Table資料轉換為 ArrayList*/
	private ArrayList<String[]> syncModelWithData(ResultSet rs) throws SQLException{
		ArrayList<String[]> result = new ArrayList<String[]>();
		while (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			String[] list = new String[rsmd.getColumnCount()];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				list[i] = (rs.getString(i + 1));
			}
			result.add(list);
		}
		return result;
	}
}
