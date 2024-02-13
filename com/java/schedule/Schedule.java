package com.java.schedule;

import java.util.Comparator;

// 일정객체 클래스
/**
 * 일정을 저장하는 클래스
 */
public final class Schedule {
	
	/**
	 * 시각을 저장하는 멤버 변수
	 */
	private String time;			// 시각
	/**
	 * 역 이름을 저장하는 멤버 변수
	 */
	private String station;			// 역
	/**
	 * 일정을 저장하는 멤버 변수
	 */
	private String schedule;		// 일정
	
	/**
	 * 일정 객체 생성자
	 * @param time 시각
	 * @param station 역 이름
	 * @param schedule 일정
	 */
	public Schedule(String time, String station, String schedule) {
		
		this.time = time;
		this.station = station;
		this.schedule = schedule;
		
	}
	/**
	 * 시각을 리턴하는 메서드
	 * @return 시각
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 역 이름을 리턴하는 메서드
	 * @return 역 이름
	 */
	public String getStation() {
		return station;
	}
	/**
	 * 일정을 리턴하는 메서드
	 * @return 일정
	 */
	public String getSchedule() {
		return schedule;
	}

	@Override
	public String toString() {
		return String.format("Schedule [시각: %s, 역: %s, 일정: %s]\r\n"
							, this.time, this.station, this.schedule);
	}
	
	/**
	 * 시간을 기준으로 정렬하는 메서드
	 */
	public static Comparator<Schedule> timeComparator = new Comparator<Schedule>() {
		
		@Override
		public int compare(Schedule s1, Schedule s2) {
			return s1.getTime().compareTo(s2.getTime());
		}
		
	};
	
	
}//End of class
