package edu.ksu.mep.service;

import java.util.ArrayList;

import edu.ksu.mep.bean.Record;
import edu.ksu.mep.dao.RecordDAO;
import framework.service.Service;

public class RecordService extends Service {
	
	//新增Record
	public boolean addRecord(Record record) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		return dao.addRecord(record);
	}

	//我的Record列表
	public ArrayList getMyRecordList(Record record) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		return dao.getMyRecordList(record);
	}

	//最新Record列表
	public ArrayList getNewRecordList(Record record) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		return dao.getNewRecordList(record);
	}

	//熱門Record列表
	public ArrayList getHotRecordList(Record record) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		return dao.getHotRecordList(record);
	}
	//刪除Record
	public boolean delRecord(Record record) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		return dao.delRecord(record);
	}
	// 觀看人數+1
	public boolean addRecordViewers(Record record) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		return dao.addRecordViewers(record);
	}

}
