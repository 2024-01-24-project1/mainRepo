package com.java.member.employee;

import java.util.Scanner;

import com.java.view.View;

public class main {

	public static void main(String[] args) {

		while(true) {
			Scanner scan = new Scanner(System.in);
			int num;
			View.userVoice();
			System.out.println("1. 전체 분실물 보기");
			
			System.out.println("2. 전체 민원 보기");
			
			System.out.print("숫자 입력: ");
			num = scan.nextInt();
			System.out.println();
			
			if(num == 1) {
				lostArticle.articlALl();
			} else if (num == 2) {
				userVoice.voiceAll();
			}
			

		}
	}
}

	