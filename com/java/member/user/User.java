package com.java.member.user;

import com.java.member.Member;

public final class User extends Member{

	private String passCheck;	// 정기권유무
	private String passExpiry;	// 정기권 기간 
	
	public User(String name, String id, String pw, String registration, String phone) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.passCheck = "없음";
		this.passExpiry = "없음";
		
	}
	
	public User(String name, String id, String pw, String registration, String phone, String passCheck, String passExpiry) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.passCheck = passCheck;
		this.passExpiry = passExpiry;
	}

	public String getPassCheck() {
		return passCheck;
	}

	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
	}

	public String getPassExpiry() {
		return passExpiry;
	}

	public void setPassExpiry(String passExpiry) {
		this.passExpiry = passExpiry;
	}

	@Override
	public String toString() {
		return String.format(
				"User [이름: %s, ID: %s, PW: %s, 주민등록번호: %s, 전화번호: %s, 정기권여부: %s, 정기권기간: %s]\n"
				,this.name, this.id, this.pw, this.registration, this.phone
				, this.passCheck, this.passExpiry );
	}
	
	
}//End of class
