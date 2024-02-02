package com.java.member.employee;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data3;
import com.java.common.MovePage;
/**
 * 관리자의 정보를 출력메서드로 전달하는 클래스
 */
public class EmployeeInfo {
	


	
	//전체 직원 출력
	/**
	 * 현재 관리자 정보가 저장된 파일을 전부 출력하는 메서드
	 */
	public static void printAllEmployee() {
		
		printEmployee(Data3.employeeList);
		
	}
	
	//직원 검색 후 출력 -> 안전요원만 검색시 매개변수로 안전요원 직급 삽입
	/**
	 * 매개변수인 search가 포함된 관리자 객체만 searchList에 저장하는 메서드
	 * @param search 검색어
	 */
	public static void searchEmployee(String search) {
		
		Data3.searchList = EmployeeManagement.search(search);
		
		
		printEmployee(Data3.searchList);
		
		
	}
	
	//안전요원 미배치목록만 보는 메뉴는 필요할까?
	//호선 또는 역 이름으로 안전요원 검색하기
	/**
	 * 직급이 안전요원인 관리자 중에서 호선 또는 역 이름으로 검색하여 검색된 관리자 객체만 저장하는 메서드
	 * @param search
	 */
	public static void searchSecurityEmployee(String search) {
		
		
		Data3.searchList = EmployeeManagement.search("안전요원");
		
		
		
		
		
		
		
		
		List<EmployeeManagement> list = Data3.searchList.stream()
															.filter(e -> e.getLine().equals(search) || e.getStation().equals(search))
															.collect(Collectors.toList());
		
		Data3.searchList = (ArrayList<EmployeeManagement>) list;
		
		printEmployee(Data3.searchList);

		
	}
	
	//리스트 한줄로 출력
	
	/**
	 * 지금껏 저장된 관리자 정보를 출력하는 메서드
	 * @param list 출력하고 싶은 ArrayList
	 */
	public static void printEmployee(ArrayList<EmployeeManagement> list) {
		
		
		MovePage move = new MovePage();
		
		move.showPage(list);
		
				
	}
	
	
	
					
		
}



