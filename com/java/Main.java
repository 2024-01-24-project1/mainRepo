package com.java;


import java.util.Scanner;

import com.java.common.Exit;
import com.java.common.FindAccount;
import com.java.common.Load;
import com.java.common.LoginLogout;
import com.java.common.SignUp;
import com.java.view.ViewAll;


/**
 * 초기 화면을 나타내는 클래스
 */
public class Main {
	
	/**
	 * Main클래스 기본 생성자
	 */
	public Main() {
	}
	
	/**
	 * 프로그램 진입점인 메인메서드
	 * @param args 메인 내용
	 */
	public static void main(String[] args) {
		
		/**
		 * 파일 읽기 역할을 하는 Load 인스턴스
		 */
		Load load = new Load();
		/**
		 * 파일 쓰기 역할을 하는 Exit 인스턴스
		 */
		Exit exit = new Exit();
		
		/**
		 * 로그인 정보를 저장할 LoginLogout 인스턴스
		 */
		LoginLogout loginlogout = new LoginLogout();
		
		// 데이터 로드
		load.loadAll();
		
		Scanner sc = new Scanner(System.in);
		/**
		 * while문 제어하는 변수
		 */
		boolean loop= true;
		
		while(loop) {
			
			ViewAll.realMain();
			/**
			 * 입력받은 값을 저장하는 변수
			 */
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
				System.out.printf("\t\t\t해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
				ViewAll.pause();
			}
			
			
		}// while문 종료
		
		
		//종료시 파일 덮어쓰기할 클래스
		// View클래스에서 출력
		System.out.println("\t\t\t프로그램을 종료합니다.");
		exit.writeAll();
		System.exit(0);
		

	}//main

}
