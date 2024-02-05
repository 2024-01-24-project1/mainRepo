package com.java.common.lostarticle;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Validation;

public class LostArticleSearch {
	
	
	public static void lostArticlePage(ArrayList<LostArticle> list) {
		// 리스트의 페이지수 계산
				int page = (int)(Math.ceil((double)list.size() / 10));
				
				int index = 0;		// 문자로 입력받은 숫자를 int로 변환
				
				Scanner scan = new Scanner(System.in);
				
				while(true) {
					String sel = "";	// 입력받는 문자열
					
					// View클래스 출력
					System.out.println("======================================================");
					System.out.println("                  분실물 리스트");
					System.out.println("======================================================");
					
					list.stream().skip(index * 10)
					 							 .limit(10)
					 							 .forEach(article -> System.out.printf("%-10s|%-20s|%-10s|%-10s\r\n"
							 													, article.getArticle()
							 													, article.getContent()
							 													, article.getLostStation() + "역"
							 													, article.getFindStation() + "역"));
					
					System.out.printf("Page| %s / %s\r\n", index + 1, page);
					System.out.print("엔터입력시 리스트보기를 종료합니다.");
					System.out.print("원하는 페이지: ");
					sel = scan.nextLine();
					
					if(sel.equals("")) {
						break;
					}else if (Validation.is_NumString(sel)) {
						index = Integer.parseInt(sel) - 1;
						
						if(index < 0 || index >= page) {
							System.out.println("페이지 범위를 벗어났습니다.");
							System.out.println("다시 입력해주세요.");
							index = 0;
							
						}
						
					}else {
						System.out.println("잘못된 입력입니다.");
						System.out.println("다시 입력해주세요.");
					}
					
				}//while루프 종료
		
	}//End of lostArticlePage()
	
}//End of class
