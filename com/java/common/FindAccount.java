package com.java.common;

import java.util.Scanner;

import com.java.view.View;

// 아이디, 비밀번호 찾기
public class FindAccount {

	public static void findAccount() {
		
		View.title("ID/PW 찾기");
		
		System.out.println();
		System.out.println("            1. ID 찾기");
		System.out.println("            2. PW 찾기");
		System.out.println("            3. 뒤로가기");
		System.out.println();

		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");

		Scanner scan = new Scanner(System.in);
		String num = ""; // 선택한 번호
		num = scan.nextLine();
		
		if (num.equals("1")) { // 1. ID 찾기
			FindAccount.findId();
		} else if (num.equals("2")) { // 2. PW 찾기
			FindAccount.findPw();
		} else if (num.equals("3")) { // 3. 뒤로가기
			View.mainmenu();
		} else { // 이외의 숫자 입력 시
			System.out.println();
			System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
			System.out.println();
		}
	}
	
	//ID 찾기
	public static void findId() {
	
		View.title("ID 찾기");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("주민등록번호: ");
		String registration = scan.nextLine();
		System.out.println();
		
		Data.userList.stream().filter(s -> s.getName().equals(name))
								.filter(s -> s.getRegistration().equals(registration))
		 						.forEach(s -> System.out.println(name + "님의 ID는 " + s.getId() + " 입니다."));
		
		View.pause();
	}
	
	
	// PW 찾기
	public static void findPw() {

		View.title("PW 찾기");

		Scanner scan = new Scanner(System.in);

		System.out.println("아이디: ");
		String id = scan.nextLine();
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("주민등록번호: ");
		String registration = scan.nextLine();
		System.out.println();

		Data.userList.stream().filter(s -> s.getName().equals(name))
				.filter(s -> s.getRegistration().equals(registration))
				.filter(s -> s.getId().equals(id))
				.forEach(s -> System.out.println(name + "님의 ID " + s.getId() + "의 PW는 " + s.getPw() + " 입니다."));

		View.pause();
	}

}
