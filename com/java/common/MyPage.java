package com.java.common;

import java.util.Scanner;

import com.java.member.employee.Employee;
import com.java.member.user.User;
import com.java.view.View;

public class MyPage {
	
	public static void myPageList() {
		
		boolean loop = true;
		while(loop) {
		
		System.out.printf("이름: %s\n전화번호: %s\n", LoginLogout.auth, LoginLogout.phone);
		
		if(LoginLogout.level.equals("1")) {
			System.out.printf("정기권: %s\n정기권 유효기간: %s\n", LoginLogout.pass, LoginLogout.passExpiry);
		
		} else {
			System.out.printf("직급: %s\n", LoginLogout.position);
			for(Employee employee : Data.employeeList) {
				
				if(employee.getId().equals(LoginLogout.auth)) {
					System.out.printf("부서: %s호선 %s역\n",employee.getLine(), employee.getStation());
				}
			}
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. 비밀번호 변경");
		System.out.println("2. 전화번호 변경");
		System.out.println("3. 회원탈퇴");
		System.out.println("4. 뒤로가기");
	
		String sel = sc.nextLine();
		
		if(sel.equals("1")) {
			myPageChangePW();
		} else if(sel.equals("2")) {
			myPageChangeNum();
		} else if(sel.equals("3")) {
				myPageremoveID();
		} else if(sel.equals("4")) {
			break;
		}
		
		} // while 루프 종료
		
		
	}


	private static void myPageremoveID() {
		
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
			System.out.println("해당 계정을 삭제하시겠습니까?");
			System.out.println("yes만 삭제: ");
			
			String input = scan.nextLine();
			
			if(input.equals("yes")) {
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(DELETE)) {
						Data.userList.remove(user);
						break;
					}
					
				}
				
			}else {
				System.out.println("계정삭제 취소");
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
			
			System.out.println("해당 계정을 삭제하시겠습니까?");
			System.out.println("yes만 삭제: ");
			String input = scan.nextLine();
			
			if(input.equals("yes")) {
				
				for(Employee employee : Data.employeeList) {
					
					if(employee.getId().equals(DELETE)) {
						Data.employeeList.remove(employee);
						System.out.println(DELETE + "계정 삭제완료");
						break;	// employee객체 탐색 중지
					}
					
				}
				
				
			}else {
				System.out.println("계정삭제 취소");
			}
			
			
		}
		
		System.out.println("계정 삭제 종료.");
	}


	private static void myPageChangeNum() {
		
		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
		
		View.title("전화번호 변경");
		
		String id = LoginLogout.auth;
		String name = LoginLogout.authName;
		String phone = LoginLogout.phone;
		

		
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) 
				 													&& user.getId().equals(id));

		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) 
																	&& employee.getId().equals(id));
		
		System.out.print("변경할 전화번호: ");
		
		Scanner scan = new Scanner(System.in);
		String chPhone = scan.nextLine(); //변경할 전화번호 입력
		//chNum 전화번호 유효성 검사
		
		
		if(checkUser) {
			
			Data.userList.stream().filter(user -> user.getName().equals(name) 
											   && user.getId().equals(id))
								  .map(user -> user.getPhone() == chPhone);
		
				
			View.pause();
				
		} else if (checkEmployee) {
			
			System.out.println("Employee");
			Data.employeeList.stream().filter(employee -> employee.getName().equals(name)
										   && employee.getPw().equals(id))
			 						  .map(employee -> employee.getPhone() == chPhone);
			View.pause();
				
		}
		
		System.out.println("변경이 완료되었습니다.");
		
	}// end of myPageChangeNum

	public static void myPageChangePW(){


		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
			
		View.title("PW 변경");


		// 아이디, 이름, 주민번호
		String id = LoginLogout.auth;
		String name = LoginLogout.authName;
		System.out.println();
				
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) 
														 && user.getId().equals(id));
				
		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) 
																	 && employee.getId().equals(id));
									  
		System.out.println("변경할 비밀번호: ");
		
		Scanner scan = new Scanner(System.in);
		String chPw = scan.nextLine(); //변경할 PW 입력
		//chpw PW 유효성 검사
		System.out.println(chPw);
		
		
		if(checkUser) {
			
			
			Data.userList.stream().filter(user -> user.getName().equals(name) 
											   && user.getId().equals(id))
								  .map(user -> user.getPw() == chPw);
		
				
			View.pause();
				
		} else if (checkEmployee) {
			
				
			Data.employeeList.stream().filter(employee -> employee.getName().equals(name)
										   && employee.getPw().equals(id))
			 						  .map(employee -> employee.getPw() == chPw);
			View.pause();
				
		}
		
		System.out.println("변경이 완료되었습니다.");
		
	} // end of myPageFindPW()

} //end of myPageList()
