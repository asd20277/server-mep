package edu.ksu.mep.service;

import java.util.ArrayList;

import edu.ksu.mep.bean.ArticleMessage;
import edu.ksu.mep.dao.ArticleMessageDAO;
import framework.service.Service;

public class ArticleMessageService extends Service {

	//新增留言
	public boolean addArticleMessage(ArticleMessage mes) {
		// TODO Auto-generated method stub
		boolean result = false;
		ArticleMessageDAO dao = new ArticleMessageDAO();
		result = dao.addArticleMessage(mes);
		return result;
	}
	//叫出留言
	public ArrayList getArticleMessageList(ArticleMessage mes) {
		// TODO Auto-generated method stub
		ArticleMessageDAO dao = new ArticleMessageDAO();
		return dao.getArticleMessageList(mes);
	}
	//刪除留言
	public boolean delArticleMessageToDB(ArticleMessage arm) {
		// TODO Auto-generated method stub
		ArticleMessageDAO dao = new ArticleMessageDAO();
		return dao.delArticleMessageToDb(arm);
	}
	public ArrayList getNumberOfPosts(ArticleMessage mes) {
		// TODO Auto-generated method stub
		ArticleMessageDAO dao = new ArticleMessageDAO();
		return dao.getNumberOfPosts(mes);
	}
	
	

}
