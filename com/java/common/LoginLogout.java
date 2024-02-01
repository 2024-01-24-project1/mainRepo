package com.java.common;

import java.util.Scanner;

import com.java.member.Member;
import com.java.view.View;

public class LoginLogout {

	public static void login() {
		Scanner scan = new Scanner(System.in);

		View.title("로그인");

		System.out.print("아이디: ");
		String id = scan.nextLine();
		System.out.print("비밀번호: ");
		String pw = scan.nextLine();

		boolean check;
		check = Data.userList.stream().anyMatch(user -> user.getId().equals(id) && user.getPw().equals(pw));

		
		// 로그인 성공
		if (check == true) { // 고객 모드
			Member.auth = id;
			System.out.println();
			System.out.println("로그인이 완료되었습니다.");
			System.out.println();

			System.out.println("=======================================");
			System.out.println();
			System.out.printf("             SEOUL METRO       ");
			Data.employeeList.stream().filter(s -> s.getId().equals(Member.auth) )
			.forEach(s -> System.out.println(s.getName() + "님") );
			System.out.println();
			System.out.println("=======================================");
			System.out.println();
			System.out.println("           1. 열차 시간표");
			System.out.println("           2. 민원 접수");
			System.out.println("           3. 정기권 현황");
			System.out.println("           4. 길 찾기");
			System.out.println("           5. 요금표");
			System.out.println("           6. 마이 페이지");
			System.out.println("           7. 로그아웃");
			System.out.println();

			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");

			Scanner scan1 = new Scanner(System.in);
			String num = ""; // 선택한 번호
			num = scan1.nextLine();

			if (num.equals("1")) { // 1. 열차 시간표

			} else if (num.equals("2")) { // 2. 민원 접수

			} else if (num.equals("3")) {

			} else if (num.equals("4")) {

			} else if (num.equals("5")) {

			} else if (num.equals("6")) {

			} else if (num.equals("7")) {
				LoginLogout.logout();
				View.mainmenu();
				
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
			}

			// 관리자 모드
		} else if (check == true && Member.level.equals("2")) {
			Member.auth = id;
			System.out.println();
			System.out.println("=======================================");
			System.out.println();
			System.out.println("         [관리자 모드]");
			System.out.println();
			System.out.print("         이름: ");
			Data.employeeList.stream().filter(s -> s.getId().equals(Member.auth) )
									.forEach(s -> System.out.println(s.getName() ) );
			System.out.println("         직급: " + Member.rank); // s 직급 추가하기
			System.out.println();
			System.out.println("=======================================");
			System.out.println();
			System.out.println("           1. 역 관리");
			System.out.println("           2. 직원 관리");
			System.out.println("           3. 민원");
			System.out.println("           4. 행사 캘린더");
			System.out.println("           5. 통계 정보");
			System.out.println("           6. 행동로그 보기");
			System.out.println("           7. 로그아웃");
			System.out.println("           0. 종료");
			System.out.println();

			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");
			System.out.println();

			Scanner scan1 = new Scanner(System.in);
			String num = ""; // 선택한 번호
			num = scan1.nextLine();

			if (num.equals("1")) {
				LoginLogout.logout();
			} else if (num.equals("2")) {

			} else if (num.equals("3")) {

			} else if (num.equals("4")) {

			} else if (num.equals("5")) {

			} else if (num.equals("6")) {

			} else if (num.equals("7")) {
				LoginLogout.logout();
			} else if (num.equals("8")) {

			} else { // 이외의 숫자 입력 시
				System.out.println();
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
			}

		} else { // 로그인 실패
			System.out.println("아이디 또는 비밀번호가 틀립니다.");
			View.pause();
		}
	}

	public static void logout() {
		// 로그아웃
		Member.auth = null;
		System.out.println("로그아웃이 완료되었습니다.");
		
		View.mainmenu();
		
	}

}

