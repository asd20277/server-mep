package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.service.AccountService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class RegisterActionMobile extends AbstractAction {

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
			String dest = (String) getAttributeFromRequest(request,
					"destination");
			request.getResponse().sendRedirect(dest);
		} catch (Exception e) {
			System.out.println("Error: Can not doView() in RegisterAction");
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
		Account regis = (Account) this.getAttributeFromSession(request, "regis");
		AccountService service = (AccountService) ServiceLocator.getService("AccountService");

		boolean flag, result;

		flag = service.Verification(regis);// 驗證
		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		jsonResponse.setTotalRows(0);
		if (flag != true) {
			result = service.regis(regis);// 如果沒有重複的話，註冊
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
		// 取出網頁上FORM的資料封裝
		request.getReq().setCharacterEncoding("utf-8");
		Account regis = new Account();// 封裝所有資料一起傳
		String username = request.getValue("username");
		String password = request.getValue("password");
		String email = request.getValue("email");
		String uname = request.getValue("uname");
		String birthday = request.getValue("birthday");

		regis.setUsername(username);// 把資料設定進去
		regis.setPassword(password);
		regis.setEmail(email);
		regis.setUname(uname);
		regis.setBirthday(birthday);

		this.addAttributeToSession(request, "regis", regis);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
