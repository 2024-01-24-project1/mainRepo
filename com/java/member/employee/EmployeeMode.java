package com.java.member.employee;

import java.util.Scanner;

import com.java.common.Exit;
import com.java.common.LoginLogout;
import com.java.member.CommonFunction;
import com.java.view.View;

public final class EmployeeMode extends CommonFunction{

	public void employeeTab() {
		
		while(true) {
			
			String sel = ""; // 선택한 번호
			
			Scanner scan = new Scanner(System.in);
		
			//View클래스 출력
			System.out.println("=======================================");
			System.out.printf("             SEOUL METRO          ");
			System.out.println(LoginLogout.position + " " + LoginLogout.auth + "님");
			System.out.println("=======================================");
			System.out.println("           1. 역 관리");
			System.out.println("           2. 직원 관리");
			System.out.println("           3. 민원");
			System.out.println("           4. 행사 캘린더");
			System.out.println("           5. 통계 정보");
			System.out.println("           6. 요금표");
			System.out.println("           7. 열차 시간표");
			System.out.println("           8. 행동로그 보기");
			System.out.println("           9. 로그아웃");
			System.out.println("           0. 종료");
			System.out.println("--------------------------------------");
			System.out.print("선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("0")) {
				
				// 종료
				System.out.println("프로그램을 종료합니다.");
				Exit exit = new Exit();
				exit.writeAll();
				
				System.exit(0);
				
				
			} else if (sel.equals("1")) {
				
				// 역관리
				
			} else if (sel.equals("2")) {
				
				// 직원관리
				EmployeeManagementTab.employeeManagementTab();
				
			} else if (sel.equals("3")) {
				
				// 민원
				UserVoiceManagemnetTab.userVoiceManagementTab();
				
			} else if (sel.equals("4")) {
				
				// 행사캘린더
				
			} else if (sel.equals("5")) {
				
				// 통계정보
				
			} else if(sel.equals("6")) {
				
				// 요금표
				costsTableTab();
				
			} else if(sel.equals("7")) {
				
				// 열차 시간표
				//stationTimetableTab();
				
			} else if (sel.equals("8")) {
	
				// 행동로그 보기
				
			} else if (sel.equals("9")) {
				LoginLogout.logout();
				
				// 고객 모드 종료
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다.");
				System.out.println("다시 입력해주세요.");
				View.pause();
			}
		
		}//while루프 종료

		
	}//End of employeeTab
	
	
	
	
}//End of class
