package com.java.savelog;

import java.util.ArrayList;
import java.util.Date;

public class SaveLog {
	
	public static void main(String[] args) {
		
		LogAdd("임시 아이디", "임시 행동로그1 - 행동로그 - 행동로그 123", "임시 날짜");
		LogAdd("임시 아이디", "임시 행동로그2 - 행동로그", "임시 날짜");
		LogAdd("임시 아이디", "임시 행동로그3", "임시 날짜3");
		LogAdd("임시 아이디", "임시 행동로그4", "임시 날짜4");
		LogAdd("임시 아이디", "임시 행동로그5 - 행동로그", "임시 날짜5");
		
		System.out.println(LogPrint());
		
	}

	static ArrayList<Log> logData = new ArrayList<>();
	
	// 로그 객체 생성[id, action]
	public static void LogAdd(String id, String action){
		
		Log loglist = new Log();
		Date nowTime = new Date();
		
		loglist.time = nowTime + "";
		loglist.id = id;
		loglist.action = action;
		
		logData.add(loglist);
	}
	
	// 로그 객체 생성[id, action, date]
	public static void LogAdd(String id, String action, String date){
		
		Log loglist = new Log();
		
		loglist.time = date;
		loglist.id = id;
		loglist.action = action;
		
		logData.add(loglist);
	}
	
	// 로그 ArrayList 출력
	public static String LogPrint() {
		
		String LogList = "";
		
		for(int i=0; i<logData.size()-1; i++) {
			
			if(i==0) {
				LogList += logData.get(0).time + " ";
			}
			
			LogList += logData.get(i).action + " ";
			
			if(!logData.get(i+1).time.equals(logData.get(i).time)) {
				LogList += "\n" + logData.get(i+1).time + " ";  
			}
			
		}
		
		LogList = LogList      + logData.get(logData.size()-1).action;
		
		return LogList; // 현재 날짜 + ID + Action + Action + Action + ..
		
	}
	
	
	
}
