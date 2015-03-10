package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class GetArticleLongitudeAndLatitudeAction extends AbstractAction {

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
		request.getResponse().setCharacterEncoding("utf-8");
		
		ArticleItem arm = (ArticleItem)this.getAttributeFromSession(request, "articleid");
		ArticleService service = (ArticleService)ServiceLocator.getService("ArticleService");
		ArrayList result = service.getArticleLongitudeAndLatitude(arm);
		if(result != null){
			setDestination(request, "/mep/ArticleLongitudeAndLatitudeTable.jsp");
			}
		else{
			setDestination(request, "/fgps/AddProductDataFail.jsp");
		
		}
		doView(request);
		
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		String a = request.getValue("articleid");
		ArticleItem arm = new ArticleItem();
		arm.setId(a);
		this.addAttributeToSession(request, "articleid", arm);
		

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
