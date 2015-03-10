package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.service.AccountService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class UpdateAccountAction extends AbstractAction {

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

		Account acc= (Account) this.getAttributeFromSession(request, "account");//把剛剛封裝丟到籃子裡的資料拿出來
		AccountService serivce = (AccountService) ServiceLocator.getService("AccountService"); //拿SERVICE叫他做事
		ArrayList result = serivce.getAccount(acc);//使用AccountService裡的isValids方法做事
		request.getReq().setAttribute("acc", result);
		if(result!=null){
			this.setDestination(request, "/UpdateAccountTable.jsp");
		}else
		{
			this.setDestination(request, "/InformationError.jsp");
		}
		
		doView(request);
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
//		String a = Global.getUserid();
//		int userid = Integer.parseInt(a);
//		String a = request.getValue("id");
//		int userid = Integer.parseInt(a);
		int userid = 1;
		Account acc = new Account();
		acc.setId(userid);
		
		this.addAttributeToSession(request, "account", acc);
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
