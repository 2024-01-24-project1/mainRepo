package com.java.member.employee.stats;

import java.util.Scanner;

import com.java.busy.BusyStat;
import com.java.common.Validation;
import com.java.view.ViewAll;


public class StatsTab {
	
	final static long ALLSALES = 13000000000L;
	
	final static long ALLPASSENGER = 12000000L;
	
	
	
	public static void statsTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			String sel = ""; // 선택한 번호
			
			
			System.out.println("=======================================");
			System.out.println(" 1. 혼잡도 통계");
			System.out.println(" 2. 이용객 통계");
			System.out.println(" 3. 매출 통계");
			System.out.println(" 뒤로가기 엔터");
			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) { 			// 1. 혼잡도 통계 
				BusyStat busyStat =new BusyStat();
				busyStat.busyStat();
			}else if (sel.equals("2")) {	// 2. 이용객 통계
				userStatsTab();
			}else if (sel.equals("3")) {	// 3. 매출 통계
				salesStatsTab();
			}else if (sel.equals("")) {	// 4. 뒤로가기
				
				// 혼잡도 통계 종료
				break;
			}else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다.");
				System.out.println("다시 입력해주세요.");
				ViewAll.pause();
			}
			
		}//while루프 종료
		
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
				System.out.print("입력(N호선): ");
				sel = scan.nextLine();

				if(Validation.is_Line(sel)) {
					
					int line = Integer.parseInt(sel.charAt(0) + "");
					
					switch (line) {
					case 1: Graph.drawGraph(Stats.linePassneger(sel), 2250000L);
							break;
					case 2: Graph.drawGraph(Stats.linePassneger(sel), 2800000L);
						    break;
					case 3: Graph.drawGraph(Stats.linePassneger(sel), 1300000L);
							break;
					case 4: Graph.drawGraph(Stats.linePassneger(sel), 1150000L);
							break;
					case 5: Graph.drawGraph(Stats.linePassneger(sel), 1450000L);
							break;
					case 6: Graph.drawGraph(Stats.linePassneger(sel), 800000L);
							break;
					case 7: Graph.drawGraph(Stats.linePassneger(sel), 1350000L);
							break;
					case 8: Graph.drawGraph(Stats.linePassneger(sel), 450000L);
							break;
					case 9: Graph.drawGraph(Stats.linePassneger(sel), 900000L);
							break;
							
								
						
					}
					
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
				System.out.print("입력(N호선): ");
				sel = scan.nextLine();
				
				if(Validation.is_Line(sel)) {
					
					int line = Integer.parseInt(sel.charAt(0) + "");
					
			
					
					switch (line) {
					case 1: Graph.drawGraph(Stats.lineSales(sel), 600000000L);
							break;
					case 2: Graph.drawGraph(Stats.lineSales(sel), 3200000000L);
						    break;
					case 3: Graph.drawGraph(Stats.lineSales(sel), 1200000000L);
							break;
					case 4: Graph.drawGraph(Stats.lineSales(sel), 1150000000L);
							break;
					case 5: Graph.drawGraph(Stats.lineSales(sel), 1480000000L);
							break;
					case 6: Graph.drawGraph(Stats.lineSales(sel), 790000000L);
							break;
					case 7: Graph.drawGraph(Stats.lineSales(sel), 1380000000L);
							break;
					case 8: Graph.drawGraph(Stats.lineSales(sel), 440000000L);
							break;
					case 9: Graph.drawGraph(Stats.lineSales(sel), 600000000L);
							break;
						
					}
					
				}else {
					System.out.println("잘못된 입력");
				}
				
				System.out.println("다시입력하시려면 아무키나 눌러주세요.");
				System.out.println("뒤로가려면 엔터");
				System.out.print("입력: ");
				sel = scan.nextLine();
				
				if(sel.equals("")) {
					
					// 특정호선 통계 종료
					break;
				}
				
			}//while루프 종료
		}

	}//End of salesStatsTab()
	
}//End of class