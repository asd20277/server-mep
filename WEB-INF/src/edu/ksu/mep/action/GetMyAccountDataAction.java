package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.RequestDispatcher;

import edu.ksu.mep.service.AccountService;
import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class GetMyAccountDataAction extends AbstractAction {

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
			System.out.println("Error: Can not doView() in AddAssociatorAction");
			e.printStackTrace();}
		
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
		
		Account acc = (Account) this.getAttributeFromSession(request, "account");//account是我們給他取的標籤名子，程式會藉由標籤拿東西
		AccountService service = (AccountService) ServiceLocator.getService("AccountService");//大袋子
		acc = service.getAboutMe(acc);   
		this.addAttributeToSession(request, "account", acc);
		setDestination(request, "/mep/aboutMe.jsp");	
		
		doView(request);
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
