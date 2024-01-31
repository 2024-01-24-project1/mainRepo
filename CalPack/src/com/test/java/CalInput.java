package com.test.java;

public class CalInput {
	//날짜 입력 , 내용 입력
	public String day; //날짜  
	public String content; //내용
	
	
	public CalInput(String day, String content) {
		this.day = day; //날짜
		this.content = content; //내용
 		
		
}
	
	public String getDay() {
		return day;
	}
	public String getContent() {
		return content;
	}
	
	

	//오버라이딩 , 서식
	public String toString() {
		return String.format("날짜: %s,내용: %s\r\n",day, content);
	}
}