package edu.ksu.mep.service;

import java.util.ArrayList;

import edu.ksu.mep.bean.Favorite;
import edu.ksu.mep.dao.FavoriteDAO;
import framework.service.Service;

public class FavoriteService extends Service {
	//加入收藏
	public boolean addFavorite(Favorite fav) {
		// TODO Auto-generated method stub
		boolean result = false;
		FavoriteDAO dao = new FavoriteDAO();
		result = dao.addFavorite(fav);
		return result;
	}
	//叫出收藏列表
	public ArrayList getFavoritList(Favorite fav) {
		// TODO Auto-generated method stub
		FavoriteDAO dao = new FavoriteDAO();
		return dao.getFavoriteList(fav);
	}
	//驗證
	public boolean Verification(Favorite fav) {
		// TODO Auto-generated method stub
		FavoriteDAO dao = new FavoriteDAO();
		return dao.Verification(fav);
	}
	//刪除收藏
	public boolean delFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		FavoriteDAO dao = new FavoriteDAO();
		return dao.delFavorite(favorite);
	}
	
	
}
