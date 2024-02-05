package com.java.member.employee;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.view.View;

public class SafetyManTab {

		
			
	
	
	protected static void satetyManTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			String sel = ""; // 선택한 번호
			
			ArrayList<Employee> safetyList = new ArrayList<>();
			ArrayList<Employee> noAreaSafetyList = new ArrayList<>();
			
			for(Employee employee : Data.employeeList) {
				
				if(employee.getPosition().equals("안전요원")) {
					safetyList.add(employee);
					
					if(employee.getLine().equals("미정")) {
						noAreaSafetyList.add(employee);
					}
					
				}
				
			}
			
			
			// View클래스 출력
			System.out.println("=======================================");
			System.out.printf("             안전요원 관리          ");
			System.out.println(LoginLogout.position + " " + LoginLogout.auth + "님");
			System.out.println("=======================================");
			System.out.println("           1. 전체 안전요원 보기");
			System.out.println("           2. 안전요원 배치");
			System.out.println("           3. 안전요원 부서 해제");
			System.out.println("           4. 뒤로가기");
			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 전체 안전요원 보기
				EmployeeSearch.employeeAll(safetyList);
			} else if(sel.equals("2")) {	// 2. 안전요원 배치
				arrangeSafetyMan(noAreaSafetyList);
			} else if (sel.equals("3")) {	// 3. 안전요원 부서 해제
				safetyManWorkAreaClear(safetyList);
			} else if (sel.equals("4")) {	// 4. 뒤로가기
				
				// 직원관리 종료
				break;
					
			} else { // 이외의 숫자 입력 시
					System.out.println("해당 섹션이 없습니다.");
					System.out.println("다시 입력해주세요.");
			}
			
		}//while루프 종료
		
	}//End of satetyManTab()

	private static void safetyManWorkAreaClear(ArrayList<Employee> safetyList) {
		
		String input = "";
		
		Scanner scan = new Scanner(System.in);
		
		EmployeeSearch.employeePage(safetyList);
		
		System.out.println("해제시킬 안전요원의 아이디를 입력하세요");
		System.out.printf("아이디: ");
		input = scan.nextLine();
		
		if( Validation.is_SafetyMan(input) && (!Validation.is_WorkArea(input)) ) {
			
			for(Employee employee : Data.employeeList) {
				
				if(employee.getId().equals(input)) {
					
					employee.setLine("미정");
					employee.setStation("미정");
					
					// Employee객체 탐색종료
					break;
				}
				
			}
			
			System.out.println("안전요원 부서 해제 완료");
			
		} else if ( Validation.is_SafetyMan(input) && Validation.is_WorkArea(input) ) {
			System.out.println("입력한 안전요원은 아직 배치되지 않았습니다.");
		} else {
			System.out.println("입력한 아이디의 안전요원이 없습니다.");
		}
		
		System.out.println("안전요원 부서해제 종료");
		View.pause();
		
	}//End of safetyManWorkAreaClear()
	
	private static void arrangeSafetyMan(ArrayList<Employee> list) {
		
		String input = "";
		
		Scanner scan = new Scanner(System.in);
		
		EmployeeSearch.employeePage(list);
		
		System.out.println("배치할 안전요원의 아이디를 입력하세요");
		System.out.printf("아이디: ");
		input = scan.nextLine();
		
		if( Validation.is_SafetyMan(input) && Validation.is_WorkArea(input) ) {
			
			EmployeeUpdateTab.updateWorkArea(input);
			
			System.out.println("안전요원 배치 완료");
			
		} else if ( Validation.is_SafetyMan(input) && !(Validation.is_WorkArea(input)) ) {
			System.out.println("입력한 안전요원은 이미 배치되있습니다.");
		} else {
			System.out.println("입력한 아이디의 안전요원이 없습니다.");
		}
		
		System.out.println("안전요원 배치 종료");
		View.pause();
		
	}//End of arrangeSafetyMan()
	
	
}//End of SafetyManTab