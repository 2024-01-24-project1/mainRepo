package com.java.common.log;

import java.util.ArrayList;

import com.java.common.Data;
import com.java.view.ViewAll;

public class LogTab {
	
	public static void printLog() {
		
		ArrayList<String> logPage = new ArrayList<>();
		
		for(Log log : Data.logList) {
			
			
			String temp = "";
			
			for(int i=0; i<log.getAction().size(); i++) {
				
				
				if(i==log.getAction().size()-1) {
					
					temp += log.getAction().get(i);
					
				}
				
				temp += log.getAction().get(i) + ", ";
				
			}
			
			System.out.printf("\t날짜    : %s\n"
					        + "\t아이디  : %s\n"
					        + "\t행동로그: %s\n",log.getTime(),log.getId(),temp);
			System.out.println();
		}
		
		LogSave.logSave(LogSave.VIEWLOG);
		ViewAll.pause();
		
		
	}
	
	

}
