package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class GetArticleContentActionMobile extends AbstractAction {

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
		request.getResponse().setCharacterEncoding("utf-8");
		Article articleid = (Article) this.getAttributeFromSession(request,"articleid");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
		ArrayList result = service.getArticleContent(articleid);

		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		// 設定資料
		jsonResponse.setTotalRows(0);
		jsonResponse.setSuccess(true);
		jsonResponse.setData(result);
		jsonResponse.setMessage("ArticleContent");
		// 變成json格式
		JSONObject f = new JSONObject(jsonResponse);
		// 轉換字串
		String returnValue = f.toString();
		System.out.print(returnValue);
		// 發送資料
		request.getResponse().getWriter().print(returnValue);

	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		Article articleid = new Article();
		String a = request.getValue("articleid");
		articleid.setId(Integer.parseInt(a));
		this.addAttributeToSession(request, "articleid", articleid);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
