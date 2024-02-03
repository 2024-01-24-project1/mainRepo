package com.java.common;

import java.util.Scanner;

import com.java.member.Member;
import com.java.member.employee.Employee;
import com.java.member.employee.EmployeeMode;
import com.java.member.user.User;
import com.java.member.user.UserMode;
import com.java.view.View;

public final class LoginLogout {
	
	public static String auth = "";		  // 인증 티켓
	public static String authName = "";	  // 로그인중인 이름	
	public static String level = ""; 	  // 직원 권한 확인
	public static String position = "";	  // 직급
	public static String pass = "";		  // 정기권 유무
	public static String passExpiry = ""; // 정기권 기간
	
	
	public void loginMode() {
			
			login();
			
			if(LoginLogout.level.equals("1")) {
				
				//유저모드
				UserMode userMode = new UserMode();
				userMode.userTab();
				
			}else if (LoginLogout.level.equals("2") || LoginLogout.level.equals("3") || LoginLogout.level.equals("5")) {
				
				// 관리자 모드
				EmployeeMode employeeMode = new EmployeeMode();
				employeeMode.employeeTab();
				
			}
			
	}//End of loginMode()
	
	
	private void login() {
		
		Scanner scan = new Scanner(System.in);
		
		// View클래스 출력
		
		while(true) {
			
			boolean checkUser = false;
			boolean checkEmployee = false;
			
			View.title("로그인");
			
			System.out.print("아이디: ");
			String id = scan.nextLine();
			System.out.print("비밀번호: ");
			String pw = scan.nextLine();
			
			// 고객인지 직원인지 검사
			checkUser = Data.userList.stream().anyMatch(user -> user.getId().equals(id) && user.getPw().equals(pw));
			checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(id) && employee.getPw().equals(pw));
			
			
			
			if(checkUser) {				// 고객계정으로 로그인 완료
				System.out.println("고객계정 존재");
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(id) && user.getPw().equals(pw)) {
						LoginLogout.auth = user.getId();
						LoginLogout.authName = user.getName();
						LoginLogout.level = "1";
						LoginLogout.pass = user.getPassCheck();
						LoginLogout.passExpiry = user.getPassExpiry();
						
						// 고객리스트 탐색 종료
						break;
					}
				}
				
				System.out.println("로그인이 완료되었습니다.");
				System.out.println(LoginLogout.authName + "님 환영합니다.");
				View.pause();
				break;	// 아이디, 비밀번호 입력받기 종료
				
			}else if(checkEmployee){	// 직원계정으로 로그인 완료
				System.out.println("직원계정 존재");
				
					for(Employee employee : Data.employeeList) {
					
						if(employee.getId().equals(id) && employee.getPw().equals(pw)) {
							LoginLogout.auth = employee.getId();
							LoginLogout.authName = employee.getName();
							LoginLogout.level = employee.getLevel();
							LoginLogout.position = employee.getPosition();
							
							// 직원리스트 탐색 종료
							break;
						}
					
					}
				
					System.out.println("로그인이 완료되었습니다.");
					System.out.println(LoginLogout.authName + "님 환영합니다.");
					View.pause();
					break;	// 아이디, 비밀번호 입력받기 종료
					
			}else {
				String back = "";
				
				System.out.println("존재하지 않는 계정");
				System.out.println();
				System.out.println("메인화면으로 돌아가시려면 back을 입력하세요.");
				System.out.println("엔터를 누르면 다시 ID와 PW를 입력합니다.");
				System.out.print("             ");
				back = scan.nextLine();
				
				if(back.equals("back")) {
					
					// 로그인 while문 종료 메인으로 돌아감
					break;
				}
				
			}
			
		}//while루프 종료
		
	}//End of login()

	// 로그아웃
	public static void logout() {
		
		// 모든 LoginLogout초기화
		LoginLogout.auth = "";
		LoginLogout.authName = "";
		LoginLogout.level = "";
		LoginLogout.pass = "";
		LoginLogout.passExpiry = "";
		System.out.println("로그아웃이 완료되었습니다.");
		View.pause();
		
		
	}//End of logout()

}//End of class

