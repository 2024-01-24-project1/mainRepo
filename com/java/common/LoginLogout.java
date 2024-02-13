package com.java.common;

import java.util.Scanner;

import com.java.member.employee.Employee;
import com.java.member.employee.EmployeeMode;
import com.java.member.user.User;
import com.java.member.user.UserMode;
import com.java.view.ViewAll;

/**
 * 로그인한 계정의 정보를 저장하는 클래스
 */
public final class LoginLogout {
	
	/**
	 * 로그인한 계정의 아이디를 저장하는 멤버 변수
	 */
	public static String auth = "";		  // 인증 티켓
	/**
	 * 로그인한 계정의 이름을 저장하는 멤버 변수
	 */
	public static String authName = "";	  // 로그인중인 이름	
	/**
	 * 로그인한 계정의 직원 권한을 저장하는 멤버 변수
	 */
	public static String level = ""; 	  // 직원 권한 확인
	/**
	 * 로그인한 계정의 직원 직급을 저장하는 멤버 변수
	 */
	public static String position = "";	  // 직급
	/**
	 * 로그인한 계정의 정기권 유무를 저장하는 멤버 변수
	 */
	public static String pass = "";		  // 정기권 유무
	/**
	 * 로그인한 계정의 정기권 기간을 저장하는 멤버 변수
	 */
	public static String passExpiry = ""; // 정기권 기간
	/**
	 * 로그인한 계정의 전화번호를 저장하는 멤버 변수
	 */
	public static String phone = ""; 	  // 전화번호
	
	/**
	 * 계정 탈퇴시 메인화면으로 나가게 하기 위한 변수
	 */
	public static boolean getOut = false; // 계정 탈퇴시 메인화면으로 쫒아냄
	
	/**
	 * 로그인시 고객화면과 관리자 화면으로 나눠주는 메서드
	 */
	public void loginMode() {
			
			login();
			
			if(LoginLogout.level.equals("1")) {
				
				//유저모드
				UserMode.userTab();
				
				
			}else if (LoginLogout.level.equals("2") || LoginLogout.level.equals("3") || LoginLogout.level.equals("5")) {
				
				// 관리자 모드
				EmployeeMode.employeeTab();
			}
			
	}//End of loginMode()
	
	/**
	 * 아이디와 비밀번호를 입력받아 로그인하는 메서드
	 */
	private void login() {
		
		Scanner scan = new Scanner(System.in);
		
		// View클래스 출력
		
		while(true) {
			/**
			 * 고객 아이디인지 검사결과를 저장하는 변수
			 */
			boolean checkUser = false;
			/**
			 * 직원 아이디인지 검사결과를 저장하는 변수
			 */
			boolean checkEmployee = false;
			
			ViewAll.loginTop();
			
			System.out.print("\t\t\t아이디  : ");
			/**
			 * ID를 저장하는 변수
			 */
			String id = scan.nextLine();
			System.out.print("\t\t\t비밀번호: ");
			/**
			 * PW를 저장하는 변수
			 */
			String pw = scan.nextLine();
			
			// 고객인지 직원인지 검사
			checkUser = Data.userList.stream().anyMatch(user -> user.getId().equals(id) && user.getPw().equals(pw));
			checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(id) && employee.getPw().equals(pw));
			
			
			
			if(checkUser) {				// 고객계정으로 로그인 완료
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(id) && user.getPw().equals(pw)) {
						LoginLogout.auth = user.getId();
						LoginLogout.authName = user.getName();
						LoginLogout.level = "1";
						LoginLogout.pass = user.getPassCheck();
						LoginLogout.passExpiry = user.getPassExpiry();
						LoginLogout.phone = user.getPhone();
						
						// 고객리스트 탐색 종료
						break;
					}
				}
				
				System.out.println("\t\t\t로그인이 완료되었습니다.");
				System.out.println("\t\t\t" + LoginLogout.authName + "님 환영합니다.");
				ViewAll.pause();
				break;	// 아이디, 비밀번호 입력받기 종료
				
			}else if(checkEmployee){	// 직원계정으로 로그인 완료
				
					for(Employee employee : Data.employeeList) {
					
						if(employee.getId().equals(id) && employee.getPw().equals(pw)) {
							LoginLogout.auth = employee.getId();
							LoginLogout.authName = employee.getName();
							LoginLogout.level = employee.getLevel();
							LoginLogout.position = employee.getPosition();
							LoginLogout.phone = employee.getPhone();
							
							// 직원리스트 탐색 종료
							break;
						}
					
					}
				
					System.out.println("\t\t\t로그인이 완료되었습니다.");
					System.out.println("\t\t\t" + LoginLogout.authName + "님 환영합니다.");
					ViewAll.pause();
					break;	// 아이디, 비밀번호 입력받기 종료
					
			}else {
				/**
				 * 입력 값을 저장하는 변수
				 */
				String back = "";
				
				ViewAll.errorQuestionEmo();
				ViewAll.noIdError();
				System.out.println();
				System.out.println("\t\t\t뒤로가시려면 엔터");
				System.out.println("\t\t\t아무키나 입력시 다시 ID와 PW를 입력합니다.");
				back = scan.nextLine();
				
				if(back.equals("")) {
					
					// 로그인 while문 종료 메인으로 돌아감
					break;
				}
				
			}
			
		}//while루프 종료
		
	}//End of login()

	// 로그아웃
	/**
	 * 로그아웃하는 메서드
	 */
	public static void logout() {
		
		// 모든 LoginLogout초기화
		LoginLogout.auth = "";
		LoginLogout.authName = "";
		LoginLogout.level = "";
		LoginLogout.pass = "";
		LoginLogout.passExpiry = "";
		LoginLogout.phone = "";
		System.out.println("\t\t\t   로그아웃이 완료되었습니다.");
		ViewAll.pause();
		
		
	}//End of logout()

}//End of class

