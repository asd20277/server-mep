package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


import edu.ksu.mep.bean.Account;
import edu.ksu.mep.service.AccountService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class LoginoutAction extends AbstractAction {
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
		try{
			String dest = (String) this.getAttributeFromRequest(request, "destination");
			request.getResponse().sendRedirect(dest);
		}catch(Exception e){
			
		}
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
		Account bean = (Account) getAttributeFromRequest(request, "account");
		this.removeAttributeToSession(request, "account", bean);
		setDestination(request, "/mep/index.jsp");
		doView(request);
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {


	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
