package edu.ksu.mep.bean;

public class ArticleMessage {
	public int id;
	public int articleid;
	public int userid;
	public String message = "";
	public String dtime = "";
	public String uname = "";

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleid() {
		return articleid;
	}

	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
}
