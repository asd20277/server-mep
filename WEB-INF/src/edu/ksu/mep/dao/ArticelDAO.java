package edu.ksu.mep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleMessage;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.bean.Page;
import framework.persistence.DataSource;

public class ArticelDAO {

	// 新增文章
	public boolean addArticle(ArticleItem add) {
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sq = "Insert into  article (title,content,userid,longitude,latitude,dtime,species,article_pic,viewers) values ("
					+ "'"
					+ add.getTitle()
					+ "',"
					+ "'"
					+ add.getContent()
					+ "',"
					+ "'"
					+ add.getUserid()
					+ "',"
					+ "'"
					+ add.getLongitude()
					+ "',"
					+ "'"
					+ add.getLatitude()
					+ "',"
					+ "'"
					+ add.getDatetime()
					+ "',"
					+ "'"
					+ add.getSpecies()
					+ "',"
					+ "'"
					+ add.getPhotoname()
					+ "',"
					+ "'"
					+add.getViewers()
					+ "')";
			int a = stmt.executeUpdate(sq);
			if (a >= 1) {
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
	
	//回覆數
	public int GetNumberOfPosts(String artid){
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
	private int GetPraiseNumbers(String artid) {
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
	
	// 叫出自己文章列表
	public ArrayList getMyArticleList(ArticleItem mylist) {
		ArrayList result = new ArrayList();
		String sql = "select article.article_pic , article.viewers , article.species , article.id , account.uname , article.title , article.content , article.dtime , article.longitude , article.latitude from account , article where account.id = article.userid and article.userid = "
				+ mylist.getUserid() + " order by article.id desc";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ArticleItem data = new ArticleItem();
				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setViewers(rs.getInt("viewers"));
				data.setUname(rs.getString("uname"));
				data.setLongitude(rs.getString("longitude"));
				data.setLatitude(rs.getString("latitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setSpecies(rs.getString("species"));
				data.setPhotoname(rs.getString("article_pic"));
				result.add(data);
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
			ArticleItem a = (ArticleItem)result.get(i);
			String id = a.getId();
			int z = GetNumberOfPosts(id);
			a.setNop(z);			
		}
		
		//按讚數	
		for(int i =0; i < k; i++){
			ArticleItem z = (ArticleItem)result.get(i);
			String artid = z.getId();
			int x = GetPraiseNumbers(artid);
			z.setTn(x);
		}
		
		return result;
	}


	// 叫出文章內容
	public ArrayList getArticleContent(Article articleid) {
		ArrayList result = new ArrayList();
		
		String sql = "select article.article_pic , article.userid , article.species , article.id , account.uname , article.title , article.content , article.dtime , article.longitude , article.latitude from account , article where account.id = article.userid and article.id = "
				+ articleid.getId();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ArticleItem data = new ArticleItem();

				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setUname(rs.getString("uname"));
				data.setDatetime(rs.getString("dtime"));
				data.setLongitude(rs.getString("longitude"));
				data.setLatitude(rs.getString("latitude"));
				data.setSpecies(rs.getString("species"));
				data.setUserid(rs.getString("userid"));
				data.setPhotoname(rs.getString("article_pic"));
				result.add(data);
			}
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

			// System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
																																																																		
		String mes = Integer.toString(articleid.getId());
		ArticleItem a = (ArticleItem)result.get(0);
		ArrayList arrayList =  getArticleMessageList(mes);
		a.setMessage(arrayList);
		
		
		return result;
	}
	
	
	
	
	// 取得留言
	public ArrayList getArticleMessageList(String mes) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select message.id , account.uname , message.message , message.dtime from account , message where account.id = message.userid and articleid = "
				+ mes;
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
	
	
	
	
	
	
	
	
	

	// 刪除文章
	public boolean delMyArticleTODB(Article delid) {
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String a = "delete * from article where id =  " + delid.getId();
			System.out.print(a);
			int row = stmt.executeUpdate("delete * from article where id =  "
					+ delid.getId());

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

	// 搜尋文章
	public ArrayList getSearchList(ArticleItem art) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select article.viewers , article.id , article.title , article.content , account.uname , article.species , article.dtime from account , article "
				+ "where account.id = article.userid and"
				+ " article.title like '%"
				+ art.getContent()
				+ "%' "
				+ "or article.content like '%" + art.getContent() + "%'" 
				+ "or account.uname like '%" + art.getContent();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArticleItem ar = new ArticleItem();
				String x = rs.getString("title");
				String y = x.substring(0, 7) + "...";
				ar.setTitle(y);
				String k = rs.getString("content");
				String c = k.substring(0, 10) + "...";
				ar.setContent(c);
				ar.setId(rs.getString("id"));
				ar.setUname(rs.getString("uname"));
				ar.setSpecies(rs.getString("species"));
				ar.setDatetime(rs.getString("dtime"));
				ar.setViewers(rs.getInt("viewers"));
				result.add(ar);
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
		return result;
	}

	// 拿取所有經緯度
	public ArrayList getAllLongitudeAndLatitude(ArticleItem az) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select article.species , article.dtime , account.uname , article.id , article.title , article.longitude , article.latitude from article , account where article.userid = account.id";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArticleItem a = new ArticleItem();
				a.setId(rs.getString("id"));
				a.setTitle(rs.getString("title"));
				a.setLongitude(rs.getString("longitude"));
				a.setLatitude(rs.getString("latitude"));
				a.setDatetime(rs.getString("dtime"));
				a.setUname(rs.getString("uname"));
				a.setSpecies(rs.getString("species"));
				result.add(a);
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
		return result;
	}

	// 叫出最新文章
	public ArrayList getNewArticleList(ArticleItem ar) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select article.viewers , article.id , article.species , account.uname , article.title , article.content , article.dtime , article.longitude , article.latitude from article , account where account.id = article.userid order by article.id desc";
//		System.out.println(sql);
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArticleItem data = new ArticleItem();
				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setViewers(rs.getInt("viewers"));
				data.setUname(rs.getString("uname"));
				data.setLongitude(rs.getString("longitude"));
				data.setLatitude(rs.getString("latitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setSpecies(rs.getString("species"));
				result.add(data);
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
			ArticleItem a = (ArticleItem)result.get(i);
			String id = a.getId();
			int z = GetNumberOfPosts(id);
			a.setNop(z);			
		}
		
		//按讚數	
		for(int i =0; i < k; i++){
			ArticleItem z = (ArticleItem)result.get(i);
			String artid = z.getId();
			int x = GetPraiseNumbers(artid);
			z.setTn(x);
		}
		
		return result;
	}
	
	//觀看人數+1
	public boolean addViewers(ArticleItem arm) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try{
			Statement stmt = conn.createStatement();
			String sql = "update article set viewers=viewers+1 where id = " + arm.getId();
			int a = stmt.executeUpdate(sql);
			if (a >= 1) {
				result = true;
			}
			stmt.close();
			conn.close();
			conn = null;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	//抓取單一文章經緯度
	public ArrayList getArticleLongitudeAndLatitude(ArticleItem arm) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select id , title , dtime, longitude , latitude from article where id =" + arm.getId();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArticleItem a = new ArticleItem();
				a.setId(rs.getString("id"));
				a.setTitle(rs.getString("title"));
				a.setLongitude(rs.getString("longitude"));
				a.setLatitude(rs.getString("latitude"));
				a.setDatetime(rs.getString("dtime"));
				result.add(a);
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
		return result;
	}
	
	// 叫出熱門文章
	public ArrayList getHotArticleList(ArticleItem article) {
		// TODO Auto-generated method stub
		ArrayList result = new ArrayList();
		String sql = "select article.viewers , article.id , article.species , account.uname , article.title , article.content , article.dtime , article.longitude , article.latitude from article , account where account.id = article.userid order by viewers desc";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ArticleItem data = new ArticleItem();
				data.setId(rs.getString("id"));

//				String x = rs.getString("title");
//				String b = x.substring(0,5) + "......";
				data.setTitle(rs.getString("title"));

//				String a = rs.getString("content");
//				String z = a.substring(0,5) + ".......";
				data.setContent(rs.getString("content"));

				data.setViewers(rs.getInt("viewers"));
				data.setUname(rs.getString("uname"));
				data.setLongitude(rs.getString("longitude"));
				data.setLatitude(rs.getString("latitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setSpecies(rs.getString("species"));
				result.add(data);
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
			ArticleItem a = (ArticleItem)result.get(i);
			String id = a.getId();
			int z = GetNumberOfPosts(id);
			a.setNop(z);			
		}
		
		//按讚數	
		for(int i =0; i < k; i++){
			ArticleItem z = (ArticleItem)result.get(i);
			String artid = z.getId();
			int x = GetPraiseNumbers(artid);
			z.setTn(x);
		}
		return result;
	}
	
	//拿取照片名稱
	public ArticleItem getArticlePhoto(ArticleItem photo) {
		// TODO Auto-generated method stub
		String sql = "select article_pic from article where id =" + photo.getId();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String a = rs.getString("article_pic");
				photo.setPhotoname(a);
			}
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return photo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////web
	public boolean addArticle1(ArticleItem add) {
		boolean result = false;
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sq = "Insert into  article (title,content,userid,longitude,latitude,dtime,species,article_pic,viewers) values ("
					+ "'"
					+ add.getTitle()
					+ "',"
					+ "'"
					+ add.getContent()
					+ "',"
					+ "'"
					+ add.getUserid()
					+ "',"
					+ "'"
					+ add.getLongitude()
					+ "',"
					+ "'"
					+ add.getLatitude()
					+ "',"
					+ "'"
					+ add.getDatetime()
					+ "',"
					+ "'"
					+ add.getSpecies()
					+ "',"
					+ "'"
					+ add.getPhotoname()
					+ "',"
					+ "'"
					+add.getViewers()
					+ "')";
			int a = stmt.executeUpdate(sq);
			if (a >= 1) {
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
	
	
	public ArrayList getAllArticleList1(Article alllist) {
		ArrayList result = new ArrayList();
		String sql = "select article.viewers , article.species , article.id , account.uname , article.title , article.content , article.dtime , article.longitude , article.latitude from account , article where account.id = article.userid ";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ArticleItem data = new ArticleItem();
				data.setId(rs.getString("id"));

				String x = rs.getString("title");
				String y = x.substring(0, 5) + "...";
				data.setTitle(y);

				String a = rs.getString("content");
				String z = a.substring(0, 5) + "...";
				data.setContent(z);
				
				data.setViewers(rs.getInt("viewers"));
				data.setUname(rs.getString("uname"));
				data.setDatetime(rs.getString("dtime"));
				data.setSpecies(rs.getString("species"));
				result.add(data);
			}
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

			// System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	// 叫出文章內容
	public ArticleItem getArticleContent1(Article articleid) {
		ArrayList result = new ArrayList();
		ArticleItem data = null;
		String sql = "select article_pic , article.userid , article.species , article.id , account.uname , article.title , article.content , article.dtime , article.longitude , article.latitude from account , article where account.id = article.userid and article.id = "
				+ articleid.getId();
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				data = new ArticleItem();
				String a = rs.getString("id");
				String b = rs.getString("title");
				String c = rs.getString("content");
				String d = rs.getString("uname");
				String e = rs.getString("dtime");
				String f = rs.getString("longitude");
				String g = rs.getString("latitude");
				String h = rs.getString("species");
				String i = rs.getString("userid");
				
				
				data.setId(a);
				data.setTitle(b);
				data.setContent(c);
				data.setUname(d);
				data.setDatetime(e);
				data.setLongitude(f);
				data.setLatitude(g);
				data.setSpecies(h);
				data.setUserid(i);
				data.setPhotoname(rs.getString("article_pic"));
			}
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

			// System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
	public ArrayList getMyArticleList1(Page page){
		ArrayList result = new ArrayList();
		int counter =0;
		String sql = "select * from Article order by dtime desc";
		Connection conn = null;
		conn = DataSource.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if(counter >= page.getStartIndex() && counter <= page.getEndIndex()){
				ArticleItem data = new ArticleItem();
				data.setId(rs.getString("id"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setViewers(rs.getInt("viewers"));
				data.setLongitude(rs.getString("longitude"));
				data.setLatitude(rs.getString("latitude"));
				data.setDatetime(rs.getString("dtime"));
				data.setSpecies(rs.getString("species"));
				result.add(data);
			}
				counter++;
			}		
				int totalPage = 0;
				if(counter % page.itemPerPage ==0){
					totalPage = counter / page.itemPerPage ;
				}else{
					totalPage = (counter / page.itemPerPage) + 1;
				}
				
				page.setTotalPage(totalPage);
				rs.close();
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	

}
