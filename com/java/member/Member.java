package com.java.member;

// 고객과 직원의 공통된 기능을 가지는
// 부모 클래스
/**
 * 고객과 직원의 공통기능을 가지는 Member 부모 클래스
 */
public class Member {

	
	/**
	 * Member클래스의 기본 생성자
	 */
	public Member() {
	}

	/**
	 * 이름을 저장하는 멤버 변수
	 */
	protected String name;			// 이름
	/**
	 * ID를 저장하는 멤버 변수
	 */
	protected String id;			// 아이디
	/**
	 * PW를 저장하는 멤버 변수
	 */
	protected String pw;			// 비밀번호
	/**
	 * 전화번호를 저장하는 멤버 변수
	 */
	protected String phone;			// 전화번호
	/**
	 * 주민등록번호를 저장하는 멤버 변수
	 */
	protected String registration;	// 주민등록번호
	/**
	 * 이름을 리턴하는 메서드
	 * @return 이름
	 */
	public String getName() {
		return name;
	}
	/**
	 * 이름을 설정하는 메서드
	 * @param name 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 아이디를 리턴하는 메서드
	 * @return 아이디
	 */
	public String getId() {
		return id;
	}
	/**
	 * 아이디를 설정하는 메서드
	 * @param id 아이디
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 패스워드를 리턴하는 메서드
	 * @return 패스워드
	 */
	public String getPw() {
		return pw;
	}
	/**
	 * 패스워드를 설정하는 메서드
	 * @param pw 패스워드
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}
	/**
	 * 핸드폰 번호를 리턴하는 메서드
	 * @return 핸드폰 번호
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 핸드폰 번호를 설정하는 메서드
	 * @param phone 핸드폰 번호
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 주민등록번호를 리턴하는 메서드
	 * @return 주민등록번호
	 */
	public String getRegistration() {
		return registration;
	}
	/**
	 * 주민등록번호를 설정하는 메서드
	 * @param registration 주민등록번호
	 */
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	@Override
	public String toString() {
		return String.format("Member [name=%s, id=%s, pw=%s, phone=%s, registration=%s]", name, id, pw, phone,
				registration);
	}
	
	
}//End of class
