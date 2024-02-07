package com.java.member.employee;

import java.util.Scanner;

import com.java.common.LoginLogout;
import com.java.common.lostarticle.LostArticleTab;
import com.java.view.View;

public final class UserVoiceManagemnetTab {

	protected static void userVoiceManagementTab() {
		
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			String sel = ""; // 선택한 번호
			
			System.out.println("=======================================");
			System.out.printf("             민원           ");
			System.out.println(LoginLogout.position + " " + LoginLogout.auth + "님");
			System.out.println("=======================================");
			System.out.println("           1. 전체 분실물 보기");
			System.out.println("           2. 전체 민원 보기");
			System.out.println("              뒤로가기 엔터");
			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 전체 분실물 보기
				LostArticleTab.lostArticleTab();
			} else if(sel.equals("2")) {	// 2. 직원 민원 보기
				UserVoiceSearch.userVoiceAll();
			} else if (sel.equals("")) {	//    뒤로가기
				
				// 민원 종료
				break;
					
			} else { // 이외의 숫자 입력 시
					System.out.println("해당 섹션이 없습니다.");
					System.out.println("다시 입력해주세요.");
					View.pause();
			}
			
			
		}// while루프 종료
		
	}//End of userVoiceManagementTab()
	
	
}//End of class
