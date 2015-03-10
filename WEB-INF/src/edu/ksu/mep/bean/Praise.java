package edu.ksu.mep.bean;

public class Praise {
	public int id;
	public int userid ;
	public int articleid;
	public String dtime = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
}
