package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import edu.ksu.mep.bean.Article;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class DelMyArticleToDBAction extends AbstractAction {

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
		Article delid = (Article) this.getAttributeFromSession(request, "id");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
		boolean result = service.delMyArticleTODB(delid);
		if(result == true){
			setDestination(request, "/mep/DelMyArticleSucc.jsp");
		}
		else{
			setDestination(request, "/mep/DelMyArticleFail.jsp");
		}
		doView(request);
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		String a = request.getValue("id");
		int artid = Integer.parseInt(a);
		Article delid = new Article();
		delid.setId(artid);
		this.addAttributeToSession(request, "id", delid);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
