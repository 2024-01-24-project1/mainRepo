package com.java.stationtime;

import java.util.ArrayList;

public final class StationTime {
	
	private String line;
	private String stationName;
	
	private ArrayList<String> upNomal;
	private ArrayList<String> downNomal;
	private ArrayList<String> upHoliday;
	private ArrayList<String> downHoliday;
	
	public StationTime(String line, String stationName
			, ArrayList<String> upNomal, ArrayList<String> downNomal
			, ArrayList<String> upHoliday, ArrayList<String> downHoliday) {
		this.line = line;
		this.stationName = stationName;
		this.upNomal = upNomal;
		this.downNomal = downNomal;
		this.upHoliday = upHoliday;
		this.downHoliday = downHoliday;
	}
	
	public String getLine() {
		return line;
	}
	public String getStationName() {
		return stationName;
	}
	public ArrayList<String> getUpNomal() {
		return upNomal;
	}
	public ArrayList<String> getDownNomal() {
		return downNomal;
	}
	public ArrayList<String> getUpHoliday() {
		return upHoliday;
	}
	public ArrayList<String> getDownHoliday() {
		return downHoliday;
	}
	@Override
	public String toString() {
		return String.format(
				"호선: %s, 역이름: %s\r\n평일|상행: %s\r\n평일|하행:%s\r\n주말|상행:%s\r\n주말|하행:%s\r\n"
					, this.line, this.stationName
					, this.upNomal, this.downNomal
					, this.upHoliday, this.downHoliday);
	}
	
	
	
	
}//End of class
