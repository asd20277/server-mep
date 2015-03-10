package edu.ksu.mep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.bean.Favorite;
import framework.persistence.DataSource;

public class FavoriteDAO {
	
	//新增收藏
	public boolean addFavorite(Favorite fav) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sq = "insert into favorite (userid , articleid) values ( ' " +fav.getUserid()+ " ',' " +fav.getArticleid()+ "' )";
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
	//取得收藏列表
	public ArrayList getFavoriteList(Favorite fav) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select article.viewers , favorite.id , article.content , article.species , account.uname , article.title , article.dtime , favorite.articleid from account , article , favorite where account.id = article.userid and article.id = favorite.articleid and favorite.userid = " + fav.getUserid()  + " order by favorite.id desc";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Favorite favorite = new Favorite();
				
				String s = rs.getString("id");
				int id = Integer.parseInt(s);
				favorite.setId(id);
				
				String x = rs.getString("title");
				String y = x.substring(0, 5) + "...";
				favorite.setTitle(y);
				
				String a = rs.getString("content");
				String z = a.substring(0, 5) + "...";
				favorite.setContent(z);
				
				favorite.setUname(rs.getString("uname"));
				favorite.setDtime(rs.getString("dtime"));
				favorite.setArticleid(rs.getInt("articleid"));
				favorite.setSpecies(rs.getString("species"));
				String view = rs.getString("viewers");
				int viewers = Integer.parseInt(view);
				favorite.setViewers(viewers);
				result.add(favorite);
			}
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//回覆數
		int k = result.size();
		for(int i = 0; i < k; i++){
			Favorite a = (Favorite)result.get(i);
			int id = a.getId();
			int z = GetNumberOfPosts(id);
			a.setNop(z);			
		}
		
		//按讚數	
		for(int i =0; i < k; i++){
			Favorite z = (Favorite)result.get(i);
			int artid = z.getId();
			int x = GetPraiseNumbers(artid);
			z.setTn(x);
		}
		return result;
	}
	//回覆數
	public int GetNumberOfPosts(int artid){
		String mes = "select * from message where articleid = " + artid;
		Connection con = null;
		con = DataSource.getConnection();
		int i = 0;
		try {
			Statement stm = con.createStatement();
			ResultSet re = stm.executeQuery(mes);
			while (re.next()) {
				++i;
			}
			re.close();
			re = null;
			stm.close();
			stm = null;
			con.close();
			con = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}
	
	//按讚數
	private int GetPraiseNumbers(int artid) {
		// TODO Auto-generated method stub
		String pra = "select * from praise where articleid = " + artid;
		Connection co = null;
		co = DataSource.getConnection();
		int j = 0;
		try {
			Statement st = co.createStatement();
			ResultSet r = st.executeQuery(pra);
			while (r.next()) {
				++j;
			}
			r.close();
			st.close();
			co.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return j;
	}
	//驗證
	public boolean Verification(Favorite fav) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		String sql = "select * from favorite where articleid =" + fav.getArticleid() + "and userid =" + fav.getUserid();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				result = true;
			}else {
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
	//刪除收藏
	public boolean delFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete * from favorite where id = " + favorite.getId();
			System.out.print(sql);
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
