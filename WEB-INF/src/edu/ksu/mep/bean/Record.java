package edu.ksu.mep.bean;

public class Record {
	
	public String id = "";
	public String userid = "";
	public String title = "";
	public String recordname = "";
	public String species = "";
	public String latitude = "";
	public String longitude = "";
	public String datetime = "";
	public String uname = "";
	public int viewers;
	public int getViewers() {
		return viewers;
	}
	public void setViewers(int viewers) {
		this.viewers = viewers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
