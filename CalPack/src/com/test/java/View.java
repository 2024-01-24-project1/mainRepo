package com.test.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class View {


		public static void calView() {
			
//			if(Member.auth == null) { //관리자 권한 체크 
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
//	}
		

				//전체 일정 보기		
		public static void calAll() throws IOException {
			String set ="";
			Scanner scan = new Scanner(System.in);
			System.out.println("===================================");
			System.out.println("           전체 일정          ");
			System.out.println("===================================");
			
			Service.list();
			
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

 

}
