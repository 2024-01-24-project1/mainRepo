package com.java.station.timetable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;

public class StationTimePage {

	public static void stationTimePage(String line, String stationName, String weekOf) {
		// 리스트의 페이지수 계산
		int page = 20;
		int index = 0;		// 문자로 입력받은 숫자를 int로 변환
		
		ArrayList<String> up = new ArrayList<String>();
		ArrayList<String> down = new ArrayList<String>();
		
		for(StationTime time : Data.stationTimeList) {
			
			if(time.equals(line) && time.equals(stationName)) {
				
				if(weekOf.equals("평일")) {
					
					up = time.getUpNomal();
					down = time.getDownNomal();
					
					// 객체 탐색 종료
					break;
					
				}else if (weekOf.equals("주말")) {
					
					up = time.getUpHoliday();
					down = time.getDownHoliday();
					
					// 객체 탐색 종료
					break;
				}
				
			}
				
		}
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			String sel = "";	// 입력받는 문자열
			
			// View클래스 출력
			System.out.println("======================================================");
			System.out.printf("                  %s %s역 시간표\r\n", line, stationName);
			System.out.println("======================================================");
			
			up.stream().filter(time -> time.contains(":"))
					   .skip(index * 10)
			 							 .limit(20)
			 							 .forEach(time -> System.out.printf("%-8s|%-20s|%-2s|%-3s - %-10s|%-13s\r\n"
					 													, time));
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("Page| %s / %s\r\n", index + 1, page);
			System.out.print("엔터입력시 종료");
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
		
		
		
		
	}//End of stationTimePage()
	
}//End of class
