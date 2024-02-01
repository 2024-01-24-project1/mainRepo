package com.java;


import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Exit;
import com.java.common.FindAccount;
import com.java.common.Load;



public class Main {
	
	public static void main(String[] args) {
		
		Load load = new Load();
		Exit exit = new Exit();
		
		// 데이터 로드
		load.loadAll();
		
		
		Scanner sc = new Scanner(System.in);
		boolean loop= true;
		// 수정
		
		while(loop) {
			
			
//			View.page1(sel);
			System.out.println("메인");
			System.out.println("1->로그인");
			System.out.println("2->회원가입");
			System.out.println("3->IDPW찾기");
			System.out.println("4->종료");
			System.out.printf("메인입력: ");
			String sel = ""; 
			sel = sc.nextLine();
			
			
			if(sel.equals("1")) {
				//로그인
				
			}else if(sel.equals("2")) {
				//회원가입
				
			}else if(sel.equals("3")) {
				//IDPW 찾기
				FindAccount find = new FindAccount();
				find.findAccount();
				
			}else if(sel.equals("4")) {
				//종료
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				
				//다시입력
				System.out.println();
				System.out.printf("해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
			}
			
			
		}// while문 종료

		
		
		//종료시 파일 덮어쓰기할 클래스
		exit.writeAll();
		
		System.exit(0);

	}//main

}
