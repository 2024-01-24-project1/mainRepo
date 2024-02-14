package com.java.station;
/**
 * 이용객 수를 저장하는 클래스
 */
public final class PassengerCounting {

	/**
	 * 날짜를 저장하는 멤버 변수
	 */
	private String date;
	/**
	 * 호선을 저장하는 멤버 변수
	 */
	private String line;
	/**
	 * 역 이름을 저장하는 멤버 변수
	 */
	private String station;
	/**
	 * 이용객 수를 저장하는 멤버 변수
	 */
	private long counting;
	
	/**
	 * 역 이용객 수를 저장하는 객체 생성자
	 * @param data 날짜
	 * @param line 호선
	 * @param station 역 이름
	 * @param counting 이용객 수
	 */
	public PassengerCounting(String data, String line, String station, long counting) {
		this.date = data;
		this.line = line;
		this.station = station;
		this.counting = counting;
	}
	/**
	 * 날짜를 리턴하는 메서드
	 * @return 날짜
	 */
	public String getDate() {
		return date;
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
	public String getStation() {
		return station;
	}
	/**
	 * 이용객 수를 리턴하는 메서드
	 * @return 이용객 수
	 */
	public long getCounting() {
		return counting;
	}
	
	@Override
	public String toString() {
		return String.format("승객수[날짜:%s, 호선:%s, 역이름: %s, 승객수: %s]"
							, this.date, this.line, this.station, this.counting);
	}
	
	
}//End of class
