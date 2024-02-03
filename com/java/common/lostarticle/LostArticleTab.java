package com.java.common.lostarticle;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.view.View;

/** 확인 필요: 분실물 목록 출력 방법, 구현 필요: 다음/이전 페이지 ㅠㅠ */

public class LostArticleTab {
	
	public static void lostArticle() {
			
			Scanner scan = new Scanner(System.in);
		
			ArrayList<LostArticle> list = new ArrayList<LostArticle>();
		
			View.title(" 분실물 목록 확인");
			System.out.println();
			
			for(LostArticle article : Data.lostArticleList) {
				list.add(article);
			}
			
			 
			// 리스트의 페이지수 계산
			int page = (int)(Math.ceil((double)list.size() / 10));
			
			
			String sel = "";	// 입력받는 문자열
			int index = 1;		// 문자로 입력받은 숫자를 int로 변환
//			System.out.println(list.size());
//			System.out.println(page);
			while(true) {
				
				// View클래스 출력
				System.out.println("======================================================");
				System.out.println("                  분실물 리스트");
				System.out.println("======================================================");
				
				Data.lostArticleList.stream().skip(index * 10)
				 							 .limit(10)
				 							 .forEach(article -> System.out.printf("%-10s|%-20s|%-10s|%-10s\r\n"
						 													, article.getArticle()
						 													, article.getContent()
						 													, article.getLostStation() + "역"
						 													, article.getFindStation() + "역"));
				
				System.out.printf("Page| %s / %s\r\n", index, page);
				System.out.print("원하는 페이지: ");
				sel = scan.nextLine();
				
				
				if(sel.equals("back")) {
					break;
				}
				
				index = Integer.parseInt(sel);
				
			}//while루프 종료
			
		
	}//End of lostArticle
	
}//End of class
