package com.java.member.employee;

import java.util.Iterator;

import com.java.common.Data;

public class EmployeeUpdate {
	
	public String[] rank = {"사원","대리","과장","부장","최고관리자"}; // Data클래스로 이전해야함
	
	
	//근무지 변경
	
	public static void changeStation(String id,String line,String station) {
		
		Iterator<EmployeeManagement> iter = Data.employeeList.iterator();
		while(iter.hasNext()) {
		
			
			EmployeeManagement e = iter.next();
			
			if(e.getId().equals(id)) {
				
				e.setLine(line); // -> line 1~9입력 or 1~9입력 받으면 +호선해서 입력
				e.setStation(station); // -> 잠실 or 잠실역 입력 contains "역" 없으면 +"역" 추가
				
			}
			
		}
		
		
	}
	
	
	//직급 변경
	
	public static void changeRank(String id, String rank) {
		
		
		
		
		Iterator<EmployeeManagement> iter = Data.employeeList.iterator();
		while(iter.hasNext()) {
		
			EmployeeManagement e = iter.next();
			
			if(e.getId().equals(id)) {
				e.setRank(rank);        //rank가 있는 직급인지 확인 -> 유효성검사
				
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
