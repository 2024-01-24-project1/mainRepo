package com.java.member.employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//id id 


//auth 관리자/고객
//level 접근 권한

public class ActionLogOut {
	
	ArrayList<String> ActionLog = new ArrayList<String>(); //행동 로그 누적 저장해놓은 arraylist
	
	
	
	
	
	public static void nowTime() {
		Calendar now=Calendar.getInstance();
		System.out.printf("%d-%02d-%02d",now.get(Calendar.YEAR)
									,now.get(Calendar.MONTH)+1
									,now.get(Calendar.DAY_OF_MONTH));	
	}
	
	public static 
}

//class Action{
//	private String date;
//	private String id;
//	private List<String> ActionLog;
//	
//	
//	public String getDate() {
//		Calendar now=Calendar.getInstance();
//		System.out.printf("%d-%02d-%02d",now.get(Calendar.YEAR)
//									,now.get(Calendar.MONTH)+1
//									,now.get(Calendar.DAY_OF_MONTH));
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
//
//	public List<String> getActionLog() {
//		return ActionLog;
//	}
//	public void setActionLog(List<String> actionLog) {
//		ActionLog = actionLog;
//	}
//	
//}