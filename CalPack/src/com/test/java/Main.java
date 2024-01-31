package com.test.java;

import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	
	boolean loop = true;
	Scanner scan = new Scanner(System.in);
	while(loop) {
		
		View.calView();
		
		String sel = scan.nextLine();
		
		if(sel.equals("1")) {
			//캘린더 > 전체 일정 보기 
			View.calAll();
			
		}else if(sel.equals("2")) {
			//일정추가
			View.add();
		}else if(sel.equals("3")) {
			//일정 삭제 
			View.delete();
			
		}else if(sel.equals("4")) {
			//뒤로 가기
			//메인 화면
		}else  {
			System.out.println("올바른 입력을 넣어주세요");
		
			System.out.print("이어서 계속 하려면 아무키나 눌러주세요");
			scan.nextLine();
			}
		
	}
	
	
}//main

}



