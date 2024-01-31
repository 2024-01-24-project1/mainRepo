package mainRepo.com.java.member.employee;

import mainRepo.com.java.member.Member;

public class Employee extends Member{

	private String position;	// 직급
	private String line;		// 호선
	private String station;		// 역
	private String level;		// 권한 1 ~ 3
	
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
		return String.format("Employee [position=%s, line=%s, station=%s, level=%s]", position, line, station, level);
	}
	
	
}//End of class
