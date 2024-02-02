package com.java.common.lostarticle;

import java.util.Scanner;
import com.java.common.Data;
import com.java.common.Load;
import com.java.view.View;

/** 확인 필요: 분실물 목록 출력 방법, 구현 필요: 다음/이전 페이지 ㅠㅠ */

public class LostArticleTab {
	public static void lostArticle() {
	
		View.title(" 분실물 목록 확인");
		System.out.println();
		
		// 분실물 목록 출력
		for (int i = 0; i < Data.lostArticleList.size(); i++) {
			System.out.println(Data.lostArticleList.get(i));
		}
		
		
		while (true) {
			System.out.println();
			System.out.println("	 1. 분실물 검색하기");
			lostArticleView();

			Scanner scan = new Scanner(System.in);
			String num = "";
			num = scan.nextLine();

			if (num.equals("1")) { // 1. 분실물 검색하기
				searchArticle();
			} else if (num.equals("2")) { // 2. 다음 페이지
				
				// ***
				
			} else if (num.equals("3")) { // 3. 이전 페이지

				// ***
				
			} else if (num.equals("4")) { // 4. 뒤로 가기
				break;
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
			}
		}
	}

	private static void lostArticleView() {
		System.out.println();
		System.out.println("	 2. 다음 페이지");
		System.out.println("	 3. 이전 페이지");
		System.out.println("	 4. 뒤로 가기");
		System.out.println();

		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
	}

	public static void searchArticle() {
		
		View.title(" 분실물 검색");
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("검색 품목: ");
		System.out.println();
		String word = scan.nextLine();
		
		
		// 검색 물품 단어가 포함된 문장을 보여줌 -> ??
		boolean checkArticle = false;
		checkArticle = Data.lostArticleList.stream().anyMatch(a -> a.getArticle().equals(word));

			if (checkArticle) {
				
			} else {
				System.out.println("검색하신 물품이 없습니다.");
			}

//			Data.lostArticleList.stream().filter(a -> a.getArticle().equals(word))
//			.forEach(s -> System.out.println(s.article));		
		

		while (true) {

			lostArticleView();

			String num = "";
			num = scan.nextLine();

			if (num.equals("1")) { // 1. 다음 페이지

				// ***
				
			} else if (num.equals("2")) { // 2. 이전 페이지

				// ***
				
			} else if (num.equals("3")) { // 3. 뒤로 가기
				break;
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
			}
		}
	}
	
}
