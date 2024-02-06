package com.java.station;

public final class PassengerCounting {

	private String date;
	private String line;
	private String station;
	private long counting;
	
	public PassengerCounting(String data, String line, String station, long counting) {
		this.date = data;
		this.line = line;
		this.station = station;
		this.counting = counting;
	}
	
	public String getDate() {
		return date;
	}
	public String getLine() {
		return line;
	}
	public String getStation() {
		return station;
	}
	public long getCounting() {
		return counting;
	}
	
	@Override
	public String toString() {
		return String.format("승객수[날짜:%s, 호선:%s, 역이름: %s, 승객수: %s]"
							, this.date, this.line, this.station, this.counting);
	}
	
	
}//End of class
