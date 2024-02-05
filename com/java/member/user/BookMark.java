package com.java.member.user;

import java.util.ArrayList;

public class BookMark {
	
	private String id;
	private ArrayList<String> bookMarkList = new ArrayList<>(); //시작역,도착역,시간 String 저장
	
	public BookMark(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public ArrayList<String> getBookMarkList() {
		return bookMarkList;
	}

	public void setBookMarkList(String route) {
		this.bookMarkList.add(route);		
	}
	
	
	
	
	
	

}
