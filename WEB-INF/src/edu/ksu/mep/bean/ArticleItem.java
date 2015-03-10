package edu.ksu.mep.bean;

import java.util.ArrayList;

public class ArticleItem {

	public String title = "";
	public String content = "";
	public String uname = "";
	public String id = "";
	public String longitude = "";
	public String latitude = "";
	public String datetime = "";
	public String species = "";
	public String userid = "";
	public int viewers;
	public int nop; //回覆數 numbber of posts
	public int tn; //按讚數 tsan numbber
	public Object message;
	
	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String photoname; //照片名稱
	public String path;//路徑
	
	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
}
