package edu.ksu.mep.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.json.JSONObject;

import edu.ksu.mep.bean.Favorite;
import edu.ksu.mep.service.FavoriteService;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class DelFavoriteToDBActionMobile extends AbstractAction {

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
		
		Favorite favorite = (Favorite)this.getAttributeFromSession(request, "favid");
		FavoriteService service = (FavoriteService)ServiceLocator.getService("FavoriteService");
		
		boolean result = service.delFavorite(favorite);
		
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
		Favorite favorite = new Favorite();
		String a = request.getValue("favid");
		int id = Integer.parseInt(a);
		favorite.setId(id);
		
		this.addAttributeToSession(request, "favid", favorite);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
