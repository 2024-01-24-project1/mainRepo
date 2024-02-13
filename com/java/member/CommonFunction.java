package com.java.member;

import com.java.station.timetable.StationTimeTab;
import com.java.view.ViewAll;

/**
 * 공통기능인 역 시간표와 요금표를 나타내는 메서드
 */
public class CommonFunction {
	
	// 역 시간표 탭
	/**
	 * 역 시간표 메뉴를 나타내는 메서드
	 */
	protected static void stationTimetableTab() {
			
			ViewAll.clear();
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			StationTimeTab.stationTimeTab();

	}//stationTimetableTab()
	
	// 요금표 탭
	/**
	 * 요금표 메뉴를 나타내는 메서드
	 */
	public static void costsTableTab(){
		
		ViewAll.costTimeTable();
		ViewAll.pause();
		
	}//End of CostsTableTab()
	
}//End of class
