package edu.ksu.mep.action;


/**
 * Class to be used for returning data back to the client.
 * 
 * Data should be set on this object then serialized into JSON format
 * to be returned to the client.
 * 
 * @author nvujasin
 */
public class JsonObjectResponse {
	
	private int totalRows;
	private boolean admin;
	private boolean success;
	private Object data;
	private String message;
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
