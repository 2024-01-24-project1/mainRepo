package com.java;


import java.lang.foreign.Linker.Option;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.java.common.Data;
import com.java.common.Exit;
import com.java.common.Load;



public class Main {
	
	public static void main(String[] args) {
		
		Load load = new Load();
		Exit exit = new Exit();
		
		// 데이터 로드
		load.loadAll();
		
		
//		Scanner sc = new Scanner(System.in);
//		boolean loop= true;
//		// 수정
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

		
		
//		Data.stationTimeList.stream()
//							.forEach(s -> System.out.println(s));
		
//		ArrayList<String> temp = Data.stationTimeList.stream()
//							.filter(station -> station.getStationName().equals("청량리"))
//							.filter(s -> s.getUpNomal())
//							.toArray(String[]::new));
		

		
//		Optional<String> name = Data.userList.stream().filter(s -> s.getId().equals(id) && s.getPw().equals(pw))
//													.filter(s -> s.getName()).findFirst();
											
		
		
		
		
		//종료시 파일 덮어쓰기할 클래스
		exit.writeAll();
		
		System.exit(0);

	}//main

}
