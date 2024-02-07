package com.java.member;

import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.station.timetable.StationTimeTab;

import com.java.view.ViewAll;

public class CommonFunction {
	
	// 역 시간표 탭
	protected static void stationTimetableTab() {
		
			StationTimeTab.stationTimeTab();

	}//stationTimetableTab()
	
	// 요금표 탭
	public static void costsTableTab(){
		
		ViewAll.costTimeTable();
		ViewAll.pause();
		
	}//End of CostsTableTab()
	
}//End of class
