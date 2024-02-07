package com.java.common;

import java.util.Scanner;

import com.java.member.employee.Employee;
import com.java.member.user.MyPageBookMark;
import com.java.member.user.User;

import com.java.view.ViewAll;

public class MyPage {
	
	public static void myPageList(String mode) {
		
		while(true) {
			
			String sel = "";
			
			Scanner scan = new Scanner(System.in);
			
			
			ViewAll.userMyPage();
			System.out.printf("\t\t이름: %s 아이디: %s 전화번호: %s\n", LoginLogout.authName , LoginLogout.auth, LoginLogout.phone);
			
			
			if(mode.equals("1")) {
				
				// 유저만 가지는 정보 출력
				System.out.printf("\t\t\t정기권: %s  ", LoginLogout.pass);
				
				// 정기권이 있으면 유효기간 출력 없으면 출력하지않음
				if(LoginLogout.pass.equals("있음")) {
					System.out.printf("\t\t\t정기권 유효기간: %s\n", LoginLogout.passExpiry);
				}
				
			}else if (mode.equals("2")) {
				
				// 직원만 가지는 정보 출력
				System.out.printf("\t\t\t직급: %s  ", LoginLogout.position);
				System.out.printf("\t\t권한: LEVEL%s\n", LoginLogout.level);
				Data.employeeList.stream().filter(employee -> employee.getId().equals(LoginLogout.auth))
																	  .forEach(employee -> System.out.printf("",
																			 employee.getStation() + "역"));
			}
			
			System.out.println();
			System.out.println("\t\t\t         1. 비밀번호 변경");
			System.out.println("\t\t\t         2. 전화번호 변경");
			System.out.println("\t\t\t         3. 회원탈퇴");
			System.out.println("\t\t\t         4. 북마크");
			

			if(mode.equals("1")) {
				System.out.println("\t\t\t 4. 즐겨찾기 목록");
			}
			System.out.println("\t\t\t\t엔터입력시 뒤로가기");
			ViewAll.chooseNum();
			sel = scan.nextLine();
			
			if(sel.equals("1")) { // 1. 비밀번호 변경
				myPageChangePW();
			} else if(sel.equals("2")) { // 2. 전화번호 변경 
				myPageChangePhone();
			} else if(sel.equals("3")) { // 3. 회원탈퇴
				myPageRemoveID();
			} else if(sel.equals("4") && mode.equals("1")) { // 북마크
				MyPageBookMark myPageBookMark = new MyPageBookMark();
				myPageBookMark.myPageBookMarkSelMenu();
			} else if(sel.equals("")) {
				
				//마이 페이지 종료
				break;
			}else {
				//다시입력
				ViewAll.errorFailEmo();
				ViewAll.pause();
			}
			
			if(LoginLogout.getOut) {
				
				// UserMode나 EmployeeMode로 쫒아내기
				break;
			}
			
		
		} // while 루프 종료
		
		
	}//End of myPage()


