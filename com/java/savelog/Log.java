package com.java.savelog;

public class Log {

	String id;
	String action;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", action=" + action + "]";
	}
	
	
	
	
}
