package edu.ksu.mep.service;

import java.util.ArrayList;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.bean.Page;
import edu.ksu.mep.dao.AccountDAO;
import edu.ksu.mep.dao.ArticelDAO;
import framework.service.Service;

public class ArticleService extends Service {

	// 新增文章沒有照片
	public boolean  addArticle(ArticleItem add) {
		// TODO Auto-generated method stub
		boolean result = false;
		ArticelDAO dao = new ArticelDAO();
		result = dao.addArticle(add);
		// System.out.print(result);
		return result;
	}
	
	// 新增文章
	public boolean  addArticleWithPhoto(ArticleItem add) {
		// TODO Auto-generated method stub
		boolean result = false;
		ArticelDAO dao = new ArticelDAO();
		result = dao.addArticle(add);
		// System.out.print(result);
		return result;
	}

	// 叫出自己文章列表
	public ArrayList getMyArticleList(ArticleItem mylist) {
		ArticelDAO dao = new ArticelDAO();
		return dao.getMyArticleList(mylist);
	}


	// 顯示文章內容
	public ArrayList getArticleContent(Article articleid) {
		ArticelDAO dao = new ArticelDAO();
		return dao.getArticleContent(articleid);

	}

	// 刪除文章
	public boolean delMyArticleTODB(Article delid) {
		ArticelDAO dao = new ArticelDAO();
		return dao.delMyArticleTODB(delid);
	}

	// 搜尋文章列表
	public ArrayList getSearchList(ArticleItem art) {
		ArticelDAO dao = new ArticelDAO();
		return dao.getSearchList(art);
	}

	// 拿取所有經緯度
	public ArrayList getAllLongitudeAndLatitude(ArticleItem az) {
		// TODO Auto-generated method stub
		ArticelDAO dao = new ArticelDAO();
		return dao.getAllLongitudeAndLatitude(az);
	}

	// 叫出最新文章
	public ArrayList getNewArticleList(ArticleItem ar) {
		// TODO Auto-generated method stub
		ArticelDAO dao = new ArticelDAO();
		return dao.getNewArticleList(ar);
	}
	//觀看人數+1
	public boolean addViewers(ArticleItem arm) {
		// TODO Auto-generated method stub
		ArticelDAO dao = new ArticelDAO();
		return dao.addViewers(arm);
	}
	//抓取單一文章經緯度
	public ArrayList getArticleLongitudeAndLatitude(ArticleItem arm) {
		// TODO Auto-generated method stub
		ArticelDAO dao = new ArticelDAO();
		return dao.getArticleLongitudeAndLatitude(arm);
	}
	// 叫出熱門文章
	public ArrayList getHotArticleList(ArticleItem article) {
		// TODO Auto-generated method stub
		ArticelDAO dao = new ArticelDAO();
		return dao.getHotArticleList(article);
	}
	//拿取照片名稱
	public ArticleItem getArticlePhoto(ArticleItem photo) {
		// TODO Auto-generated method stub
		ArticelDAO dao = new ArticelDAO();
		return dao.getArticlePhoto(photo);
	}
	
	
	
	
	public boolean  addArticle1(ArticleItem add) {
		// TODO Auto-generated method stub
		boolean result = false;
		ArticelDAO dao = new ArticelDAO();
		result = dao.addArticle1(add);
		// System.out.print(result);
		return result;
	}
	public ArrayList getAllArticleList1(Article alllist) {
		ArticelDAO dao = new ArticelDAO();
		return dao.getAllArticleList1(alllist);
	}
	public ArticleItem getArticleContent1(Article articleid) {
		ArticelDAO dao = new ArticelDAO();
		return dao.getArticleContent1(articleid);

	}
	public ArrayList getMyArticleList1(Page page) {
		ArticelDAO dao = new ArticelDAO();
		return dao.getMyArticleList1(page);
	}
	
	

}
