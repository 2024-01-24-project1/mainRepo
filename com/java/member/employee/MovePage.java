package com.java.member.employee;

import java.util.ArrayList;

import java.util.Scanner;


//ArrayList를 받으면 String 배열로 저장해서 최대 10개씩 출력
//이전페이지, 다음페이지 입력해서 출력하는 클래스
/**
 * ArrayList를 전달받아 10개씩 나눠서 출력하며 이전페이지와 다음페이지를 사용할 수 있는 클래스
 * 
 */
public class MovePage {
	
	
//	private String[] itemList;
	private ArrayList<String> itemList;
	
	//10줄씩 분리된 ArrayList 한개씩 열기
	/**
	 * 관리자 객체 정보를 한번에 10줄씩 출력하며 q를 누르면 이전 10개 정보를 출력하고 e를 누르면 다음 10개의
	 * 관리자 객체 정보를 출력한다.
	 * @param list 10개씩 나눠서 출력하고 싶은 ArrayList를 전달
	 */
	public <T> void showPage(ArrayList<T> list) {
		
		saveList(list);
		
		int index = 0;
		boolean loop = true;
		
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println(itemList.get(index));
		
		while(loop) {
			
			System.out.println("이전페이지 q. ");
			System.out.println("다음페이지 e. ");
			System.out.println("탐색종료 q!. ");
			System.out.print("입력: ");
			String sel = sc.nextLine();
			
			if(sel.equals("q")) {
				
				if(index==0) {
					
					System.out.println("이전 페이지가 없습니다.");
					
				}else {
					
					index--;
					System.out.println(itemList.get(index));
					
				}
				
				

				
			}else if(sel.equals("e")) {
				
				
				if(itemList.size()-1==index) {
					
					System.out.println("마지막 페이지 입니다.");
					
				}else {
					
					index++;
					System.out.println(itemList.get(index));
				}
				

				
			}else if(sel.equals("q!")) { //뒤로가기 만들어야함
				
				loop = false;
				
			}else {
				
				
				
			}
			
		}
		
		
		
		
	}

	//매개변수로 받는 list를 10줄씩 String 배열에 저장
	/**
	 * ArrayList에 저장된 관리자 객체를 10개씩 나눠서 새로운 itemList에 저장
	 * @param list 10개씩 나눌 리스트
	 */
	private <T> void saveList(ArrayList<T> list) {
		
		int count = 1;
		int index = 0;
		String divideTen = "";
		
		itemList = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			
			//divideTen = divideTen + count + ". |" + (list.get(i) + "");
			divideTen += String.format("%2d. | %s", count, list.get(i));
			
			if(count % 10 == 0 || i == list.size()-1) {
				itemList.add(divideTen);
				divideTen = "";
				index++;
			}
			count++;
		}
	}
	
	

}
