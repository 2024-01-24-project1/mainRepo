package com.java.station.timetable;

import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.station.StationNamePage;

import com.java.view.ViewAll;

public class StationTimeTab {
	
	
	
	public static void stationTimeTab() {
		Scanner scan = new Scanner(System.in);
		ViewAll.trainTimeTable();
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
				
				final String LINE = sel;
				int line = Integer.parseInt(sel.charAt(0) + "");
				
				switch (line) {
				
				case 1: StationNamePage.stationNamePage(Data.LINE1_STATION_NAME, LINE);
						break;
				case 2: StationNamePage.stationNamePage(Data.LINE2_STATION_NAME, LINE);
						break;
				case 3: StationNamePage.stationNamePage(Data.LINE3_STATION_NAME, LINE);
						break;
				case 4: StationNamePage.stationNamePage(Data.LINE4_STATION_NAME, LINE);
						break;
				case 5: StationNamePage.stationNamePage(Data.LINE5_STATION_NAME, LINE);
						break;
				case 6: StationNamePage.stationNamePage(Data.LINE6_STATION_NAME, LINE);
						break;
				case 7: StationNamePage.stationNamePage(Data.LINE7_STATION_NAME, LINE);
						break;
				case 8: StationNamePage.stationNamePage(Data.LINE8_STATION_NAME, LINE);
						break;
				case 9: StationNamePage.stationNamePage(Data.LINE9_STATION_NAME, LINE);
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
				ViewAll.statisticsChaosThree();
				weekOf =scan.nextLine();
				
				// 맞으면 해당 호선의 역 시간표 출력
				
				if(check && Validation.is_WeekOf(weekOf)) { // 호선과 역이름이 맞을경우
					ViewAll.trainTimeTableSearch();
					StationTimePage.stationTimePage(sel, STATION, weekOf);
					
				}else if ( !Validation.is_WeekOf(weekOf)) {	// 주말,평일 잘못 입력한 경우
					ViewAll.dayError();
					ViewAll.pause();
				}
				else {	  									// 역이름이 호선과 틀릴경우
					System.out.println("잘못된 역이름 또는 호선에 맞지않는 역이름");
					ViewAll.hoLineError();
					ViewAll.pause();
				}
				
			}else {
				ViewAll.lineError();
				ViewAll.pause();
			}
			
		}//while루프 종료
		
	}//End of stationTimeTab()
	
}//End of class
