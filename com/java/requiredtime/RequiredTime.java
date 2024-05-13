package com.java.requiredtime;

/**
 * 소요시간 정보를 저장하는 클래스
 */
public class RequiredTime {
	
	/**
	 * 호선 정보를 저장하는 멤버 변수
	 */
	private String line;
	/**
	 * 역 이름 정보를 저장하는 멤버 변수
	 */
	private String station;
	/**
	 * 시간 정보를 저장하는 멤버 변수
	 */
	private String time;
	
	

	/**
	 * 소요시간 클래스의 생성자
	 * @param line 호선(1~9)
	 * @param station 역이름
	 * @param time 시간대(05~24)
	 */
	public RequiredTime(String line, String station, String time) {
		this.line = line;
		this.station = station;
		this.time = time;
	}

	/**
	 * 호선 정보를 리턴하는 메서드
	 * @return 호선(1~9) String
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 역 이름을 리턴하는 메서드
	 * @return 역 이름 String
	 */
	public String getStation() {
		
		return station;
		
	}
	
	/**
	 * 시간대를 리턴하는 메서드
	 * @return 시간 String
	 */
	public String getTime() {
		
		return time;
		
	}


	@Override
	public String toString() {
		return String.format("소요시간 [station=%s, time=%s]", station, time);
	}
	
	
	
	
	

}
