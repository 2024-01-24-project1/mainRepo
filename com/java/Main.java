package com.java;

import java.util.Scanner;
import com.java.common.Exit;
import com.java.common.FindAccount;
import com.java.common.Load;
import com.java.common.LoginLogout;
import com.java.common.SignUp;
import com.java.view.View;

public class Main {

	public static void main(String[] args) {

		Load load = new Load();
		Exit exit = new Exit();

		LoginLogout loginlogout = new LoginLogout();
		SignUp signup = new SignUp();
		FindAccount find = new FindAccount();
		
		load.loadAll();

		Scanner sc = new Scanner(System.in);
		boolean loop = true;

		// 데이터 로드
		while (loop) {
			View.mainmenu(); // 프로그램 초기 화면

			String sel = "";
			sel = sc.nextLine();

			if (sel.equals("1")) {
				// 로그인
				LoginLogout.login();

			} else if (sel.equals("2")) {
				// 회원가입
				signup.signUp();

			} else if (sel.equals("3")) {
				// ID, PW 찾기
				FindAccount.findAccount();

			} else if (sel.equals("4")) {
				// 종료
				System.out.println("프로그램을 종료합니다.");
				loop = false;
				
			} else {
				// 이외의 숫자 입력 시
				System.out.println();
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
			}

		}

		// 종료시 파일 덮어쓰기할 클래스
		exit.writeAll();
		System.exit(0);

	}// main

}
