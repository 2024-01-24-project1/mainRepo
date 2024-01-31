package mainRepo.com.java.calendar;

public class Calendar {
	
	private String time;
	private String line;
	private String station;
	private String schedule;
	
	public Calendar(String time, String line, String station, String schedule) {
		
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
		return String.format("Calendar [시각: %s, 호선: %s, 역: %s, 일정: %s]\r\n"
							, this.time, this.line, this.station, this.schedule);
	}
	
	
	
	
	
	
}//End of class
