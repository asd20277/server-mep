package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import org.json.JSONObject;

import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class GetArticleContentAction extends AbstractAction {

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
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(dest);
			dispatcher.forward(request.getReq(),request.getResponse());

			
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
		Article articleid = (Article) this.getAttributeFromSession(request,"articleid");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
		ArrayList result = service.getArticleContent(articleid);

	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		Article articleid = new Article();
		String a = request.getValue("articleid");
		int id = Integer.parseInt(a);
		articleid.setId(id);
		this.addAttributeToSession(request, "articleid", articleid);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
