package com.java.station.timetable;

import java.util.ArrayList;

/**
 * 역 시간표를 저장하는 클래스
 */
public final class StationTime {
	/**
	 * 호선을 저장하는 멤버 변수
	 */
	private String line;
	/**
	 * 역 이름을 저장하는 멤버 변수
	 */
	private String stationName;
	
	/**
	 * 평일 상행선 시간표를 저장하는 ArrayList
	 */
	private ArrayList<String> upNomal;
	/**
	 * 평일 하행선 시간표를 저장하는 ArrayList
	 */
	private ArrayList<String> downNomal;
	/**
	 * 주말 상행선 시간표를 저장하는 ArrayList
	 */
	private ArrayList<String> upHoliday;
	/**
	 * 주말 하행선 시간표를 저장하는 ArrayList
	 */
	private ArrayList<String> downHoliday;
	
	/**
	 * 시간표 객체를 생성하는 생성자
	 * @param line 호선(1~9)
	 * @param stationName 역이름
	 * @param upNomal 평일 상행선
	 * @param downNomal 평일 하행선
	 * @param upHoliday 주말 상행선
	 * @param downHoliday 주말 하행선
	 */
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
	/**
	 * 호선을 리턴하는 메서드
	 * @return 호선
	 */
	public String getLine() {
		return line;
	}
	/**
	 * 역 이름을 리턴하는 메서드
	 * @return 역 이름
	 */
	public String getStationName() {
		return stationName;
	}
	/**
	 * 평일 상행선 시간표를 리턴하는 메서드
	 * @return 평일 상행선 시간표
	 */
	public ArrayList<String> getUpNomal() {
		return upNomal;
	}
	/**
	 * 평일 하행선 시간표를 리턴하는 메서드
	 * @return 평일 하행선 시간표
	 */
	public ArrayList<String> getDownNomal() {
		return downNomal;
	}
	/**
	 * 주말 상행선 시간표를 리턴하는 메서드
	 * @return 주말 상행선 시간표
	 */
	public ArrayList<String> getUpHoliday() {
		return upHoliday;
	}
	/**
	 * 주말 하행선 시간표를 리턴하는 메서드
	 * @return 주말 하행선 시간표
	 */
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
