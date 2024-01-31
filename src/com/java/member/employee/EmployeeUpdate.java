package com.java.member.employee;

import java.util.Iterator;

import com.java.common.Data;

public class EmployeeUpdate {
	
	
	
	
	//근무지 변경
	
	public static void changeStation(String id,String line,String station) {
		
		Iterator<EmployeeManagement> iter = Data.employeeList.iterator();
		while(iter.hasNext()) {
		
			
			EmployeeManagement e = iter.next();
			
			if(e.getId().equals(id)) {
				
				e.setLine(line);
				e.setStation(station);
				
			}
			
		}
		
		
	}
	
	
	//직급 변경
	
	public static void changeRank(String id, String rank) {
		
		Iterator<EmployeeManagement> iter = Data.employeeList.iterator();
		while(iter.hasNext()) {
		
			EmployeeManagement e = iter.next();
			
			if(e.getId().equals(id)) {
				e.setRank(rank);
				
			}
			
		}
		
	}
	
	
	//직원 삭제
	public static void removeEmployee(String id) {
		
		int index = -1;
		for(EmployeeManagement e : Data.employeeList) {
			
			if(e.getId().equals(id)) {
				
				index = Data.employeeList.indexOf(e);
				
			}
			
		}
		
		Data.employeeList.remove(index);
		
	}
	
	

}
