package com.java.member.user;

import java.util.ArrayList;

/**
 * 즐겨찾기 정보를 저장하는 클래스
 */
public class BookMark {

	/**
	 * BookMark클래스의 기본 생성자
	 */
	public BookMark() {
	}
	
	/**
	 * 즐겨찾기한 id를 저장하는 멤버 변수
	 */
	private String id;
	/**
	 * 즐겨찾기 노선을 저장하는 ArrayList 멤버 변수
	 */
	private ArrayList<String> bookMarkList = new ArrayList<>(); //시작역,도착역,시간 String 저장
	
	/**
	 * 즐겨찾기 노선 클래스의 생성자
	 * @param id 고객의 아이디
	 */
	public BookMark(String id) {
		this.id = id;
	}
	
	/**
	 * 고객의 id를 리턴하는 메서드
	 * @return 고객의 아이디
	 */
	public String getId() {
		return id;
	}
	/**
	 * 고객의 즐겨찾기 목록을 리턴하는 메서드
	 * @return 고객의 즐겨찾기 목록 ArrayList of String
	 */
	public ArrayList<String> getBookMarkList() {
		return bookMarkList;
	}
	
	/**
	 * 경로를 매개변수로 받아 즐겨찾기 목록에 추가하는 메서드
	 * @param route 해당 경로
	 */
	public void setBookMarkList(String route) {
		this.bookMarkList.add(route);		
	}
	
	
	
	
	
	

}