	private static void myPageRemoveID() {
		
		boolean userCheck = false;
		boolean employeeCheck = false;
		
		Scanner scan = new Scanner(System.in);

		final String DELETE = LoginLogout.auth;
		
		userCheck = Data.userList.stream().anyMatch(user -> user.getId().equals(DELETE));
		employeeCheck = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(DELETE));
		
		
		if(userCheck) {
			
			Data.userList.stream().filter(user -> user.getId().equals(DELETE))
							      .forEach(user -> 
							System.out.printf("이름: %s\r\n아이디: %s\r\n생년월일: %s\r\n전화번호: %s\r\n정기권유무: %s\r\n정기권기간: %s\r\n"
												, user.getName()
												, user.getId()
												, user.getRegistration().substring(0, 6)
												, user.getPhone()
												, user.getPassCheck()
												, user.getPassExpiry()));
			System.out.println("\t\t\t해당 계정을 삭제하시겠습니까?");
			System.out.print("\t\t\tyes만 삭제: ");
			
			String input = scan.nextLine();
			
			if(input.equals("yes")) {
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(DELETE)) {
						Data.userList.remove(user);
						
						System.out.println("\t\t\t" + DELETE + "계정 삭제완료");
						
						LoginLogout.getOut = true;	// 메인화면으로 쫒아내기
						break;
					}
					
				}
				
			}else {
				System.out.println("\t\t\t계정삭제 취소");
			}
			
			
		}else if (employeeCheck && (!Validation.is_Sudo(DELETE)) ) {
			
			Data.employeeList.stream().filter(employee -> employee.getId().equals(DELETE))
									  .forEach(employee -> 
							System.out.printf("이름: %s\r\n아이디: %s\r\n생년월일: %s\r\n전화번호: %s\r\n직급: %s\r\n근무지: %s호선 %s역\r\n권한: %s\r\n"
												, employee.getName()
												, employee.getId()
												, employee.getRegistration().substring(0, 6)
												, employee.getPhone()
												, employee.getPosition()
												, employee.getLine()
												, employee.getStation()
												, employee.getLevel()));
			
			System.out.println("\t\t\t해당 계정을 삭제하시겠습니까?");
			System.out.println("\t\t\tyes만 삭제: ");
			String input = scan.nextLine();
			
			if(input.equals("yes")) {
				
				for(Employee employee : Data.employeeList) {
					
					if(employee.getId().equals(DELETE)) {
						Data.employeeList.remove(employee);
						System.out.println("\t\t\t" + DELETE + "계정 삭제완료");
						
						LoginLogout.getOut = true;	// 메인화면으로 쫒아내기
						
						break;	// employee객체 탐색 중지
					}
					
				}
				
				
			}else if (employeeCheck && Validation.is_Sudo(DELETE)) {
				System.out.println("\t\t\t최고권한 계정은 회원탈퇴가 불가능합니다.");
			}
			else {
				System.out.println("\t\t\t계정삭제 취소");
			}
			
			ViewAll.pause();
		}
		
	}//End of myPageRemoveID()


	private static void myPageChangePhone() {
		
		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
		
		Scanner scan = new Scanner(System.in);
		
		ViewAll.phoneNumChange();
		
		String id = LoginLogout.auth;
		String name = LoginLogout.authName;
		String phone = LoginLogout.phone;
		

		
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) 
				 													&& user.getId().equals(id));

		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) 
																	&& employee.getId().equals(id));
		
		System.out.print("\t\t\t변경할 전화번호: ");
		
		String chPhone = scan.nextLine(); //변경할 전화번호 입력
		
		// 전화번호 형식이면 010-XXXX-XXXX형식으로 변환
		if(Validation.is_PhoneFormat(chPhone)) {
			SignUp.formatPhoneNumber(chPhone);
		}
		
		// 유효성 검사
		// 이미 있는 전화번호인지, 전화번호 형식에 맞는지
		if(  Validation.is_PhoneFormat(chPhone) && Validation.is_Phone(chPhone) && !Validation.is_Duplication_Phone(chPhone)) {
	
			//고객의 전화번호 변경
			if(checkUser) {
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(LoginLogout.auth)) {
						user.setPhone(chPhone);
						
						// user객체 탐색 종료
						break;
					}
					
				}
				
				
			// 직원의 전화번호 변경
			} else if (checkEmployee) {
				
				for(Employee employee : Data.employeeList) {
					
					if(employee.getId().equals(LoginLogout.auth)) {
						employee.setPhone(chPhone);
						
						// user객체 탐색 종료
						break;
					}
					
				}
				
			}
		
		System.out.println("\t\t\t변경이 완료되었습니다.");
		
		}else {
			ViewAll.phoneNumberFormatError();
		}
		ViewAll.pause();
		
	}// end of myPageChangeNum

	public static void myPageChangePW(){


		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
			
		ViewAll.pwChange();


		// 고객 계정인지 직원계정인지 검사
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(LoginLogout.authName) 
														 && user.getId().equals(LoginLogout.auth));
				
		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(LoginLogout.authName) 
																	 && employee.getId().equals(LoginLogout.auth));
									  
		System.out.print("\t\t\t변경할 비밀번호: ");
		
		Scanner scan = new Scanner(System.in);
		String chPw = scan.nextLine(); //변경할 PW 입력
		
		// 비밀번호가 다음의 조건을 만족하면
		// 비밀번호: 8~15자, 대소문자+숫자+특수문자(!~*)
		if( Validation.is_Pw(chPw)) {
			
			// 고객 비밀번호 변경
			if(checkUser) {
				
					for(User user : Data.userList) {
					
					if(user.getId().equals(LoginLogout.auth)) {
						user.setPw(chPw);
						
						// user객체 탐색 종료
						break;
					}
					
				}
				
				
			// 직원 비밀번호 변경
			} else if (checkEmployee) {
				
				
				for(Employee employee : Data.employeeList) {
					
					if(employee.getId().equals(LoginLogout.auth)) {
						employee.setPw(chPw);
						
						// employee객체 탐색 종료
						break;
					}
					
				}
				
			}
			ViewAll.successVEmo();
			
		}else {
			ViewAll.pwFormatError();
		}
		
		ViewAll.pause();
		
	} // end of myPageFindPW()

} //end of myPageList()
