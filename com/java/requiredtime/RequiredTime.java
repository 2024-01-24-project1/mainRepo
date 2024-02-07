package com.java.requiredtime;

public class RequiredTime {
	
	private String line;
	private String station;
	private String time;
	
	

	public RequiredTime(String line, String station, String time) {
		this.line = line;
		this.station = station;
		this.time = time;
	}

	public String getLine() {
		return line;
	}

	public String getStation() {
		
		return station;
		
	}
	
	
	public String getTime() {
		
		return time;
		
	}


	@Override
	public String toString() {
		return String.format("소요시간 [station=%s, time=%s]", station, time);
	}
	
	
	
	
	

}
