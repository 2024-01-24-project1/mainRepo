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
				"User [passCheck=%s, getPassCheck()=%s, getName()=%s, getId()=%s, getPw()=%s, getPhone()=%s, getRegistration()=%s, toString()=%s, getClass()=%s, hashCode()=%s]",
				passCheck, getPassCheck(), getName(), getId(), getPw(), getPhone(), getRegistration(), super.toString(),
				getClass(), hashCode());
	}
	
	
}//End of class
