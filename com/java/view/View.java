package com.java.view;

import java.util.Scanner;
import com.java.member.Member;

public class View {

	// 로그인 전후 화면 출력
	public static void mainmenu() {

		if (Member.auth == "") { // 프로그램 초기 화면 (로그인 안한 상태)
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

		System.out.println();
		System.out.println("계속하려면 엔터를 치세요.");
		scan.nextLine();
		System.out.println();
	}

}
