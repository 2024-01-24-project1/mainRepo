package com.java.schedule;

import java.util.Comparator;

// 일정객체 클래스
public final class Schedule {
	
	private String time;			// 시각
	private String station;			// 역
	private String schedule;		// 일정
	
	public Schedule(String time, String station, String schedule) {
		
		this.time = time;
		this.station = station;
		this.schedule = schedule;
		
	}
	
	public String getTime() {
		return time;
	}

	public String getStation() {
		return station;
	}

	public String getSchedule() {
		return schedule;
	}

	@Override
	public String toString() {
		return String.format("Schedule [시각: %s, 역: %s, 일정: %s]\r\n"
							, this.time, this.station, this.schedule);
	}
	
	public static Comparator<Schedule> timeComparator = new Comparator<Schedule>() {
		
		@Override
		public int compare(Schedule s1, Schedule s2) {
			return s1.getTime().compareTo(s2.getTime());
		}
		
	};
	
	
}//End of class
