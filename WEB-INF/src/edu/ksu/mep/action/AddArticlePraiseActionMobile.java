package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.Praise;
import edu.ksu.mep.service.PraiseService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class AddArticlePraiseActionMobile extends AbstractAction {

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

		Praise bean = (Praise) this.getAttributeFromSession(request, "priase");
		PraiseService service = (PraiseService)ServiceLocator.getService("PraiseService");

		boolean flag, result;
		//跳掉
		flag = service.Verification(bean);// 驗證
		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		jsonResponse.setTotalRows(0);
		if (flag != true) {
			result = service.addPriase(bean);
			if (result == true) {
				jsonResponse.setSuccess(true);
			} else {
				jsonResponse.setSuccess(false);
			}
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
		Praise bean = new Praise();
		String a = request.getValue("userid");
		int userid = Integer.parseInt(a);
		String b = request.getValue("articleid");
		int articleid = Integer.parseInt(b);
		String dtime = request.getValue("datetime");

		bean.setUserid(userid);
		bean.setArticleid(articleid);
		bean.setDtime(dtime);

		this.addAttributeToSession(request, "priase", bean);
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
