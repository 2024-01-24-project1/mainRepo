package com.java.member.employee;

import java.util.Scanner;

import com.java.common.Exit;
import com.java.common.LoginLogout;
import com.java.common.MyPage;
import com.java.member.CommonFunction;
import com.java.member.employee.log.LogTab;
import com.java.member.employee.stats.StatsTab;
import com.java.schedule.ScheduleTab;
import com.java.station.management.AddTrain;
import com.java.station.management.ChangeNoChairTrain;

import com.java.view.ViewAll;

public final class EmployeeMode extends CommonFunction{

	public static void employeeTab() {
		
		while(true) {
			
			String sel = ""; // 선택한 번호
			
			Scanner scan = new Scanner(System.in);
		
			//View클래스 출력
			ViewAll.adminMainView();
			ViewAll.chooseNum();
			sel = scan.nextLine();
			
			if(sel.equals("0")) {
				
				// 종료
				System.out.println("\t\t\t프로그램을 종료합니다.");
				Exit exit = new Exit();
				exit.writeAll();
				
				System.exit(0);
				
				
			} else if (sel.equals("1")) {	// 1. 역관리
				StationManagementTab.stationManagementTab();
			} else if (sel.equals("2")) {	// 2 직원관리
				if(LoginLogout.level.equals("5")) EmployeeManagementTab.employeeManagementTab();
				else ViewAll.rankError();
			} else if (sel.equals("3")) {	// 3. 민원
				
				if(LoginLogout.level.equals("5") || LoginLogout.level.equals("3")) {
					
					UserVoiceManagemnetTab.userVoiceManagementTab();
					
				}else {
					ViewAll.rankError();
				}
				
				
			} else if (sel.equals("4")) {	// 4. 행사캘린더
				
				if(LoginLogout.level.equals("5") || LoginLogout.level.equals("3")) {
				
					ScheduleTab.scheduleTab();
				
				}else {
					ViewAll.rankError();
				}
				
			} else if (sel.equals("5")) {	// 5. 통계정보
				StatsTab.statsTab();
			} else if(sel.equals("6")) {	// 6. 요금표
				costsTableTab();
			} else if(sel.equals("7")) {	// 7. 열차 시간표
				stationTimetableTab();
			} else if (sel.equals("8")) {
				
				if(LoginLogout.level.equals("5") || LoginLogout.level.equals("3")) {
					
					LogTab.printLog();
				
				}else {
					ViewAll.rankError();
				}
				
			} else if (sel.equals("9")) {
				MyPage.myPageList("2");
				
			} else if (sel.equals("10")) {
				LoginLogout.logout();
				
				// 고객 모드 종료
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("\t\t\t해당 섹션이 없습니다.");
				System.out.println("\t\t\t다시 입력해주세요.");
				ViewAll.pause();
			}
		
			if(LoginLogout.getOut) {
				// 회원 탈퇴시 메인으로 보내기
				LoginLogout.getOut = false;
				System.out.println("\t\t\t지금까지 이용해주셔서 감사합니다.");
				LoginLogout.logout();
				break;
			}
			
		}//while루프 종료

		
	}//End of employeeTab
	
}//End of class
