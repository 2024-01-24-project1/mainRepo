package com.java.schedule;

// 일정객체 클래스
public final class Schedule {
	
	private String time;			// 시각
	private String line;			// 호선
	private String station;			// 역
	private String schedule;		// 일정
	
	public Schedule(String time, String line, String station, String schedule) {
		
		this.time = time;
		this.line = line;
		this.station = station;
		this.schedule = schedule;
		
	}
	
	public String getTime() {
		return time;
	}

	public String getLine() {
		return line;
	}

	public String getStation() {
		return station;
	}

	public String getSchedule() {
		return schedule;
	}

	@Override
	public String toString() {
		return String.format("Schedule [시각: %s, 호선: %s, 역: %s, 일정: %s]\r\n"
							, this.time, this.line, this.station, this.schedule);
	}
	
	
	
}//End of class
