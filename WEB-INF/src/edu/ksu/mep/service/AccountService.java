package edu.ksu.mep.service;

import java.util.ArrayList;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.dao.AccountDAO;
import framework.service.Service;

public class AccountService extends Service {
	
	//登入android
	public Account login(Account acct){
		AccountDAO dao = new AccountDAO();
		return dao.login(acct);			
	}
	
	//註冊
	public boolean regis(Account regis) {
		// TODO Auto-generated method stub
		boolean result =false;
		AccountDAO dao = new AccountDAO();
		result = dao.regis(regis);
        return result;
	}
		
	//修改資料TODB
	public boolean updateAccountToDB(Account updateAccountToDB){
		AccountDAO dao = new AccountDAO();
        return dao.UpdateAccountToDB(updateAccountToDB);	
	}

	//關於我
	public Account getAccountData(Account ac) {
		// TODO Auto-generated method stub
		AccountDAO dao = new AccountDAO();
		return dao.getAccountData(ac);
	}
	
	//關於我
	public Account getAboutMe(Account acc) {   
		boolean result = false;
		AccountDAO dao = new AccountDAO();
		return dao.getAboutMe(acc);
	}
	
	//登入web
	public boolean loginl(Account bean) {
		AccountDAO dao = new AccountDAO();
		return dao.isValid(bean);
	} 
	//修改資料
	public ArrayList getAccount(Account acc) {   
		boolean result = false;
		AccountDAO dao = new AccountDAO();
		return dao.getAccount(acc);
	}

	//驗證
	public boolean Verification(Account regis) {
		// TODO Auto-generated method stub
		AccountDAO dao = new AccountDAO();
		return dao.Verification(regis);	
	}

}
