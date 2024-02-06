package com.java.member.user;

import java.util.Scanner;

import com.java.common.Exit;
import com.java.common.LoginLogout;
import com.java.common.MyPage;
import com.java.member.CommonFunction;
import com.java.station.management.FindWay;
import com.java.view.View;

public final class UserMode extends CommonFunction{
	
		
	public static void userTab() {
		
		while(true) { 
			
			String sel = ""; // 선택한 번호
			
			Scanner scan = new Scanner(System.in);
			
			// View클래스 출력
			System.out.println("=======================================");
			System.out.printf("             SEOUL METRO       ");
			System.out.println(LoginLogout.auth + "님");
			System.out.println("=======================================");
			System.out.println();
			System.out.println("           1. 열차 시간표");
			System.out.println("           2. 민원 접수");
			System.out.println("           3. 정기권 현황");
			System.out.println("           4. 길 찾기");
			System.out.println("           5. 요금표");
			System.out.println("           6. 마이 페이지");
			System.out.println("           7. 로그아웃");
			System.out.println("           0. 종료");
			System.out.println("--------------------------------------");
			
			System.out.print("선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("0")) {			// 0. 프로그램 종료
				
				// 종료
				System.out.println("프로그램을 종료합니다.");
				Exit exit = new Exit();
				exit.writeAll();
				
				System.exit(0);
				
			} else if (sel.equals("1")) { 	// 1. 열차 시간표
				
				// 열차 시간표
				stationTimetableTab();
				
			} else if (sel.equals("2")) { 	// 2. 민원 접수
				UserVoiceTab.userVoice();
			} else if (sel.equals("3")) {	// 3. 정기권 현황
				PassTab.passTab();
			} else if (sel.equals("4")) {	// 4. 길찾기
				
				// 길찾기
				System.out.println("1.경로입력, 2. 즐겨찾기, 뒤로가기 엔터");
				sel = scan.nextLine();
				
				if(sel.equals("1")) {
					FindWay findWay = new FindWay();
					findWay.findWaySelMenu();
				}else if (sel.equals("2")) {
					BookMarkRoute bookMarkRoute = new BookMarkRoute();
					bookMarkRoute.bookMarkRouteSelMenu();
				}
			
				
			} else if (sel.equals("5")) {	// 5. 요금표
				costsTableTab();
			} else if (sel.equals("6")) {	// 6. 마이페이지
				// 마이페이지
				MyPage.myPageList("1");
				
			} else if (sel.equals("7")) {	// 7. 로그아웃
				LoginLogout.logout();
				
				// 고객 모드 종료
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다.");
				System.out.println("다시 입력해주세요.");
				View.pause();
			}
			
			if(LoginLogout.getOut) {
				// 회원 탈퇴시 메인으로 보내기
				LoginLogout.getOut = false;
				System.out.println("지금까지 이용해주셔서 감사합니다.");
				LoginLogout.logout();
				break;
			}
			
		
			}//while루프 종료
	
	}//End of userTab()
	
}//End of class
