package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Page;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class GetMyArticleListAction extends AbstractAction {

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
		request.getResponse().setContentType("text/html; charset=utf-8");
		Account bean = (Account) getAttributeFromRequest(request, "account");
		HttpSession session = request.getReq().getSession();
		Page page = null;
		page = (Page) session.getAttribute("MyArticleListPageBean");
		if(session.getAttribute("MyArticleListPageBean") == null){
			// 第一次，沒有 page bean
			 page = new Page();
			 page.setCurrentPage(1);
			 int startIndex = (page.getCurrentPage() - 1) * page.itemPerPage;
			 int endIndex = startIndex + page.itemPerPage -1;
			 page.setStartIndex(startIndex);
			 page.setEndIndex(endIndex);
			// 封裝資料
			 ArticleService service = (ArticleService)ServiceLocator.getService("ArticleService");
			ArrayList mylist = service.getMyArticleList1(page);

			page.setData(mylist);
			 session.setAttribute("MyArticleListPageBean", page);
		 }else{
			 int p;
			 if(request.getValue("page")!="")
			 		 p=Integer.parseInt(request.getValue("page"));
			 else
				 p=1;
				 page.setCurrentPage(p);

				 int startIndex = (page.getCurrentPage() - 1) * page.itemPerPage;
				 int endIndex = startIndex + page.itemPerPage -1;
				 page.setStartIndex(startIndex);
				 page.setEndIndex(endIndex);
				// 封裝資料
				 ArticleService service = (ArticleService)ServiceLocator.getService("ArticleService");
				ArrayList mylist = service.getMyArticleList1(page);
				 
				page.setData(mylist);
			 }
		 
		setDestination(request, "/mep/MyArticleListTable.jsp");	// 對的話，傳到主頁
		
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
