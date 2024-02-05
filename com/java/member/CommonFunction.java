package com.java.member;

import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.view.View;

public class CommonFunction {
	
	// 역 시간표 탭
	protected static void stationTimetableTab() {
		
			String line = "";
			String stationName = "";
			String weekOf = "";
			String back = "";	 
		
			Scanner scan = new Scanner(System.in);
		
			// 제대로된 호선과 시간표를 입력받기
			while(true) {
				
				System.out.println("시간표");
				System.out.print("호선: ");
				line = scan.nextLine();
				System.out.print("역: ");
				stationName = scan.nextLine();
				System.out.print("평일/주말: ");
				weekOf = scan.nextLine();
				
				
				if( !Validation.is_Line(line)) {
					System.out.println("잘못된 호선입니다.");
				}
				if( !Validation.is_StationName(stationName)) {
					System.out.println("잘못된 역 이름입니다.");
				}
				if( !(weekOf.equals("평일") || weekOf.equals("주말") )) {
					System.out.println("평일/주말 중 하나만 적어주세요.");
				}
				// 유효성 검사가 제대로 됬을 경우
				
				
				
				// 안됬을 경우
				
				
				
			}// while루프 종료

	}//stationTimetableTab()
	
	
	// 요금표 탭
	public static void costsTableTab(){
		
		View.costTimeTable();
		View.pause();
		
	}//End of CostsTableTab()
	
}//End of class
