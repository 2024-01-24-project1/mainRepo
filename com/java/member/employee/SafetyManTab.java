package com.java.member.employee;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.view.ViewAll;

/**
 * 안전요원 관리 메뉴를 나타내는 클래스
 */
public class SafetyManTab {
	
	/**
	 * 안전요원 관리 메뉴를 나타내는 메서드
	 */
	protected static void safetyManTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = ""; // 선택한 번호
			
			/**
			 * 안전요원 목록을 저장하는 ArrayList
			 */
			ArrayList<Employee> safetyList = new ArrayList<>();
			/**
			 * 안전요원 중 미배치 안전요원만 저장하는 ArrayList
			 */
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
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			System.out.printf("\t\t\t   안전요원 관리       ");
			System.out.println("\t\t\t" + LoginLogout.position + " " + LoginLogout.auth + "님");
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			System.out.println("\t\t\t  1. 전체 안전요원 보기");
			System.out.println("\t\t\t  2. 안전요원 배치");
			System.out.println("\t\t\t  3. 안전요원 부서 해제");
			System.out.println("\t\t\t     뒤로가기 엔터");
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			ViewAll.chooseNum();
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 전체 안전요원 보기
				EmployeeSearch.employeeAll(safetyList);
			} else if(sel.equals("2")) {	// 2. 안전요원 배치
				arrangeSafetyMan(noAreaSafetyList);
			} else if (sel.equals("3")) {	// 3. 안전요원 부서 해제
				safetyManWorkAreaClear(safetyList);
			} else if (sel.equals("")) {	//    뒤로가기
				
				// 직원관리 종료
				break;
					
			} else { // 이외의 숫자 입력 시
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
			}
			
		}//while루프 종료
		
	}//End of satetyManTab()

	/**
	 * 안전요원 부서 해제 시키는 메서드
	 * @param safetyList 안전요원 목록
	 */
	private static void safetyManWorkAreaClear(ArrayList<Employee> safetyList) {
		/**
		 * 입력한 값을 저장하는 변수
		 */
		String input = "";
		
		Scanner scan = new Scanner(System.in);
		
		EmployeeSearch.employeePage(safetyList);
		
		System.out.println("\t\t\t해제시킬 안전요원의 아이디를 입력하세요");
		System.out.printf("\t\t\t아이디: ");
		input = scan.nextLine();
		
		if( Validation.is_SafetyMan(input) && (!Validation.is_WorkArea(input)) ) {
			
			for(Employee employee : Data.employeeList) {
				
				if(employee.getId().equals(input)) {
					
					employee.setLine("미정");
					employee.setStation("미정");
					LogSave.logSave(LogSave.SAFYTYMANWORKAREACLEAR);
					// Employee객체 탐색종료
					break;
				}
				
			}
			
			System.out.println("\t\t\t안전요원 부서 해제 완료");
			
		} else if ( Validation.is_SafetyMan(input) && Validation.is_WorkArea(input) ) {
			System.out.println("\t\t\t입력한 안전요원은 아직 배치되지 않았습니다.");
		} else {
			System.out.println("\t\t\t입력한 아이디의 안전요원이 없습니다.");
		}
		
		ViewAll.pause();
		
	}//End of safetyManWorkAreaClear()
	/**
	 * 안전요원 근무지 배치하는 메서드
	 * @param list 안전요원 목록
	 */
	private static void arrangeSafetyMan(ArrayList<Employee> list) {
		/**
		 * 입력한 값을 저장하는 변수
		 */
		String input = "";
		
		Scanner scan = new Scanner(System.in);
		
		EmployeeSearch.employeePage(list);
		
		System.out.println("\t\t\t배치할 안전요원의 아이디를 입력하세요");
		System.out.printf("\t\t\t아이디: ");
		input = scan.nextLine();
		
		if( Validation.is_SafetyMan(input) && Validation.is_WorkArea(input) ) {
			
			EmployeeUpdateTab.updateWorkArea(input);
			
			LogSave.logSave(LogSave.ARRANGESAFYTYMAN);
			System.out.println("\t\t\t안전요원 배치 완료");
			
		} else if ( Validation.is_SafetyMan(input) && !(Validation.is_WorkArea(input)) ) {
			System.out.println("\t\t\t입력한 안전요원은 이미 배치되있습니다.");
		} else {
			System.out.println("\t\t\t입력한 아이디의 안전요원이 없습니다.");
		}
		
		ViewAll.pause();
		
	}//End of arrangeSafetyMan()
	
	
}//End of SafetyManTab
