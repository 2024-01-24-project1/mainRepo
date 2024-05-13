package com.java.member.employee;

import com.java.member.Member;

// 직원 클래스
/**
 * 직원 정보를 저장하는 클래스
 */
public final class Employee extends Member{

	/**
	 * 직급을 저장하는 멤버 변수
	 */
	private String position;	// 직급
	/**
	 * 근무지(호선)을 저장하는 멤버 변수
	 */
	private String line;		// 호선
	/**
	 * 근무지(역 이름)을 저장하는 멤버 변수
	 */
	private String station;		// 역
	/**
	 * 직원 권한을 저장하는 멤버 변수
	 */
	private String level;		// 권한 1 ~ 3
	
	// 회원가입할때 Employee객체 생성자
	/**
	 * 회원가입 할때 Employee 객체 생성자
	 * @param name 이름
	 * @param id 아이디
	 * @param pw 패스워드
	 * @param registration 주민등록번호
	 * @param phone 전화번호
	 */
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
	/**
	 * 파일 읽고 쓰기 위한 Employee 객체 생성자
	 * @param name 이름
	 * @param id 아이디
	 * @param pw 패스워드
	 * @param registration 주민등록번호
	 * @param phone 전화번호
	 * @param position 직급
	 * @param line 근무지(호선)
	 * @param station 근무지(역 이름)
	 * @param level 직원권한
	 */
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
	
	/**
	 * 직급을 리턴하는 메서드
	 * @return 직급
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 직급을 설정하는 메서드
	 * @param position 직급
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 근무지(호선)을 리턴하는 메서드
	 * @return 근무지(호선)
	 */
	public String getLine() {
		return line;
	}
	/**
	 * 근무지(호선)을 설정하는 메서드
	 * @param line 근무지(호선)
	 */
	public void setLine(String line) {
		this.line = line;
	}
	/**
	 * 근무지(역 이름)을 리턴하는 메서드
	 * @return 근무지(역 이름)
	 */
	public String getStation() {
		return station;
	}
	/**
	 * 근무지(역 이름)을 설정하는 메서드
	 * @param station 근무지(역 이름)
	 */
	public void setStation(String station) {
		this.station = station;
	}
	/**
	 * 권한을 리턴하는 메서드
	 * @return 권한
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 권한을 설정하는 메서드
	 * @param level 권한
	 */
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
