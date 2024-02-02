package com.java.member.employee;

import java.util.ArrayList;

import com.java.common.Data3;
/**
 * 관리자의 정보를 저장하는 클래스
 */
public class EmployeeManagement {
	
	private String id;       		//관리자 ID     
	private String pw;				//관리자 PW
	private String name;			//관리자 이름
	private String idNum;  			//관리자 주민등록번호
	private String phone;   		//관리자 핸드폰 번호
	private String rank;			//관리자 직급
	private String line;			//관리자 호선
	private String station;			//관리자 근무지
	private String level;			//관리자 접근권한
		
	
	/**
	 * 관리자 생성자
	 * @param id 아이디
	 * @param pw 패스워드
	 * @param name 이름
	 * @param idNum 주민등록번호
	 * @param phone 휴대폰 번호
	 * @param rank 직급
	 * @param line 근무지(호선)
	 * @param station 근무지(역)
	 * @param level 접근권한
	 */
	public EmployeeManagement(String id, String pw, String name, String idNum, String phone, String rank, String line,
			String station, String level) {
		
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
	

	/**
	 * 관리자정보가 들어있는 ArrayList에서 매개변수 word가 포함된 객체를 
	 * ArrayList에 저장해서 리턴해주는 메서드
	 * @param word 찾고싶은 검색어
	 * @return 매개변수가 포함된 객체를 저장한 ArrayList
	 */
	public static ArrayList<EmployeeManagement> search(String word) {
		
		ArrayList<EmployeeManagement> list = new ArrayList<>();

		for(EmployeeManagement e : Data3.employeeList) {

			if(isExist(e,word)) {

				list.add(e);

			}


		}
		
		return list;


	}

	/**
	 * 매개변수 word를 검색해서 파일에 저장된 관리자 정보와 일치하는지 확인하는 메서드
	 * @param e 관리자 객체
	 * @param word 검색어
	 * @return word 매개변수중 근무지(역), 이름, 호선, 직급중 하나라도 일치하면 true를 리턴
	 */
	private static boolean isExist(EmployeeManagement e, String word) {

		if(e.getStation().equals(word)) {
			return true;
		}else if(e.getName().equals(word)) {
			return true;
		}else if(e.getLine().equals(word)) {
			return true;
		}else if(e.getRank().equals(word)) {
			return true;
		}



		return false;
	}

	
	
	

	@Override
	public String toString() {
		return String.format(
				"[이름: %s, 아이디: %s, 전화번호: %s, 직급: %5s, 호선: %s, 역: %5s, 접근권한: %s]",
				name, id, phone, rank, line, station, level);
	}
	
	
	
	
	

}
