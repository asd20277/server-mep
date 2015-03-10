package edu.ksu.mep.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.service.AccountService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class RegisterAction extends AbstractAction {

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
		request.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = request.getResponse().getWriter();
		Account acc=(Account)request.getReq().getAttribute("account");
		AccountService serivce = (AccountService) ServiceLocator.getService("AccountService"); //拿SERVICE叫他做事
		boolean result = serivce.regis(acc);//使用AccountService裡的isValids方法做事
				if(result==true)  //假如有註冊成功就跳到login畫面
				{
					this.setDestination(request, "/mep/register.jsp");//setDestination目的
				}  		
		this.doView(request);//會根據我的設定到頁面去
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		//取出網頁上FORM的資料封裝
		Account acc = new Account();//封裝所有資料一起傳
		String username = request.getValue("username");
		String password = request.getValue("password");
		String email = request.getValue("email");
		String uname = request.getValue("u_name");
		String birthday = request.getValue("u_birthday");
		
		acc.setUsername(username);//把資料設定進去
		acc.setPassword(password);
		acc.setEmail(email);
		acc.setUname(uname);
		acc.setBirthday(birthday);
		/*System.out.print(regis.username);
		System.out.print(regis.password);
		System.out.print(regis.sno);
		System.out.print(regis.uname);
		System.out.print(regis.birthday);*/
		
		this.addAttributeToRequest(request, "account", acc);//把封裝的資料丟到籃子裡去貼上"regis"標籤 然後封裝資料是regis
		//System.out.print(regis);
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
