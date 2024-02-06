package com.java.busy;

import java.util.ArrayList;

public class Busy {
	
	private String dayOfWeek;
	private String lines;
	private String station;
	private String way;
	private ArrayList<Double> crowded;
	

	public Busy(String dayOfWeek, String lines, String station, String way, ArrayList<Double> crowded) {
		
		this.dayOfWeek = dayOfWeek;
		this.lines = lines;
		this.station = station;
		this.way = way;
		this.crowded = crowded;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public ArrayList<Double> getCrowded() {
		return crowded;
	}
	public void setCrowded(ArrayList<Double> crowded) {
		this.crowded = crowded;
	}

	@Override
	public String toString() {
		return String.format("Busy [dayOfWeek=%s, lines=%s, station=%s, way=%s, crowded=%s]", dayOfWeek, lines, station,
				way, crowded);
	}
	
	
	
	

}
