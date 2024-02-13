package com.java.busy;

import java.util.ArrayList;

/**
 * 혼잡도 데이터를 읽어와서 혼잡도 정보를 저장하는 클래스
 */
public class Busy {
	
	/**
	 * 요일(평일/주말)
	 */
	private String dayOfWeek;
	/**
	 * 호선(1~9)
	 */
	private String line;
	/**
	 * 역 이름
	 */
	private String station;
	/**
	 * 방향(상행/하행 or 외선/내선)
	 */
	private String way;
	/**
	 * 혼잡도 수치 저장하는 ArrayList
	 */
	private ArrayList<Double> crowded;
	
	
	/**
	 * 혼잡도 정보를 저장하는 생성자
	 * @param dayOfWeek 요일(평일/주말)
	 * @param line 호선(1~9)
	 * @param station 역 이름
	 * @param way 방향(상행/하행 or 외선/내선)
	 * @param crowded 혼잡도 수치
	 */
	public Busy(String dayOfWeek, String line, String station, String way, ArrayList<Double> crowded) {
		
		this.dayOfWeek = dayOfWeek;
		this.line = line;
		this.station = station;
		this.way = way;
		this.crowded = crowded;
	}
	
	/**
	 * 요일을 리턴하는 메서드
	 * @return 요일(평일/주말)
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	/**
	 * 호선을 리턴하는 메서드
	 * @return 호선(1~9)
	 */
	public String getLines() {
		return line;
	}
	/**
	 * 역 이름을 리턴하는 메서드
	 * @return 역 이름
	 */
	public String getStation() {
		return station;
	}
	/**
	 *  방향을 리턴하는 메서드
	 * @return 방향(상행/하행 or 외선/내선)
	 */
	public String getWay() {
		return way;
	}
	/**
	 * 혼잡도 수치를 저장한 ArrayList를 리턴하는 메서드 
	 * @return 혼잡도 수치
	 */
	public ArrayList<Double> getCrowded() {
		return crowded;
	}
	@Override
	public String toString() {
		return String.format("Busy [dayOfWeek=%s, lines=%s, station=%s, way=%s, crowded=%s]", dayOfWeek, line, station,
				way, crowded);
	}
	
	
	
	

}
