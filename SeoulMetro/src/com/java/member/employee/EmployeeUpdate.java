package com.java.member.employee;

import java.util.Iterator;
import java.util.Scanner;

import com.java.common.Data3;

public class EmployeeUpdate {
	
	public String[] rank = {"사원","대리","과장","부장","최고관리자"}; // Data클래스로 이전해야함
	
	
	//정보 변경
	/**
	 * id를 입력하기 전 관리자의 정보를 먼저 불러오고 출력한다.
	 * q!를 입력해 탐색 종료를 하면
	 * 변경하고 싶은 관리자의 id를 입력받고 변경할 직급 or 역, 호선을 입력하고 수정한다.
	 * 
	 * @param id 관리자의 id
	 * @param line 변경하고 싶은 호선
	 * @param station 변경하고 싶은 역 이름
	 */
	public static void changeInfo(String id, String select) {
		
		Scanner sc = new Scanner(System.in);
		
		Iterator<EmployeeManagement> iter = Data3.employeeList.iterator();
		while(iter.hasNext()) {
		
			
			EmployeeManagement e = iter.next();
			
			if(e.getId().equals(id)) {
				
				printInfo(e);
				
				if(select.equals("1")) {	//근무지 변경
					
					
					System.out.print("변경할 호선: ");
					String line  = sc.nextLine();
					
					System.out.print("변경할 역 이름: ");
					String station = sc.nextLine();
					
					
					e.setLine(line); // -> line 1~9입력 or 1~9입력 받으면 +호선해서 입력
					e.setStation(station); // -> 잠실 or 잠실역 입력 contains "역" 없으면 +"역" 추가

				}else if(select.equals("2")) { //직급 변경
					
					System.out.println("변경할 직급: ");
					String rank = sc.nextLine();
					
					e.setRank(rank);
					
				}else if(select.equals("3")) { //안전요원 용
					
					e.setLine("미배치");;
					e.setStation("미배치");
					
				}
				
				
			}
			
			if(e.getId().equals(id)) {
				
				EmployeeManagement e1 = searchId(id);
				System.out.println("변경 후");
				printInfo(e1);
				
			}
			
		}
		
		
	}
	
	/**
	 * 관리자 id로 검색후 해당 관리자의 정보를 출력하는 메서드
	 * 이름,직급,호선,역 순으로 출력한다.
	 * 
	 * @param 관리자 객체
	 */
	private static void printInfo(EmployeeManagement e) {
		
		String temp = String.format("이름: %s\n"
								  + "직급: %s\n"
								  + "호선: %s\n"
								  + "역  : %s\n ",e.getName(),e.getRank(),e.getLine(),e.getStation());
		
		System.out.println(temp);
		
	}
	
	//id검색후 객체 반환해 주는 메서드
	/**
	 * id를 입력하면 관리자 객체를 탐색하여 같은 id를 찾고 그 객체를 반환.
	 * @param id 관리자의 ID
	 * @return 같은 id를 가진 관리자를 반환한다.
	 */
	public static EmployeeManagement searchId(String id) {
		
		for(EmployeeManagement e : Data3.employeeList) {
			
			if(e.getId().equals(id)) {
				
				return e;
				
			}
			
		}
		return null;
		
		
	}


	//직급 변경
	/**
	 * 변경하고 싶은 관리자의 id를 입력받고 변경될 직급을 입력한다.
	 * @param id 관리자의 id
	 * @param rank 변경 될 직급
	 */
//	public static void changeRank(String id, String rank) {
//		
//		Iterator<EmployeeManagement> iter = Data.employeeList.iterator();
//		while(iter.hasNext()) {
//		
//			EmployeeManagement e = iter.next();
//			
//			if(e.getId().equals(id)) {
//				
//				printInfo(e);
//				
//				e.setRank(rank);        //rank가 있는 직급인지 확인 -> 유효성검사
//				
//			}
//			
//		}
//		
//	}
	
	
	//직원 삭제
	/**
	 * 관리자의 id를 입력하면 그 관리자의 정보를 삭제한다.
	 * @param id 삭제하고 싶은 관리자의 아이디
	 */
	public static void removeEmployee(String id) {
		
		int index = -1;
		for(EmployeeManagement e : Data3.employeeList) {
			
			if(e.getId().equals(id)) {
				
				index = Data3.employeeList.indexOf(e);
				
			}
			
		}
		
		Data3.employeeList.remove(index);
		
	}
	
	

}
