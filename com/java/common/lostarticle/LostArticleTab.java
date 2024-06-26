package com.java.common.lostarticle;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.view.ViewAll;

/**
 * 분실물 메뉴를 나타내는 클래스
 */
public class LostArticleTab {
	
	/**
	 * LostArticleTab클래스의 기본 생성자
	 */
	public LostArticleTab() {
	}
	
	/**
	 * 분실물 메뉴를 나타내는 메서드
	 */
	public static void lostArticleTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			/**
			 * 입력받은 값을 저장하는 변수
			 */
			String sel = ""; // 선택한 번호
			
			// 모든 분실물 먼저 보여주기
			lostArticleAll();
			
			ViewAll.lostarticleMain();
			ViewAll.chooseNum();

			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 분실물 검색
				lostArticleSearch();
			} else if(sel.equals("2")) {	// 2. 분실물 추가
				addLostArticle();
			} else if(sel.equals("3")) {	// 3. 분실물 제거
				removeLostArticle();
			} else if (sel.equals("")) {	//    뒤로가기
				
				// 분실물탭 종료
				break;
					
			} else { // 이외의 숫자 입력 시
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
			}
			
		}//while루프 종료
		
	}//End of lostArticleTab()
	
	/**
	 * 분실물을 검색하는 메서드
	 */
	public static void lostArticleSearch() {
			
			Scanner scan = new Scanner(System.in);
			
			ViewAll.lostarticleSearchTop();
			System.out.println();
			
			/**
			 * 분실물 정보를 저장하는 ArrayList
			 */
			ArrayList<LostArticle> list = new ArrayList<LostArticle>();
			
			
			while(true) {
				/**
				 * 입력한 값을 저장하는 변수
				 */
				String search ="";
				
				System.out.println("\t\t\t찾으실 분실물을 검색해주세요.");
				System.out.print("\t\t\t분실물: ");
				search = scan.nextLine();
				
				if (search.equals("")) {					// 엔터 입력시 문구 출력후 쫒아내기
					System.out.println("\t\t\t잘못된 입력입니다.");
					break;
				}
				
				/**
				 *  입력한 값을 저장하는 변수
				 */
				final String SEARCH = search;
				
				/**
				 * 일치 여부를 저장하는 변수
				 */
				boolean check = Data.lostArticleList.stream().anyMatch(article -> article.getArticle().contains(SEARCH));
				
				if(check) {		// 1개라도 있으면
					
					// 입력이 포함된 분실물 이름을 가지는 ArrayList만들기
					for(LostArticle article : Data.lostArticleList) {
						
						if(article.getArticle().contains(search)) {
							list.add(article);
						}
						
					}
					
					LostArticleSearch.lostArticlePage(list);
					
				} else {			// 1개도 없으면
					System.out.println("\t\t\t" + search + "를 포함하는 분실물이 없습니다.");
				}
				
				System.out.println("\t\t\t검색을 그만두시려면 엔터");
				System.out.println("\t\t\t다시 입력하시려면 아무키나 입력하세요.");
				search = scan.nextLine();
				
				if(search.equals("")) {
					break;
				}
				
			}//while루프 종료
			
		
	}//End of lostArticle()
	
	/**
	 * 분실물을 추가하는 메서드
	 */
	public static void addLostArticle() {
		
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
			
			/**
			 * 입력받은 값을 저장하는 변수
			 */
			String sel = "";
			/**
			 * 분실물 이름을 저장하는 변수
			 */
			String article = "";
			/**
			 * 분실물 내용을 저장하는 변수
			 */
			String content = "";
			/**
			 * 분실물 찾은 위치를 저장하는 변수
			 */
			String find = "";
			/**
			 * 분실물 보관 위치를 저장하는 변수
			 */
			String keep = "";
			
			ViewAll.lostarticleAdd();
			
			System.out.println("\t\t\t추가할 분실물의 이름");
			System.out.printf("\t\t\t분실물: ");
			article = scan.nextLine();
			System.out.print("\t\t\t내용  : ");
			content = scan.nextLine();
			System.out.print("\t\t\t발견역: ");
			find = scan.nextLine();
			System.out.print("\t\t\t보관역: ");
			keep = scan.nextLine();
			
			if (find.endsWith("역")) {
	            // '역'을 제거한 문자열을 반환합니다.
				find = find.substring(0, find.length() - 1);
	        } 
			
			if (keep.endsWith("역")) {
	            // '역'을 제거한 문자열을 반환합니다.
				keep = keep.substring(0, keep.length() - 1);
	        } 
			
				
			if(article.length() > 20 || article.length() < 1) {
				System.out.println("\t\t\t분실물의 이름은 20글자 이하 1글자 이상입니다");
			}
			
			if(content.length() > 20) {
				System.out.println("\t\t\t상세내용은 20글자 이하입니다.");
			}
			
			if( Validation.is_StationName(find)) {
				System.out.println("\t\t\t발견역이 올바른 역이름이 아닙니다.");
			}
			
			if( Validation.is_StationName(find)) {
				System.out.println("\t\t\t보관중인 역이름이 올바른 역이름이 아닙니다.");
			}
			
			// 모든 조건에 걸리지 않을경우
			if( !(article.length() > 20 || article.length() < 1) && content.length() > 20 
					&& Validation.is_StationName(find) && Validation.is_StationName(keep) ) {
				
				LostArticle lostArticle = new LostArticle(article, content, find, keep);
				Data.lostArticleList.add(lostArticle);
				
				System.out.println("\t\t\t분실물 추가 완료");
				ViewAll.lostarticleAddResult();
				ViewAll.pause();
				
				// 분실물 추가 종료
				break;
			}
			
			System.out.println("\t\t\t다시 입력하시려면 아무키나 입력하세요.");
			System.out.println("\t\t\t뒤로가려면 엔터를 입력하세요.");
			System.out.printf("입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				
				// 분실물 추가 종료
				break;
			}
			
			
		}//while루프 종료
		
		
	}//End of addLostArticle()
	
	/**
	 * 분실물을 제거하는 변수
	 */
	public static void removeLostArticle() {
		
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
			
			LostArticleSearch.lostArticlePage(Data.lostArticleList);
			/**
			 * 입력한 값인 분실물의 이름을 저장하는 변수
			 */
			String sel = "";
			/**
			 * 입력한 값인 보관 된 위치를 저장하는 변수
			 */
			String sts = "";
			
			System.out.println("\t\t\t삭제할 분실물의 이름");
			System.out.printf("\t\t\t분실물 : ");
			sel = scan.nextLine();
			System.out.println("\t\t\t보관 된 위치: ");
			sts = scan.nextLine();
			
			if (sts.endsWith("역")) {
	            // '역'을 제거한 문자열을 반환합니다.
	            sts = sts.substring(0, sts.length() - 1);
	        }
			
			
			// 해당역에 보관중인 분실물이 존재하는지 확인
			if( Validation.is_Duplication_LostArticle(sel, sts)) {
				
				for(LostArticle article : Data.lostArticleList) {
					
					if(article.getArticle().equals(sel) && article.getFindStation().equals(sts)) {
						Data.lostArticleList.remove(article);
						
						break;	// article객체 탐색 종료
					}
					
				}
				
				LogSave.logSave(LogSave.LOSTARTICLEREMOVE);
				ViewAll.lostarticleDeleteResult();
				ViewAll.pause();
				
				// 분실물 삭제 종료
				break;
				
			}else {
				System.out.println("\t\t\t존재하지 않는 분실물 입니다.");
				ViewAll.pause();
			}
			
			System.out.println("\t\t\t다시 삭제하려면 아무키나 입력");
			System.out.println("\t\t\t뒤로 가시려면 엔터");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				// 분실물 삭제 종료
				break;
			}
			
			
		}//while루프 종료
		
	}//End of removeLostArticle()
	/**
	 * 모든 분실물을 보는 메서드
	 */
	public static void lostArticleAll() {
		
		LostArticleSearch.lostArticlePage(Data.lostArticleList);
		
	}//End of lostArticle
	
}//End of class
