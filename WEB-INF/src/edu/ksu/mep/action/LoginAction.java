package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.service.AccountService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class LoginAction extends AbstractAction {

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
			String dest = (String) getAttributeFromRequest(request, "destination");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(dest);
			dispatcher.forward(request.getReq(),request.getResponse());

			
		}catch(Exception e){
			System.out.println("Error: Can not doView() in LoginAction");
			e.printStackTrace();
		}

//		try {
//			String dest = (String) getAttributeFromRequest(request, "destination");
//			request.getResponse().sendRedirect(dest); 
//		}catch(Exception e){
//			System.out.println("Error: Can not doView() in LoginAction");
//			e.printStackTrace();
//		}

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
		//做要做的事
System.out.println("process");
		
		Account bean = (Account) getAttributeFromRequest(request, "account");
		AccountService service = (AccountService) ServiceLocator.getService("AccountService");
		boolean result = service.loginl(bean);
		if(result == true){
			this.addAttributeToSession(request, "account", bean); //代表已經登入
			if(bean.isAdmin()){
				setDestination(request, "/index.jsp");
			}else{
				setDestination(request, "/index.jsp");
			}
		}
		else
			this.setDestination(request, "/loginerror.jsp");	
		doView(request);

	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		//取出網頁上FORM的資料封裝
		String username = request.getValue("username");
		String password = request.getValue("password");
		Account acc = new Account();//封裝所有資料一起傳
		acc.setUsername(username);
		acc.setPassword(password);
		//System.out.print(acct);
		addAttributeToRequest(request, "account", acc);//把封裝的資料丟到籃子裡去貼上"acct"標籤 然後封裝資料是acct
	}

}
