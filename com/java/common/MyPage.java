package com.java.common;

import java.util.Scanner;

import com.java.member.employee.Employee;
import com.java.member.user.User;
import com.java.view.View;

public class MyPage {
	
	public static void myPageList(String mode) {
		
		while(true) {
			
			String sel = "";
			
			Scanner scan = new Scanner(System.in);
		
			System.out.printf("이름: %s\n아이디: %s\n전화번호: %s\n", LoginLogout.authName , LoginLogout.auth, LoginLogout.phone);
			
			if(mode.equals("1")) {
				
				// 유저만 가지는 정보 출력
				System.out.printf("정기권: %s\n", LoginLogout.pass);
				
				// 정기권이 있으면 유효기간 출력 없으면 출력하지않음
				if(LoginLogout.pass.equals("있음")) {
					System.out.printf("정기권 유효기간: %s\n", LoginLogout.passExpiry);
				}
				
			}else if (mode.equals("2")) {
				
				// 직원만 가지는 정보 출력
				System.out.printf("직급: %s\n", LoginLogout.position);
				System.out.printf("권한: LEVEL%s\n", LoginLogout.level);
				Data.employeeList.stream().filter(employee -> employee.getId().equals(LoginLogout.auth))
																	  .forEach(employee -> System.out.printf("",
																			  employee.getLine() + "호선"
																			 ,employee.getStation() + "역"));
			}
			
			System.out.println();
			System.out.println("1. 비밀번호 변경");
			System.out.println("2. 전화번호 변경");
			System.out.println("3. 회원탈퇴");
			System.out.println("엔터입력시 뒤로가기");
			System.out.print("입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) {
				myPageChangePW();
			} else if(sel.equals("2")) {
				myPageChangePhone();
			} else if(sel.equals("3")) {
				myPageRemoveID();
			} else if(sel.equals("")) {
				
				//마이 페이지 종료
				break;
			}else {
				//다시입력
				System.out.println();
				System.out.printf("해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
				View.pause();
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
			System.out.println("해당 계정을 삭제하시겠습니까?");
			System.out.println("yes만 삭제: ");
			
			String input = scan.nextLine();
			
			if(input.equals("yes")) {
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(DELETE)) {
						Data.userList.remove(user);
						
						System.out.println(DELETE + "계정 삭제완료");
						
						LoginLogout.getOut = true;	// 메인화면으로 쫒아내기
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
						
						LoginLogout.getOut = true;	// 메인화면으로 쫒아내기
						
						break;	// employee객체 탐색 중지
					}
					
				}
				
				
			}else if (employeeCheck && Validation.is_Sudo(DELETE)) {
				System.out.println("최고권한 계정은 회원탈퇴가 불가능합니다.");
			}
			else {
				System.out.println("계정삭제 취소");
			}
			
			View.pause();
		}
		
	}//End of myPageRemoveID()


	private static void myPageChangePhone() {
		
		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
		
		Scanner scan = new Scanner(System.in);
		
		View.title("전화번호 변경");
		
		String id = LoginLogout.auth;
		String name = LoginLogout.authName;
		String phone = LoginLogout.phone;
		

		
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) 
				 													&& user.getId().equals(id));

		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) 
																	&& employee.getId().equals(id));
		
		System.out.print("변경할 전화번호: ");
		
		String chPhone = scan.nextLine(); //변경할 전화번호 입력
		
		// 유효성 검사
		// 이미 있는 전화번호인지, 전화번호 형식에 맞는지
		if( Validation.is_Phone(chPhone) && !Validation.is_Duplication_Phone(chPhone)) {
	
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
		
		System.out.println("변경이 완료되었습니다.");
		
		}else {
			
			System.out.println("형식에 맞지않거나 이미 존재하는 전화번호입니다.");
		}
		View.pause();
		
	}// end of myPageChangeNum

	public static void myPageChangePW(){


		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
			
		View.title("PW 변경");


		// 고객 계정인지 직원계정인지 검사
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(LoginLogout.authName) 
														 && user.getId().equals(LoginLogout.auth));
				
		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(LoginLogout.authName) 
																	 && employee.getId().equals(LoginLogout.auth));
									  
		System.out.println("변경할 비밀번호: ");
		
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
			
			System.out.println("변경이 완료되었습니다.");
			
		}else {
			System.out.println("비밀번호의 형식이 올바르지 않습니다");
			System.out.println("비밀번호: 8~15자, 대소문자+숫자+특수문자(!~*)");
		}
		
		View.pause();
		
	} // end of myPageFindPW()

} //end of myPageList()
