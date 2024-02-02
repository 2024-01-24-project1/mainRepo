package com.java.member.employee;

import com.java.member.Member;

// 직원 클래스
public final class Employee extends Member{

	private String position;	// 직급
	private String line;		// 호선
	private String station;		// 역
	private String level;		// 권한 1 ~ 3
	
	// 회원가입할때 Employee객체 생성자
	public Employee(String name, String id, String pw, String registration, String phone) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.position = "일반";
		this.line = "미정";
		this.station = "미정";
		this.level = "2";
	}
	
	// csv파일 읽고 쓰기위한 Employee객체 생성자
	public Employee(String name, String id, String pw, String registration, String phone
					, String position, String line, String station, String level) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.position = position;
		this.line = line;
		this.station = station;
		this.level = level;
		
	}
	
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	
	@Override
	public String toString() {
		return String.format("[이름: %s, ID: %s, PW: %s, 주민등록번호: %s, 전화번호: %s, 직급: %s, 호선: %s, 역: %s, 접근권한: %s]\r\n"
							, this.name, this.id, this.pw, this.registration, this.phone
							, this.position, this.line, this.station, this.level);
	}
	
	
}//End of class
