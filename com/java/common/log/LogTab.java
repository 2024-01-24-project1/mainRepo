package com.java.common.log;

import java.util.Scanner;

import com.java.common.Data;

public class LogTab {
	
	public static void printLog() {
		
		
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
		}
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();
		
		
	}
	
	

}
