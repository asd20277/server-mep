package edu.ksu.mep.bean;

public class Favorite {
	public int id;
	public String userid = "";
	public int articleid;
	public String title = "";
	public String uname = "";
	public String dtime = "";
	public String content = "";
	public String species = "";
	public int viewers;
	public int nop; //回覆數 numbber of posts
	public int tn; //按讚數 tsan numbber
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getNop() {
		return nop;
	}

	public void setNop(int nop) {
		this.nop = nop;
	}

	public int getTn() {
		return tn;
	}

	public void setTn(int tn) {
		this.tn = tn;
	}

	public int getViewers() {
		return viewers;
	}

	public void setViewers(int viewers) {
		this.viewers = viewers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
}
