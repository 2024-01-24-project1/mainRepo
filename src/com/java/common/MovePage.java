package com.java.common;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.member.employee.EmployeeManagement;

//ArrayList를 받으면 String 배열로 저장해서 최대 10개씩 출력
//이전페이지, 다음페이지 입력해서 출력하는 클래스

public class MovePage {
	
	
	private String[] itemList;
	
	
	//10줄씩 분리된 String 배열 한개씩 열기
	public void showPage(ArrayList<EmployeeManagement> list) {
		
		saveList(list);
		
		int index = 0;
		boolean loop = true;
		
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println(itemList[index]);
		
		while(loop) {
			
			System.out.print("입력: ");
			String sel = sc.nextLine();
			
			if(sel.equals("q")) {
				
				if(index==0) {
					
					System.out.println("이전 페이지가 없습니다.");
					
				}else {
					
					index--;
					System.out.println(itemList[index]);
					
				}
				
				

				
			}else if(sel.equals("e")) {
				
				
				if(itemList.length-1==index) {
					
					System.out.println("마지막 페이지 입니다.");
					
				}else {
					
					index++;
					System.out.println(itemList[index]);
				}
				

				
			}else if(sel.equals("q!")) {
				
				loop = false;
				
			}
			
		}
		sc.close();
		
	}

	//매개변수로 받는 list를 10줄씩 String 배열에 저장
	private void saveList(ArrayList<EmployeeManagement> list) {
		int count = 0;
		int index = 0;
		String count10 = "";
		
		
		
		
		itemList = new String[list.size()%10==0 ? list.size()%10 : (list.size()/10)+1];
		
		for(int i=0; i<list.size(); i++) {
			
			count10 += list.get(i)+"\n";
			count++;
			
			if(count==10 || i == list.size()-1) {
				
				itemList[index] = count10;
				count10 = "";
				index++;
				count = 0;
			}
			
			
			
		}
	}
	
	

}
