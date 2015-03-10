package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.ArticleMessage;
import edu.ksu.mep.service.ArticleMessageService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class DelArticleMessageToDBActionMobile extends AbstractAction {

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
		ArticleMessage arm = (ArticleMessage)this.getAttributeFromSession(request, "message");
		ArticleMessageService service = (ArticleMessageService)ServiceLocator.getService("ArticleMessageService");
		boolean	result = service.delArticleMessageToDB(arm);
		
		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		jsonResponse.setTotalRows(0);
		
		if (result == true) {
			jsonResponse.setSuccess(true);
		} else {
			jsonResponse.setSuccess(false);
		}
		
		JSONObject json = new JSONObject(jsonResponse);
		String returnValue = json.toString();
		request.getResponse().getWriter().println(returnValue);
		
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		ArticleMessage arm = new ArticleMessage();
		String a = request.getValue("mesid");
		int messageid = Integer.parseInt(a);
		arm.setId(messageid);
		
		this.addAttributeToSession(request, "message", arm);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
