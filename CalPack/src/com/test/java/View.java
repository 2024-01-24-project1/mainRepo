package com.test.java;

import java.util.Scanner;

public class View {


		public static void calView() {
			
			if(Member.auth == null) { //관리자 권한 넣을때만 나오는 부분이기 때문에 
				System.out.println("=================================================================");
				System.out.println("               현재 메뉴:행사 캘린더                              ");
				System.out.println("                   	이름                                          ");
				System.out.println("                     직급                                       ");
				System.out.println("==============================================================");
			System.out.println("1. 전체 일정 보기");
		
			System.out.println("2. 일정추가");
		
			
			System.out.println("3. 일정 제거");
			
			
			System.out.println("4. 뒤로 가기");
			System.out.println("==========================");
			System.out.print("선택(번호) : ");
		}
	}
		

				//전체 일정 보기		
		public static void calAll() {
		String set = ""; // q , e, q! 받는 변수
			Scanner scan = new Scanner(System.in);
			System.out.println("===================================");
			System.out.println("           전체 일정          ");
			System.out.println("===================================");
			
			System.out.println(Data.list);
			System.out.print("이전 페이지를 보고 싶으면 q, 다음 페이지를 보고 싶으면 e를 작성해주세요\r\n");
			System.out.print("뒤로 가기를 원할 시 q!를 작성해주세요");
			
			set = scan.nextLine();
			if( set.equals("q")) {
				//이전 페이지
			}else if(set.equals("e")) {
				//다음 페이지
			}else if(set.equals("q!")){
			//뒤로 가기 		
			}
			
		}
		//일정 추가 화면 
		public static void add() {
			Scanner scan = new Scanner(System.in);
			String set = ""; // q , e 받는 변수
			String date = ""; //날짜 받을 변수
			String txt =""; //내용 받을 변수
			
			System.out.println("===================================");
			System.out.println("           추가 할 일정을 작성해주세요          ");
			System.out.println("===================================");
			
			System.out.print("날짜");
			
			date = scan.nextLine();
			
			System.out.println("내용");
			txt = scan.nextLine();
			
			CalInput m1 = new CalInput(date, txt);
			
			
			System.out.println("일정이 성공적으로 추가되었습니다.");
			
		}
		
		//일정 삭제 화면 
		public static void delete() {
			
			Scanner scan = new Scanner(System.in);
			String date = ""; //날짜 받을 변수
			String txt =""; //내용 받을 변수
			
			System.out.println("===================================");
			System.out.println("           삭제 할 일정을 작성해주세요        ");
			System.out.println("===================================");
			
			System.out.print("날짜");
			
			date = scan.nextLine();
			
			System.out.println("내용");
			txt = scan.nextLine();
			
			//일치하는 내용 있으면 삭제 
			

			
		}
}
