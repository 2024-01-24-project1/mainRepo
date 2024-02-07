package com.java.station.timetable;

import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.view.View;

public class StationTimeTab {
	
	
	
	public static void stationTimeTab() {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			boolean check = false;	// 호선을 제대로 입력했는지
			
			String staion = "";		// 입력받을 역 이름
			String sel = "";		// 입력받을 호선
			String weekOf = "";		// 평일,주말 입력받기
			
			System.out.println("시간표를 그만보시려면 엔터입력");
			System.out.println("호선을 입력해주세요");
			System.out.print("입력(N호선): ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				
				// 시간표탭 종료
				break;
				
			}
			
			
			// 제대로된 호선을 입력받으면 해당 호선의 역 이름들을 출력
			if(Validation.is_Line(sel)) {
				
				int line = Integer.parseInt(sel.charAt(0) + "");
				
				switch (line) {
					case 1: Data.LINE1_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 2: Data.LINE2_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 3: Data.LINE3_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 4: Data.LINE4_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 5: Data.LINE5_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 6: Data.LINE6_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 7: Data.LINE7_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 8: Data.LINE8_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					case 9: Data.LINE9_STATION_NAME.stream().forEach(name -> System.out.println(name));
					break;
					
				}
				
				System.out.println("역 이름: ");
				staion = scan.nextLine();
				
				if (staion.endsWith("역")) {
		            // '역'을 제거한 문자열을 반환합니다.
		            staion =  staion.substring(0, staion.length() - 1);
		        }
				
				final String STATION = staion;
				
				switch (line) {
				
				case 1: check = Data.LINE1_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 2: check = Data.LINE2_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 3: check = Data.LINE3_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 4: check = Data.LINE4_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 5: check = Data.LINE5_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 6: check = Data.LINE6_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 7: check = Data.LINE7_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 8: check = Data.LINE8_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
				case 9: check = Data.LINE9_STATION_NAME.stream().anyMatch(name -> name.equals(STATION));
						break;
					
				}	
				
				// 평일인지 주말인지 입력받기
				System.out.print("평일과 주말중에 어느 시간표를 보시겠습니까?");
				weekOf =scan.nextLine();
				
				// 맞으면 해당 호선의 역 시간표 출력
				
				if(check && Validation.is_WeekOf(weekOf)) { // 호선과 역이름이 맞을경우
					
					StationTimePage.stationTimePage(sel, STATION, weekOf);
					
				}else if ( !Validation.is_WeekOf(weekOf)) {	// 주말,평일 잘못 입력한 경우
					System.out.println("주말,평일을 잘못 입력하셨습니다.");
					View.pause();
				}
				else {	  									// 역이름이 호선과 틀릴경우
					System.out.println("잘못된 역이름 또는 호선에 맞지않는 역이름");
					View.pause();
				}
				
			}else {
				System.out.println("잘못된 호선");
				View.pause();
			}
			
		}//while루프 종료
		
	}//End of stationTimeTab()
	
}//End of class
