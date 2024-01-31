package mainRepo.com.java.member.user;

import mainRepo.com.java.member.Member;

public class User extends Member{

	private String passCheck;	// 정기권유무
	
	public User(String name, String id, String pw, String registration, String phone) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.passCheck = "없음";
		
	}
	
	public User(String name, String id, String pw, String registration, String phone, String passCheck) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.registration = registration;
		this.phone = phone;
		this.passCheck = passCheck;
	}

	public String getPassCheck() {
		return passCheck;
	}

	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
	}

	@Override
	public String toString() {
		return String.format(
				"User [이름: %s, ID: %s, PW: %s, 주민등록번호: %s, 전화번호: %s, 정기권여부: %s]\n"
				,this.name, this.id, this.pw, this.registration, this.phone, this.passCheck );
	}
	
	
}//End of class
