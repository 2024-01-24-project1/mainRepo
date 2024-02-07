package com.java.member.employee.log;

import java.util.Scanner;

import com.java.common.Data;
import com.java.view.ViewAll;

public class LogTab {
	
	public static void printLog() {
		
		if(Data.logList.size()==0) {
			System.out.println("행동로그가 존재하지 않습니다.");
		
		}
		
		for(Log log : Data.logList) {
			
			
			String temp = "";
			
			for(int i=0; i<log.getAction().size(); i++) {
				
				
				if(i==log.getAction().size()-1) {
					
					temp += log.getAction().get(i);
					
				}
				
				temp += log.getAction().get(i) + ", ";
				
			}
			
			System.out.printf("날짜    : %s\n"
					        + "아이디  : %s\n"
					        + "행동로그: %s\n",log.getTime(),log.getId(),temp);
			System.out.println();
			
			LogSave.logSave(LogSave.VIEWLOG);
			
		}
		
		
		ViewAll.pause();
		
	}
	
	

}
