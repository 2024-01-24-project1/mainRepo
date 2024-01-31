package com.test.java;

public class CalInput {
	public String day;
	public String content;
	
	public CalInput(String day, String content) {
		this.day = day;
		this.content = content;
		
		
}
	
	public String getDay() {
		return day;
	}
	public String getContent() {
		return content;
	}
	
	public String toString() {
		return String.format("[날짜: %s] [내용: %s]\r\n",day, content);
	}
}