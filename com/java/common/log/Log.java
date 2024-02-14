package com.java.common.log;

import java.util.ArrayList;

/**
 * 행동 로그를 저장하는 클래스
 */
public final class Log {
	
	/**
	 * 시간을 저장하는 멤버 변수
	 */
	private String time;
	/**
	 * 직원 id를 저장하는 멤버 변수
	 */
	private String id;
	/**
	 * 직원 행동로그를 저장하는 ArrayList
	 */
	private ArrayList<String> action;
	
	/**
	 * 행동로그 클래스의 생성자
	 * @param time 시간
	 * @param id 직원 id
	 * @param action 행동 로그
	 */
	public Log(String time, String id, ArrayList<String> action) {
		this.time = time;
		this.id = id;
		this.action = action;
	}
	
	/**
	 * 시간을 리턴하는 메서드
	 * @return 시간 String
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 직원 id를 리턴하는 메서드
	 * @return id String
	 */
	public String getId() {
		return id;
	}
	/**
	 * 행동 로그를 리턴하는 메서드
	 * @return 행동로그 ArrayList of String
	 */
	public ArrayList<String> getAction() {
		return action;
	}
	
	/**
	 * 행동 로그를 저장하는 메서드
	 * @param action 행동
	 */
	public void setAction(String action) {
		this.action.add(action);
	}

	@Override
	public String toString() {
		return String.format("Log [시간: %s, 아이디: %s, 행동: %s]"
							, this.time, this.id, this.action);
	}
	
}//End of class
