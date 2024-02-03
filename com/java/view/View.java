package com.java.view;

import java.util.Scanner;

import com.java.common.LoginLogout;

public class View {

	// 로그인 전후 화면 출력
	public static void mainmenu() {

		if (LoginLogout.auth == "") { // 프로그램 초기 화면 (로그인 안한 상태)
			System.out.println("=======================================");
			System.out.println();
			System.out.println("             SEOUL METRO");
			System.out.println();
			System.out.println("=======================================");

			System.out.println();
			System.out.println("                        1. 로그인");
			System.out.println("                        2. 회원가입");
			System.out.println("                        3. ID/PW 찾기");
			System.out.println("                        4. 종료");
			System.out.println();

			System.out.println("---------------------------------------");
			System.out.print("선택 (번호): ");
			System.out.println();
		}
	}

	public static void title(String title) { // 페이지 제목 출력
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("             " + title);
		System.out.println("--------------------------------------");
	}

	public static void pause() { // 일시 정지
		Scanner scan = new Scanner(System.in);

		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();
	}
	
	public static void ticketView() {
		View.title("정기권");

		System.out.println();
		System.out.println("          1. 나의 정기권 확인하기");
		System.out.println("          2. 정기권 등록");
		System.out.println("          3. 뒤로가기");
		System.out.println();

		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
	}
	
	public static void userVoiceView() { // 민원 초기 페이지
		View.title("민원");

		System.out.println();
		System.out.println("	 1. 일반 민원 접수");
		System.out.println("	 2. 분실물 목록 확인");
		System.out.println("     3. 분실물 검색하기");
		System.out.println("	 4. 뒤로 가기");
		System.out.println();
		
		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
	}

}//End of class
