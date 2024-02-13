package com.java.member.employee;

import java.util.Scanner;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.view.ViewAll;

public class EmployeeManagementTab {

	
	protected static void employeeManagementTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			String sel = ""; // 선택한 번호
			
			// View클래스 출력
			System.out.println("\t\t\t" + LoginLogout.position + " " + LoginLogout.auth + "님");
			ViewAll.employeeMain();
			ViewAll.chooseNum();
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 전체 직원 정보 보기
				
				EmployeeSearch.employeeAll(Data.employeeList);
				
			} else if(sel.equals("2")) {	// 2. 직원 정보 수정
				
					// 최고 권한만 직원정보수정 가능
					if(LoginLogout.level.equals("5")) {
						EmployeeUpdateTab.employeeUpdate();
					}else {
						// View클래스 접근권한없는 화면 출력
						System.out.println("\t\t\t접근 권한이 없습니다.");
						ViewAll.pause();
					}
					
			} else if (sel.equals("3")) {	// 3. 안전요원 관리
				SafetyManTab.safetyManTab();
			} else if (sel.equals("")) {	// 4. 뒤로가기
				
				// 직원관리 종료
				break;
					
			} else { // 이외의 숫자 입력 시
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
			}
			
			
		}// while루프 종료
		
	}//End of employeeManagementTab()
	
}//End of class
