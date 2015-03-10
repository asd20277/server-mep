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

public class LoginActionMobile extends AbstractAction {

	@Override
	protected void doUpdate(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDelete(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Collection getModel(RuntimeRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
			System.out.println("Error: Can not doView() in LoginAction");
			e.printStackTrace();
		}

	}

	@Override
	protected String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(RuntimeRequest request) throws SQLException,
			IOException {
		// TODO Auto-generated method stub
		// 做要做的事
		request.getResponse().setCharacterEncoding("utf-8");
		Account account = (Account) this.getAttributeFromSession(request, "acct");// 把剛剛封裝丟到籃子裡的資料拿出來
		AccountService serivce = (AccountService) ServiceLocator
				.getService("AccountService"); // 拿SERVICE叫他做事
		Account result = serivce.login(account);// 叫service去驗證ac的帳密~驗證完之後告訴我對或錯

		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		jsonResponse.setTotalRows(0);
		jsonResponse.setData(result);

		if (result.getId() != 0) {
			jsonResponse.setSuccess(true);
			jsonResponse.setMessage("Sucess");
		} else {
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage("Fail");
		}

		JSONObject json = new JSONObject(jsonResponse);
		String returnValue = json.toString();

		System.out.print(returnValue);
		request.getResponse().getWriter().println(returnValue);

	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		// 取出網頁上FORM的資料封裝
		request.getReq().setCharacterEncoding("utf-8");
		String username = request.getValue("username");
		String password = request.getValue("password");
		Account account = new Account();// 封裝所有資料一起傳
		account.setUsername(username);
		account.setPassword(password);
		// System.out.print(acct);
		this.addAttributeToSession(request, "acct", account);// 把封裝的資料丟到籃子裡去貼上"acct"標籤
		// 然後封裝資料是acct
	}

}
