package com.java.member.employee;

import com.java.common.Data;

public class EmployeeManagement {
	
	private String id;            
	private String pw;
	private String name;
	private String idNum;
	private String phone;
	private String rank;
	private String line;
	private String station;
	private String level;
		
	public EmployeeManagement(String id, String pw, String name, String idNum, String phone, String rank, String line,
			String station, String level) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.idNum = idNum;
		this.phone = phone;
		this.rank = rank;
		this.line = line;
		this.station = station;
		this.level = level;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public EmployeeManagement search(String word) {
		
		for(EmployeeManagement e : Data.employeeManagement) {
			
			if(isExist(word)) {
				
			}
			
			
		}
		
		
		return null;
		
	}
	

	private boolean isExist(String word) {
		
		for(EmployeeManagement e : Data.employeeManagement) {
			
			if(e.getId().equals(word)) {
				return true;
			}else if(e.getName().equals(word)) {
				return true;
			}else if(e.getLine().equals(word)) {
				return true;
			}else if(e.getStation().equals(word)) {
				return true;
			}
			
			
		}
		
		
		
		return false;
	}

	@Override
	public String toString() {
		return String.format(
				"[id=%s, pw=%s, name=%s, idNum=%s, phone=%s, rank=%s, line=%s, station=%s, level=%s]",
				id, pw, name, idNum, phone, rank, line, station, level);
	}
	
	
	
	
	

}
