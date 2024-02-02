package com.test.java.stat;
import java.util.ArrayList;
import java.util.Scanner;


///////// 유효성 검사랑 1번 혼잡도 통계 추가해야함

public class Static {
	
	public static void main(String[] args) {
		
	int month=1;
	boolean loop = true;
	Scanner scan=new Scanner(System.in);
	
	while (loop) {
		
		System.out.println("원하는 기능을 선택하세요.");
		System.out.println("1. 혼잡도 통계");
		System.out.println("2. 이용객 통계");
		System.out.println("3. 매출 통계");
		
	
		
		
		String sel = scan.nextLine();
		
		if(sel.equals("1")) { //1. 혼잡도 통계 >> 추가 예정
			
			
		}else if(sel.equals("2")) { //"2. 이용객 통계"
			System.out.println("1. 월 전체 이용 인원");
			System.out.println("2. 특정 호선 이용 인원");
			System.out.println("3. 호선별 이용 인원");
			sel = scan.nextLine();
			
			if(sel.equals("1")) { //이용객 통계 > 월 전체
				long[] asd = CongestionStat.TotalCal("0");
				CongestionStat.sum(asd, month);
				
			}else if(sel.equals("2")) { //이용객 통계>특정 호선
				System.out.print("호선을 입력하세요: ");
				String lines = scan.nextLine();
				long[] asd = CongestionStat.TotalCal(lines);
				System.out.println(lines+"호선");
				CongestionStat.sum(asd, month);
				
			}else if(sel.equals("3")) { //이용객 통계>호선별 달 이용객 합계
				ArrayList<Long> list = YearWon.sumYear();
				for(int i=1; i<list.size(); i++) {
					
					System.out.printf("%d호선 이용객 합계: %,d명\n", i, list.get(i));
				
////					SalesStat.Cal();
				}
				}
			
		}else if(sel.equals("3")) { //"3. 매출 통계"
			System.out.println("1. 전체 호선 월 매출");
			System.out.println("2. 특정 호선 월 매출");
			System.out.println("3. 호선별 전체 매출 ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) { //매출 통계 > 전체 노선 합계
				
				long[] asd = CongestionStat.TotalCal("0");
				CongestionStat.ChooseCal(asd);
				
				
			}else if(sel.equals("2")) { //매출 통계 > 선택 노선 통계
				System.out.print("호선을 입력하세요: ");
				String lines=scan.nextLine();
				System.out.println(lines+"호선");
				long[] asd = CongestionStat.TotalCal(lines);
				CongestionStat.ChooseCal(asd);
			}else if(sel.equals("3")) { //매출 통계 > 호선별 연매출
				ArrayList<Long> list = YearWon.sumYear();
				
				for(int i=1; i<list.size(); i++) {
					
					System.out.printf("%d호선 연 매출: %,.0f원\n", i,list.get(i)*(1400*0.75+800*0.1));
					}
				
				
	
			}
			
		}else {
			System.out.println("다시 입력하십시오");
			loop=true;
		}
		
		}
	}
	}
	


