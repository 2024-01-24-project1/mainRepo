package com.java.member.employee;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data;
import com.java.common.MovePage;

public class EmployeeInfo {
	


	
	//전체 직원 출력
	public static void printAllEmployee() {
		
		printEmployee(Data.employeeList);
		
	}
	
	//직원 검색 후 출력 -> 안전요원만 검색시 매개변수로 안전요원 직급 삽입
	public static void searchEmployee(String search) {
		
		Data.searchList = EmployeeManagement.search(search);
		
		printEmployee(Data.searchList);
		
		
	}
	
	//호선 또는 역 이름으로 안전요원 검색하기
	public static void searchSecurityEmployee(String search) {
		
		
		Data.searchList = EmployeeManagement.search("안전요원");
		
		List<EmployeeManagement> list = Data.searchList.stream()
															.filter(e -> e.getLine().equals(search))
															.collect(Collectors.toList());
		
		Data.searchList = (ArrayList<EmployeeManagement>) list;
		
		
	}
	
	//리스트 한줄로 출력
	
	public static void printEmployee(ArrayList<EmployeeManagement> list) {
		
		
		MovePage move = new MovePage();
		
		move.showPage(list);
		
				
	}
	
	
	
					
		
}



