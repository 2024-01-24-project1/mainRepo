package mainRepo.com.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import mainRepo.com.java.common.Data;
import mainRepo.com.java.common.Load;
import mainRepo.com.java.member.user.User;



public class Main {
	
	public static void main(String[] args) {
		
		Load load = new Load();
		load.loadAll();
		
		
//		Scanner sc = new Scanner(System.in);
//		boolean loop= true;
//		// 수정
//		
//		
//		//데이터 로드
//		
//		
//		
//		while(loop) {
//			
//			
////			View.page1(sel);
//			
//			String sel = sc.nextLine();
//			
//			
//			if(sel.equals("1")) {
//				
//				//로그인
//				
//			}else if(sel.equals("2")) {
//				
//				//회원가입
//				
//			}else if(sel.equals("3")) {
//				
//				//IDPW 찾기
//				
//			}else if(sel.equals("4")) {
//				
//				//종료
//				
//			}else {
//				
//				//다시입력
//				
//			}
//			
//			
//			
//			
//			
//		}
		
		
		//종료시 파일 덮어쓰기할 클래스
		System.exit(0);
		
		
		

	}//main

}
