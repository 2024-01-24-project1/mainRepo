package com.java.member.user;

import java.util.Scanner;
import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.lostarticle.LostArticleTab;
import com.java.view.ViewAll;

public class UserVoiceTab {
	
	public static void userVoice() {
		
		while (true) {
			
			ViewAll.userVocMain();
			ViewAll.chooseNum();
			
			Scanner scan = new Scanner(System.in);
			String num = "";
			num = scan.nextLine();

			if (num.equals("1")) { 		  // 1. 일반 민원 접수
				complain();
			} else if (num.equals("2")) { // 2. 분실물 목록 확인
				LostArticleTab.lostArticleAll();
			} else if(num.equals("3")) {  // 3. 분실물 검색 
				LostArticleTab.lostArticleSearch();
			}
			else if (num.equals("")) {    // 4. 뒤로가기
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("\t\t\t해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
				ViewAll.pause();
			} 
		}
	}//End of userVoice()
	
	private static void complain() { // 1. 일반 민원 접수
		
		Scanner scan = new Scanner(System.in);
		
		String title = "";
		String content = "";
		
		ViewAll.userVoc();
		System.out.print("\t\t\t제목: ");
		title = scan.nextLine();
		System.out.print("\t\t\t내용: ");
		content = scan.nextLine();
		
		boolean titleCheck = (title.length() > 20 || title.length() < 2);
		boolean contentCheck = (content.length() > 200 || content.length() < 2); 
		
		if (titleCheck) {
			System.out.println("\t\t\t제목의 글자 수는 2~20자로 제한됩니다.");
		}
		if( contentCheck) {
			System.out.println("\t\t\t내용의 글자수는 2 ~ 200자로 제한됩니다.");
		} 
		
		// 글자수를 둘다 맞춘경우
		if( !(titleCheck || contentCheck) ) {
			
			System.out.println("\t\t\t민원이 접수되었습니다.");
			UserVoice userVoice = new UserVoice(LoginLogout.auth, title, content);
			Data.userVoiceList.add(userVoice);
		}else {
			System.out.println("\t\t\t다시 민원을 접수해주세요.");
		}
		ViewAll.pause();
		
	}//End of complain()

}//End of class
