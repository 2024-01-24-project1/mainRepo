package com.java;


import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Exit;
import com.java.common.FindAccount;
import com.java.common.Load;
import com.java.common.LoginLogout;
import com.java.common.SignUp;
import com.java.common.Validation;
import com.java.member.employee.stats.Graph;
import com.java.member.employee.stats.Stats;
import com.java.station.timetable.StationTime;
import com.java.view.ViewAll;



public class Main {
	
	public static void main(String[] args) {
		
		Load load = new Load();
		Exit exit = new Exit();
		
		LoginLogout loginlogout = new LoginLogout();
		
		// 데이터 로드
		load.loadAll();
		
		Scanner sc = new Scanner(System.in);
		boolean loop= true;
		
		String t = "01029053433";
		System.out.println(t);
		
		t = SignUp.formatPhoneNumber(t);
		System.out.println(t.length());
		System.out.println(t);
		
		while(loop) {
			
			ViewAll.realMain();
			String sel = ""; 
			sel = sc.nextLine();
			
			
			if(sel.equals("1")) {
				
				//로그인
				loginlogout.loginMode();
				
			}else if(sel.equals("2")) {
				
				//회원가입
				SignUp.signUp();
				
			}else if(sel.equals("3")) {
				
				//IDPW 찾기
				FindAccount.findAccount();
				
			}else if(sel.equals("4")) {
				
				// Main종료
				break;
				
			}else {
				
				//다시입력
				System.out.println();
				System.out.printf("해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
				ViewAll.pause();
			}
			
			
		}// while문 종료
		
		
		//종료시 파일 덮어쓰기할 클래스
		// View클래스에서 출력
		System.out.println("프로그램을 종료합니다.");
		exit.writeAll();
		System.exit(0);
		

	}//main

}
