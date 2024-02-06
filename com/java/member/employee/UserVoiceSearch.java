package com.java.member.employee;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.member.user.UserVoice;
import com.java.view.View;

public class UserVoiceSearch {
	
	protected static void userVoiceAll() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			String sel = "";
			String id = "";
			String title = "";
			
			userVoicePage(Data.userVoiceList);
			System.out.println("뒤로가기 엔터");
			System.out.println("민원의 내용을 보시려면 아무키나 입력");
			System.out.print("입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				
				// 모든민원보기 종료
				break;
			}
			
			System.out.println("아이디와 제목을 정확히 입력하시면");
			System.out.println("민원의 내용을 확인합니다.");
			System.out.print("아이디: ");
			id = scan.nextLine();
			System.out.print("제목: ");
			title = scan.nextLine();
			
			if( Validation.is_Duplication_UserVoice(id, title)) {
				
				for(UserVoice voice : Data.userVoiceList) {
					
					if(voice.getTitle().equals(title)) {
						
						// 읽음으로 변경
						voice.setIsRead("읽음");
						
						// 민원의 내용 출력
						System.out.println(voice.getContent());
						View.pause();
						
						break; // 민원객체 탐색 종료
					}
					
				}
				
			}else {
				System.out.println("잘못된 제목");
				System.out.println("다시 입력하세요.");
				View.pause();
			}
			
			
		}//while루프 종료
		
	}//End of userVoiceAll()
	
	public static void userVoicePage(ArrayList<UserVoice> list) {
		// 리스트의 페이지수 계산
		int page = (int)(Math.ceil((double)list.size() / 10));
		
		int index = 0;		// 문자로 입력받은 숫자를 int로 변환
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			String sel = "";	// 입력받는 문자열
			
			// View클래스 출력
			System.out.println("======================================================");
			System.out.println("                  민원 리스트");
			System.out.println("======================================================");
			
			list.stream().skip(index * 10)
			 							 .limit(10)
			 							 .forEach(uservoice -> System.out.printf("%-10s|%-20s|%-3s|\r\n"
					 													, uservoice.getId()
					 													, uservoice.getTitle()
					 													, uservoice.getIsRead()));
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("Page| %s / %s\r\n", index + 1, page);
			System.out.print("엔터입력시 페이지모드 종료.");
			System.out.print("원하는 페이지: ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				break;
			}else if (Validation.is_NumString(sel)) {
				index = Integer.parseInt(sel) - 1;
				
				if(index < 0 || index >= page) {
					System.out.println("페이지 범위를 벗어났습니다.");
					System.out.println("다시 입력해주세요.");
					index = 0;
					
				}
				
			}else {
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}//while루프 종료
		
	}//End of lostArticlePage()
	
}//End of class
