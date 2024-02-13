package com.java.member.user;

import java.util.Scanner;
import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.lostarticle.LostArticleTab;
import com.java.view.ViewAll;

/**
 * 고객의 소리 메뉴를 나타내는 클래스
 */
public class UserVoiceTab {
	
	/**
	 * 고객의 소리 메뉴를 나타내는 메서드
	 */
	public static void userVoice() {
		
		while (true) {
			
			ViewAll.userVocMain();
			ViewAll.chooseNum();
			
			Scanner scan = new Scanner(System.in);
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = "";
			sel = scan.nextLine();

			if (sel.equals("1")) { 		  // 1. 일반 민원 접수
				complain();
			} else if (sel.equals("2")) { // 2. 분실물 목록 확인
				LostArticleTab.lostArticleAll();
			} else if(sel.equals("3")) {  // 3. 분실물 검색 
				LostArticleTab.lostArticleSearch();
			}
			else if (sel.equals("")) {    // 4. 뒤로가기
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("\t\t\t해당 섹션이 없습니다.");
				System.out.println("\t\t\t다시 입력해주세요.");
				System.out.println();
				ViewAll.pause();
			} 
		}
	}//End of userVoice()
	
	/**
	 * 고객이 민원 접수하는 메서드
	 */
	private static void complain() { // 1. 일반 민원 접수
		
		Scanner scan = new Scanner(System.in);
		
		/**
		 * 민원 제목을 저장하는 변수
		 */
		String title = "";
		/**
		 * 민원 내용을 저장하는 변수
		 */
		String content = "";
		
		ViewAll.userVoc();
		System.out.print("\t\t\t제목: ");
		title = scan.nextLine();
		System.out.print("\t\t\t내용: ");
		content = scan.nextLine();
		
		/**
		 * 제목의 유효성검사 결과를 저장하는 변수
		 */
		boolean titleCheck = (title.length() > 20 || title.length() < 2);
		/**
		 * 내용의 유효성검사 결과를 저장하는 변수
		 */
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
