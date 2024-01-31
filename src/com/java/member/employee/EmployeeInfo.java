package com.java.member.employee;


import java.util.ArrayList;

import com.java.common.Data;
import com.java.common.MovePage;

public class EmployeeInfo {
	


	
	//전체 직원 출력
	public static void printAllEmployee() {
		
		printEmployee(Data.employeeList);
		
	}
	
	//직원 검색 후 출력
	public static void searchEmployee(String search) {
		
		Data.searchList = EmployeeManagement.search(search);
		
		printEmployee(Data.searchList);
		
		
	}
	
	//리스트 한줄로 출력
	
	public static void printEmployee(ArrayList<EmployeeManagement> list) {
		
		
		MovePage move = new MovePage();
		
		move.showPage(list);
		
				
		}
					
		
}



