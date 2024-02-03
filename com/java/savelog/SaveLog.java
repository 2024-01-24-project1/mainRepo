package com.java.savelog;

import java.util.ArrayList;
import java.util.Date;

public class SaveLog {

	static ArrayList<Log> logData = new ArrayList<>();
	
	// 로그 객체 생성[id, action]
	public static void LogAdd(String id, String action){
		
		Log loglist = new Log();
		
		loglist.id = id;
		loglist.action = action;
		
		logData.add(loglist);
	}
	
	// 로그 ArrayList 출력
	public static String LogPrint() {
				
		Date nowTime = new Date(); // 현재 날짜
		String LogAll = nowTime + " " + logData.get(1).id;
		
		for(int i=0; i<logData.size()-1; i++) {
			
			LogAll = LogAll + " " +logData.get(i).action + "-";
		
		}
		
		LogAll = LogAll + " " + logData.get(logData.size()-1).action;
		
		return LogAll; // 현재 날짜 + ID + Action + Action + Action + ..
		
	}
	
	
	
}
