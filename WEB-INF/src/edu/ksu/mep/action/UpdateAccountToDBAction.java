package edu.ksu.mep.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.service.AccountService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class UpdateAccountToDBAction extends AbstractAction {

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

		request.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = request.getResponse().getWriter();
		Account acc=(Account)this.getAttributeFromSession(request, "account");//大籃子 裡面有標籤的有給個才會有，開啟瀏覽器就會有大籃子，關掉瀏覽器大籃子就清空
		AccountService service = (AccountService) ServiceLocator.getService("AccountService");
		boolean result = service.updateAccountToDB(acc);// 使用AccountService裡的isValids方法做事

		if (result == true) // 假如有註冊成功就跳到login畫面
		{
			this.setDestination(request, "/mep/UpdateAccountSucc.jsp");// setDestination目的
		} else {
			this.setDestination(request, "/mep/UpdateAccounFail.jsp");
		}
		// this.setDestination(request, "/mep/login.html");//setDestination目的

		this.doView(request);// 會根據我的設定到頁面去

	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		// String a = Global.getUserid();
		// int userid = Integer.parseInt(a);

		int userid = 1;
		String username = request.getValue("username");
		String password = request.getValue("password");
		String uname = request.getValue("uname");
		String email = request.getValue("email");
		String birthday = request.getValue("birthday");

		Account acc = (Account) this.getAttributeFromSession(request, "account");
		acc.setId(userid);
		acc.setUsername(username);
		acc.setPassword(password);
		acc.setUname(uname);
		acc.setBirthday(birthday);
		acc.setEmail(email);

		this.addAttributeToRequest(request, "account", acc);
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
