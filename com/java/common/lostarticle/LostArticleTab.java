package com.java.common.lostarticle;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.view.View;

public class LostArticleTab {
	
	public static void lostArticleSearch() {
			
			Scanner scan = new Scanner(System.in);
			
			View.title(" 분실물 검색");
			System.out.println();
			
			ArrayList<LostArticle> list = new ArrayList<LostArticle>();
			
			
			while(true) {
				String search ="";
				
				System.out.println("찾으실 분실물을 검색해주세요.");
				System.out.print("분실물: ");
				search = scan.nextLine();
				
				final String SEARCH = search;
				System.out.println("");
				
				boolean check = Data.lostArticleList.stream().anyMatch(article -> article.getArticle().contains(SEARCH));
				
				if(check) {		// 1개라도 있으면
					
					// 입력이 포함된 분실물 이름을 가지는 ArrayList만들기
					for(LostArticle article : Data.lostArticleList) {
						
						if(article.getArticle().contains(search)) {
							list.add(article);
						}
						
					}
					
					lostArticlePage(list);
					
				}else {			// 1개도 없으면
					System.out.println(search + "를 포함하는 분실물이 없습니다.");
				}
				
				System.out.println("검색을 그만두시려면 back입력");
				System.out.println("다시 검색하시려면 엔터");
				System.out.printf("입력: ");
				search = scan.nextLine();
				
				if(search.equals("back")) {
					break;
				}
				
				
			}//while루프 종료
			
		
	}//End of lostArticle()
	
	public static void lostArticleAll() {
		
		lostArticlePage(Data.lostArticleList);
		
	}//End of lostArticle
	
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
					System.out.print("back입력시 뒤로갑니다.");
					System.out.print("원하는 페이지: ");
					sel = scan.nextLine();
					
					if(sel.equals("back")) {
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
