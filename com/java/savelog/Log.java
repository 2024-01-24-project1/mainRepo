package com.java.savelog;

public class Log {

	String time;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "[time=" + time + ", id=" + id + ", action=" + action + "]";
	}
	
	
	
	
	
}
