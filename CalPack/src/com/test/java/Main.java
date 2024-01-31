package com.test.java;

import java.io.IOException;
import java.util.Scanner;
import com.test.java.Service;
import com.test.java.Data;
public class Main {
public static void main(String[] args) throws IOException {
	
	boolean loop = true;
	Scanner scan = new Scanner(System.in);
	while(loop) {
		//캘린더 화면 
		View.calView();
		
		String sel = scan.nextLine();
		
		if(sel.equals("1")) {
			//캘린더 > 전체 일정 보기
			Service service = new Service();
			View.calAll();
			
		}else if(sel.equals("2")) {
			//일정추가
			Service service = new Service();
			service.add();
		}else if(sel.equals("3")) {
			//일정 삭제 
			Service service = new Service();
			service.delete();
			
		}else if(sel.equals("4")) {
			//뒤로 가기
			//메인 화면
		}else  {
			loop = false;
//			System.out.println("올바른 입력을 넣어주세요");
//		
//			System.out.print("이어서 계속 하려면 아무키나 눌러주세요");
//			scan.nextLine();
			}
		
	}
	//작성한 데이터 저장
	Data.save();
	
}//main

}



