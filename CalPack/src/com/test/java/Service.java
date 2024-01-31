package com.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Service {
	//목록 출력
	public static void list() throws IOException {
		//파일 내용 불러오기 
		 BufferedReader reader 
		 = new BufferedReader(new FileReader(Data.CALDAT)); //읽기 선언
		 
		 
		 System.out.println("=================================");
		 System.out.println(" 캘린더 목록");
		 System.out.println("=================================");

		 try {
			 String line = "";
			 while((line = reader.readLine()) != null) {
				 System.out.println(line);
				 
			 }
		 }catch(IOException e){
			 System.out.println("ㅇㅅㅇ");
		 }
		 
			
	}
		
	
	//일정 화면 추가
	public static void add() {
		Scanner scan = new Scanner(System.in);
		String date ; //날짜 받을 변수
		String txt ; //내용 받을 변수
		
		System.out.println("===================================");
		System.out.println("           추가 할 일정을 작성해주세요          ");
		System.out.println("===================================");
		
		System.out.print("날짜(XXXX-XX-XX 형식으로 작성해주세요)");
		
		 date = scan.nextLine();
		
		System.out.println("내용");
		txt = scan.nextLine();
		
		CalInput input = new CalInput(date,txt);
		 Data.list.add(input);
		 
		 
		
		
		System.out.println("일정이 성공적으로 추가되었습니다.");
		
	}
	
	//일치하는 내용 있으면 삭제 
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
		CalInput m1 = new CalInput(date, txt);
		
		if(date.equals(m1.day) && txt.equals(m1.content)){
			// 해당하는 글자 삭제 (???)
			Data.list.remove((date.equals(m1.day)) && (txt.equals(m1.content)));
			System.out.println("성공적으로 삭제가 되었습니다.");
		}else {
			System.out.println("올바른 입력이 아닙니다.");
		}
		

		
	}
	
}
