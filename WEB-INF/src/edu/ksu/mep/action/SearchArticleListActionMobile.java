package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.Article;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class SearchArticleListActionMobile extends AbstractAction {

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
		String name = (String) this.getAttributeFromSession(request, "name");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
//		ArrayList result = service.searcharticlelist(name);

		JsonObjectResponse jsonResponse = new JsonObjectResponse();

//		jsonResponse.setTotalRows(0);
//		jsonResponse.setSuccess(true);
//		jsonResponse.setData(result);
//		// System.out.print(result);
//		jsonResponse.setMessage("Searcharticlelist");
//		// 變成json格式
//		JSONObject f = new JSONObject(jsonResponse);
//		// 轉換字串
//		String returnValue = f.toString();
//		// System.out.print(returnValue);
//		// 發送資料
//		request.getResponse().getWriter().print(returnValue);

	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		// Article alllist = new Article();
		String name = request.getValue("sear");

		this.addAttributeToSession(request, "name", name);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
