package com.java.member.user;

import com.java.member.Member;

/**
 * 고객의 정보를 저장하는 클래스
 */
public final class User extends Member{

	/**
	 * 정기권 유무를 저장하는 멤버 변수
	 */
	private String passCheck;	// 정기권유무
	/**
	 * 정기권 기간을 저장하는 멤버 변수
	 */
	private String passExpiry;	// 정기권 기간 
	
	/**
	 * 회원가입할때 사용하는 User 객체 생성자
	 * @param name 이름
	 * @param id 아이디
	 * @param pw 패스워드
	 * @param registration 주민등록번호
	 * @param phone 전화번호
	 */
	public User(String name, String id, String pw, String registration, String phone) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.passCheck = "없음";
		this.passExpiry = "없음";
		
	}
	/**
	 * 파일 읽고 쓰기할때 사용하는 User 객체 생성자
	 * @param name 이름
	 * @param id 아이디
	 * @param pw 패스워드
	 * @param registration 주민등록번호
	 * @param phone 전화번호
	 * @param passCheck 정기권유무
	 * @param passExpiry 정기권기간
	 */
	public User(String name, String id, String pw, String registration, String phone, String passCheck, String passExpiry) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.passCheck = passCheck;
		this.passExpiry = passExpiry;
	}
	
	/**
	 * 정기권 유무 리턴하는 메서드
	 * @return 정기권 유무
	 */
	public String getPassCheck() {
		return passCheck;
	}
	/**
	 * 정기권 유무 설정하는 메서드
	 * @param passCheck 정기권 유무
	 */
	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
	}
	/**
	 * 정기간 기간 리턴하는 메서드
	 * @return 정기권 기간
	 */
	public String getPassExpiry() {
		return passExpiry;
	}
	/**
	 * 정기권 기간 설정하는 메서드
	 * @param passExpiry 정기권 기간
	 */
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
