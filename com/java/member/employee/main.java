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
			}
			
			//추가 제거 검색
//			while(true) {
//			if(num == 1) {
//					
//					
//				System.out.println("1. 분실물 추가");
//				System.out.println("2. 분실물 제거");
//				System.out.println("3. 분실물 검색");
//					
//				System.out.print("숫자 입력: ");
//				num = scan.nextInt();
//				System.out.println();
//					
//				if(num == 1) {
//					lostArticle.articleAdd();
//				} else if(num == 2) {
//					View.lostArticlelist();
//					lostArticle.articleremove();
//				} else if(num == 3) {
//					lostArticle.articleSearch();
//				} else if(num == 9) { // 뒤로가기
//					 break;
//				} else {
//					System.out.println("올바른 값을 입력하세요.");
//				}
					
					
					
//			} else if (num == 2) {
//				View.userVoiceprint();
//				userVoice.list();
//					
//				System.out.println("1. 민원 내용 보기");
//				System.out.println("2. ID로 민원 검색");
//				num = scan.nextInt();
//					
//				if(num == 1) {
//					userVoice.read();
//				} else if(num == 2) {
//					userVoice.searchId();
//				} else if(num == 9) { // 뒤로가기
//					
//				} else {
//					System.out.println("올바른 값을 입력하세요.");
//				}
//					
//			} else {
//				System.out.println("올바른 값을 입력하세요.");
//				break;
//			}
//			}
//		}
	}
	}
}

	