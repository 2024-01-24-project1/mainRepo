package com.java.member.employee;

import java.util.ArrayList;

/**
 * 관리자의 행동로그를 저장하는 클래스
 */
public class EmployeeAction {
	
	
	private String time;
	private String id;
	private ArrayList<String> actionList;
	
	public EmployeeAction(String time, String id , ArrayList<String> actionList) {
		
		this.time=time;
		this.id = id;
		this.actionList=actionList;
		
	}
	
	   
	/**
	 * 행동이 마무리되는 메서드 실행후 마지막 setActionList()를 실행하면 행동로그가 저장됨.
	 * @param action
	 */
	public void setActionList(String action) {
		this.actionList.add(action);
	}
	
	
	
	
	
	

}
