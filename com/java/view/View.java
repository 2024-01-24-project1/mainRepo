package com.java.view;

import java.util.Scanner;

import com.java.common.LoginLogout;

public class View {
	
	public static String line = "====================================================";
	public static String empty8 = "        ";
	
	
	// 이전의 내용들을 위로 올리기
	public static void clear() {
		
		for(int i = 0; i < 20; i++) {
			System.out.println();
		}
		
	}//End of clear()
	
	
	// 로그인 전후 화면 출력
	public static void mainmenu() {
			clear();
			System.out.printf("%s%s", empty8, line);
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
		System.out.println("	 뒤로 가기 엔터");
		System.out.println();
		
		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
	}
	
	public static void costTimeTable() { //고객 > 요금표 (추가 운임 미정)
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t 길 찾기 (즐겨찾기 노선)");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t [운임]\t\t[연령]\t\t[이용방법]\t[수도권 가격]");
		System.out.println("\t\t\t\t\t\t\t [기본운임]\t[일반]\t\t[교통카드]\t[1400원]");
		System.out.println("\t\t\t\t\t\t\t [기본운임]\t[일반]\t\t[1회권]\t\t[1500원]");
		System.out.println("\t\t\t\t\t\t\t [기본운임]\t[청소년]\t\t[교통카드]\t[800원]");
		System.out.println("\t\t\t\t\t\t\t [기본운임]\t[청소년]\t\t[1회권]\t\t[1500원]");
		System.out.println("\t\t\t\t\t\t\t [기본운임]\t[어린이]\t\t[교통카드]\t[500원]");
		System.out.println("\t\t\t\t\t\t\t [기본운임]\t[어린이]\t\t[1회권]\t\t[500원]");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t만 6세 미만 또는 만 65세 이상, 장애인, 국가 유공자, 보훈 보상대상자 무료");
		
	}

}//End of class
