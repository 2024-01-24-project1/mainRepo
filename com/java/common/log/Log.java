package com.java.common.log;

import java.util.ArrayList;

public final class Log {
	
	private String time;
	private String id;
	private ArrayList<String> action;
	
	public Log(String time, String id, ArrayList<String> action) {
		this.time = time;
		this.id = id;
		this.action = action;
	}
	
	public String getTime() {
		return time;
	}
	public String getId() {
		return id;
	}
	public ArrayList<String> getAction() {
		return action;
	}
	@Override
	public String toString() {
		return String.format("Log [시간: %s, 아이디: %s, 행동: %s]"
							, this.time, this.id, this.action);
	}
	
}//End of class
