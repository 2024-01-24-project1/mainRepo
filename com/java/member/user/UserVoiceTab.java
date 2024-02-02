package com.java.member.user;

import java.util.Scanner;
import com.java.common.Data;
import com.java.common.lostarticle.LostArticleTab;
import com.java.view.View;

public class UserVoiceTab {
	public static void userVoice() {
		
		while (true) {
			
			userVoiceView();
			
			Scanner scan = new Scanner(System.in);
			String num = "";
			num = scan.nextLine();

			if (num.equals("1")) { // 1. 일반 민원 접수
				complain();
				
			} else if (num.equals("2")) { // 2. 분실물 목록 확인
				LostArticleTab.lostArticle();
				
			} else if (num.equals("3")) { // 3. 뒤로가기
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
			} 
		}
	}

	private static void userVoiceView() { // 민원 초기 페이지
		View.title("민원");

		System.out.println();
		System.out.println("	 1. 일반 민원 접수");
		System.out.println("	 2. 분실물 목록 확인");
		System.out.println("	 3. 뒤로 가기");
		System.out.println();
		
		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
	}
	
	
	public static void complain() { // 1. 일반 민원 접수
		
		View.title("민원 접수");
		Scanner scan = new Scanner(System.in);
		
		int minLength = 2;
		int maxLength = 100;
		String title = "";
		String content = "";

		while (true) {
			System.out.println();
			System.out.print("제목: ");
			title = scan.nextLine();

			if (title.length() < minLength || title.length() > maxLength) {
				System.out.println("글자 수는 2~100자로 제한됩니다.");
			} else {
				break;
			}
		}

		while (true) {
			System.out.print("내용: ");
			System.out.println();
			content = scan.nextLine();

			if (content.length() < minLength || content.length() > maxLength) {
				System.out.println("글자 수는 2~100자로 제한됩니다.");
			} else {
				System.out.println();
				System.out.println("접수되었습니다.");
				View.pause();
				break;
			}
		}

		UserVoice userVoice = new UserVoice(User.auth, title, content);
		Data.userVoiceList.add(userVoice);
		
	}
	

}
