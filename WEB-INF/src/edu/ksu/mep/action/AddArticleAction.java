package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class AddArticleAction extends AbstractAction {

	@Override
	protected void doDelete(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doUpdate(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doView(RuntimeRequest request) throws SQLException,
			IOException {
		// TODO Auto-generated method stub
		
		try {
			String dest = (String) getAttributeFromRequest(request, "destination");
			request.getResponse().sendRedirect(dest); 
		}catch(Exception e){
			System.out.println("Error: Can not doView() in LoginAction");
			e.printStackTrace();
		}

	}

	@Override
	protected Collection getModel(RuntimeRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(RuntimeRequest request) throws SQLException,
			IOException {
		// TODO Auto-generated method stub
		request.getResponse().setCharacterEncoding("utf-8");
		
		ArticleItem add = (ArticleItem) this.getAttributeFromSession(request, "add");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
		boolean result = service.addArticle1(add);
		if(result == true){
			setDestination(request, "/mep/AddArticle.jsp");
		}
		else{
			setDestination(request, "/mep/AddArticlefail.jsp");
		}
		doView(request);
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		
		String title = request.getValue("title");
		String content = request.getValue("content");
		String species = request.getValue("species");
//		String a = Global.getUserid();
//		int userid = Integer.parseInt(a);
			
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date current = new Date(0);
		String datetime = sdFormat.format(current);
		
		ArticleItem add = new ArticleItem();
		add.setTitle(title);
		add.setContent(content);
		add.setSpecies(species);
//		add.setUserid(userid);
		add.setDatetime(datetime);
		
		this.addAttributeToSession(request, "add", add);
		
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
