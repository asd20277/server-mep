package edu.ksu.mep.service;

import edu.ksu.mep.bean.Praise;
import edu.ksu.mep.dao.PraiseDAO;
import framework.service.Service;

public class PraiseService extends Service {

	// 驗證
	public boolean Verification(Praise bean) {
		// TODO Auto-generated method stub
		PraiseDAO dao = new PraiseDAO();
		return dao.Verification(bean);
	}

	// 加入讚
	public boolean addPriase(Praise bean) {
		// TODO Auto-generated method stub
		PraiseDAO dao = new PraiseDAO();
		return dao.addPriase(bean);
	}

	// 收回讚
	public boolean delPraise(Praise bean) {
		// TODO Auto-generated method stub
		PraiseDAO dao = new PraiseDAO();
		return dao.delPraise(bean);
	}

}
