package edu.ksu.mep.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class AddArticleWithoutPhotoActionMobile extends AbstractAction {
	         
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
		
		HttpSession session = request.getReq().getSession();
		request.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = request.getResponse().getWriter();
//		
		request.getResponse().setCharacterEncoding("utf-8");
		ArticleItem add = (ArticleItem) this.getAttributeFromSession(request, "add");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
		boolean result = service.addArticle(add);
		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		jsonResponse.setTotalRows(0);
		
		if (result == true) {
			jsonResponse.setSuccess(true);
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
		request.getReq().setCharacterEncoding("utf-8");
		ArticleItem add = new ArticleItem();
		
		String title = request.getValue("title");
		String content = request.getValue("content");
		String userid =  request.getValue("userid");
		String longitude = request.getValue("longitude");
		String latitude = request.getValue("latitude");
		String datetime = request.getValue("datetime");
		String species = request.getValue("species");
		int v = 0;
		add.setTitle(title);
		add.setContent(content);
		add.setUserid(userid);
		add.setLongitude(longitude);
		add.setLatitude(latitude);
		add.setDatetime(datetime);
		add.setSpecies(species);
		add.setViewers(v);
		this.addAttributeToSession(request, "add", add);
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
