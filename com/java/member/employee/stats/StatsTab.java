package com.java.member.employee.stats;

import java.util.Scanner;

import com.java.common.Validation;
import com.java.view.View;

public class StatsTab {
	
	final static long ALLSALES = 20000000000L;
	
	final static long ALLPASSENGER = 20000000L;
	
	
	
	public static void statsTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			String sel = ""; // 선택한 번호
			
			
			System.out.println("=======================================");
			System.out.println(" 1. 혼잡도 통계");
			System.out.println(" 2. 이용객 통계");
			System.out.println(" 3. 매출 통계");
			System.out.println(" 4. 뒤로가기");
			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) { 			// 1. 혼잡도 통계 
				
			}else if (sel.equals("2")) {	// 2. 이용객 통계
				userStatsTab();
			}else if (sel.equals("3")) {	// 3. 매출 통계
				salesStatsTab();
			}else if (sel.equals("4")) {	// 4. 뒤로가기
				
				// 혼잡도 통계 종료
				break;
			}else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다.");
				System.out.println("다시 입력해주세요.");
				View.pause();
			}
			
		}//while루프 종료
		
		System.out.println("통계탭 종료");
	}//End of statsTab()
	
	public static void userStatsTab() {
		String sel = "";
		
		Scanner scan = new Scanner(System.in);
			
		
		System.out.println("전체 통계 1, 특정호선 통계 2, 아무키나 누르면 뒤로가기");
		sel = scan.nextLine();
		
		if(sel.equals("1")) {
			Graph.drawGraph(Stats.allPassenger(), ALLPASSENGER);
		}else if (sel.equals("2")) {	// 2. 특정 호선 통계
			
			while(true) {
				System.out.println("보고싶은 호선을 입력해주세요");
				System.out.println("입력(N호선): ");
				sel = scan.nextLine();
				
				if(Validation.is_Line(sel)) {
					Graph.drawGraph(Stats.linePassneger(sel), ALLPASSENGER);
				}else {
					System.out.println("잘못된 입력");
				}
				
				System.out.println("다시입력하시려면 아무키나 눌러주세요.");
				System.out.println("뒤로가려면 엔터");
				
				if(sel.equals("")) {
					
					// 특정호선 통계 종료
					break;
				}
				
			}//while루프 종료
			
			
		}// 특정호선 통계 종료
		
		System.out.println("이용객 통계 종료");
	}//End of userStatsTab()
	
	public static void salesStatsTab() {
		String sel = "";
		
		Scanner scan = new Scanner(System.in);
			
		
		System.out.println("전체 매출 통계 1, 특정호선 매출 통계 2, 아무키나 누르면 뒤로가기");
		sel = scan.nextLine();
		
		if(sel.equals("1")) {
			Graph.drawGraph(Stats.allSales(), ALLSALES);
		}else if (sel.equals("2")) {
			while(true) {
				System.out.println("보고싶은 호선을 입력해주세요");
				System.out.println("입력(N호선): ");
				sel = scan.nextLine();
				
				if(Validation.is_Line(sel)) {
					Graph.drawGraph(Stats.lineSales(sel), ALLPASSENGER);
				}else {
					System.out.println("잘못된 입력");
				}
				
				System.out.println("다시입력하시려면 아무키나 눌러주세요.");
				System.out.println("뒤로가려면 엔터");
				
				if(sel.equals("")) {
					
					// 특정호선 통계 종료
					break;
				}
				
			}//while루프 종료
		}
		
		System.out.println("이용객 통계 종료");
		
		
		

	}//End of salesStatsTab()
	
}//End of class
