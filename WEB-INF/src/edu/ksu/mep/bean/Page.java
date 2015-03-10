package edu.ksu.mep.bean;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class Page {
	public int totalPage = 0;
	public int currentPage = 0;
	public int startIndex = 0;
	public int endIndex = 0;
	public final int itemPerPage = 12;
	
	public ArrayList data = new ArrayList();
	
	public void setPageInfo(ArrayList studyinformation, HttpSession session){
		Page p = (Page) session.getAttribute("StudyInformationIndexPageObj");
		//<!-- Begin: 顯示分頁項目需要此資訊 -->
		int itemPerPage = 10;
		int totalPage = 1;
		if(studyinformation.size() % itemPerPage ==0){
			totalPage = studyinformation.size() / itemPerPage ;
		}else{
			totalPage = (studyinformation.size() / itemPerPage) + 1;
		}
		p.setTotalPage(totalPage);
		int cpage = p.getCurrentPage();
		int startIndex = (cpage -1) * itemPerPage;
		p.setStartIndex(startIndex);
		int endIndex = 0;
		if(cpage == totalPage){
			endIndex = studyinformation.size();
		}else{
			endIndex = startIndex + itemPerPage;
		}
		p.setEndIndex(endIndex);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public ArrayList getData() {
		return data;
	}

	public void setData(ArrayList data) {
		this.data = data;
	}
}
