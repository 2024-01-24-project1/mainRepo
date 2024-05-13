package com.java.common;

import java.util.Scanner;

import com.java.common.log.LogSave;
import com.java.member.employee.Employee;
import com.java.member.user.MyPageBookMark;
import com.java.member.user.User;
import com.java.view.ViewAll;

/**
 * 마이페이지를 나타내는 클래스
 */
public class MyPage {
	/**
	 * MyPage클래스의 기본 생성자
	 */
	public MyPage() {
	}
	
	/**
	 * 마이페이지 목록을 매개변수에 따라 고객페이지 또는 관리자페이지로 나눠서 출력하는 메서드
	 * @param mode 고객=1 관리자=2
	 */
	public static void myPageList(String mode) {
		
		while(true) {
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = "";
			
			Scanner scan = new Scanner(System.in);
			ViewAll.userMyPage();
			System.out.printf("\t     이름: %s  아이디: %s  전화번호: %s\n", LoginLogout.authName , LoginLogout.auth, LoginLogout.phone);
			System.out.print("\t\t");
			if(mode.equals("1")) {
				
				// 유저만 가지는 정보 출력
				System.out.printf("\t\t정기권: %s  ", LoginLogout.pass);
				
				// 정기권이 있으면 유효기간 출력 없으면 출력하지않음
				if(LoginLogout.pass.equals("있음")) {
					System.out.printf("\t\t\t정기권 유효기간: %s\n", LoginLogout.passExpiry);
				}
				
			}else if (mode.equals("2")) {
				
				// 직원만 가지는 정보 출력
				System.out.printf("\t직급: %s  ", LoginLogout.position);
				System.out.printf("\t권한: LEVEL%s\n", LoginLogout.level);
				Data.employeeList.stream().filter(employee -> employee.getId().equals(LoginLogout.auth))
										  .forEach(employee -> System.out.printf("", employee.getStation() + "역"));
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t         0. 회원탈퇴");
			System.out.println("\t\t\t         1. 비밀번호 변경");
			System.out.println("\t\t\t         2. 전화번호 변경");
			
			
			if(mode.equals("1")) {
				System.out.println("\t\t\t         3. 북마크");
			}
			
			
			System.out.println("\t\t\t\t엔터입력시 뒤로가기");
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			ViewAll.chooseNum();
			sel = scan.nextLine();
			
			if(sel.equals("0")) {
				myPageRemoveID();
			} else if(sel.equals("1")) {
				myPageChangePW();
			} else if(sel.equals("2")) {
				myPageChangePhone();
			} else if(sel.equals("3") && mode.equals("1")) {
				MyPageBookMark myPageBookMark = new MyPageBookMark();
				myPageBookMark.myPageBookMarkSelMenu();
			} else if(sel.equals("")) {
				
				//마이 페이지 종료
				if(mode.equals("2")) LogSave.logSave(LogSave.MYPAGE);
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

	
	/**
	 * 마이페이지에서 회원탈퇴를 하는 메서드
	 */
	private static void myPageRemoveID() {
		
		/**
		 * 고객 아이디인지 확인 결과를 저장하는 변수
		 */
		boolean userCheck = false;
		/**
		 * 직원 아이디인지 확인 결과를 저장하는 변수
		 */
		boolean employeeCheck = false;
		
		Scanner scan = new Scanner(System.in);

		/**
		 * 삭제할 아이디를 저장하는 변수
		 */
		final String DELETE = LoginLogout.auth;
		
		userCheck = Data.userList.stream().anyMatch(user -> user.getId().equals(DELETE));
		employeeCheck = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(DELETE));
		
		
		if(userCheck) {
			
			Data.userList.stream().filter(user -> user.getId().equals(DELETE))
							      .forEach(user -> 
							      System.out.printf("\t\t\t이름: %s\r\n\t\t\t아이디: %s\r\n\t\t\t생년월일: %s\r\n\t\t\t전화번호: %s\r\n\t\t\t정기권유무: %s\r\n\t\t\t정기권기간: %s\r\n"
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
				
				
			} else {
				System.out.println("\t\t\t계정삭제 취소");
			}
			
			ViewAll.pause();
		} else if (employeeCheck && Validation.is_Sudo(DELETE)) {
			System.out.println("\t\t\t최고권한 계정은 회원탈퇴가 불가능합니다.");
			ViewAll.pause();
		}
		
	}//End of myPageRemoveID()


	/**
	 * 마이페이지에서 휴대폰 번호를 변경하는 메서드
	 */
	private static void myPageChangePhone() {
		/**
		 * 고객의 아이디인지 확인 결과를 저장하는 변수
		 */
		boolean checkUser = false;		// 고객
		/**
		 * 직원의 아이디인지 확인 결과를 저장하는 변수
		 */
		boolean checkEmployee = false;	// 직원
		
		Scanner scan = new Scanner(System.in);
		
		ViewAll.phoneNumChange();
		
		/**
		 * 로그인한 아이디를 저장하는 변수
		 */
		String id = LoginLogout.auth;
		/**
		 * 로그인한 계정의 이름을 저장하는 변수
		 */
		String name = LoginLogout.authName;
		
		
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) 
				 													&& user.getId().equals(id));

		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) 
																	&& employee.getId().equals(id));
		
		System.out.print("\t\t\t변경할 전화번호: ");
		
		/**
		 * 변경할 전화번호를 저장하는 변수
		 */
		String chPhone = scan.nextLine(); //변경할 전화번호 입력
		
		// 전화번호 형식이면 010-XXXX-XXXX형식으로 변환
		if(Validation.is_PhoneFormat(chPhone)) {
			chPhone = SignUp.formatPhoneNumber(chPhone);
		}
		
		// 유효성 검사
		// 이미 있는 전화번호인지, 전화번호 형식에 맞는지
		if(  Validation.is_PhoneFormat(chPhone) && Validation.is_Phone(chPhone) && !Validation.is_Duplication_Phone(chPhone)) {
	
			//고객의 전화번호 변경
			if(checkUser) {
				
				for(User user : Data.userList) {
					
					if(user.getId().equals(LoginLogout.auth)) {
						user.setPhone(chPhone);
						LoginLogout.phone= chPhone;
						// user객체 탐색 종료
						break;
					}
					
				}
				
				
			// 직원의 전화번호 변경
			} else if (checkEmployee) {
				
				for(Employee employee : Data.employeeList) {
					
					if(employee.getId().equals(LoginLogout.auth)) {
						employee.setPhone(chPhone);
						LoginLogout.phone= chPhone;
						
						LogSave.logSave(LogSave.MODIFYMYPAGE);
						// user객체 탐색 종료
						break;
					}
					
				}
				
			}
		
		System.out.println("\t\t\t변경이 완료되었습니다.");
		
		}else {
			
			System.out.println("형식에 맞지않거나 이미 존재하는 전화번호입니다.");
		}
		ViewAll.pause();
		
	}// end of myPageChangeNum

	/**
	 * 마이페이지에서 비밀번호를 변경하는 메서드
	 */
	public static void myPageChangePW(){


		/**
		 * 고객의 아이디인지 확인 결과를 저장하는 변수
		 */
		boolean checkUser = false;		// 고객
		/**
		 * 직원의 아이디인지 확인 결과를 저장하는 변수
		 */
		boolean checkEmployee = false;	// 직원
			
		ViewAll.pwChange();


		// 고객 계정인지 직원계정인지 검사
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(LoginLogout.authName) 
														 && user.getId().equals(LoginLogout.auth));
				
		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(LoginLogout.authName) 
																	 && employee.getId().equals(LoginLogout.auth));
		
		System.out.println("비밀번호는 8~15자, 영어 대소문자,숫자 그리고 특수문자(!,~,*,@)만 입력 가능합니다.");
		System.out.println();
		System.out.print("\t\t\t변경할 비밀번호: ");
		
		Scanner scan = new Scanner(System.in);
		/**
		 * 변경할 PW를 저장할 변수
		 */
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
						LogSave.logSave(LogSave.MODIFYMYPAGE);
						// employee객체 탐색 종료
						break;
					}
					
				}
				
			}
			
			
			System.out.println("\t\t\t변경이 완료되었습니다.");
			
		}else {
			System.out.println("\t\t\t비밀번호의 형식이 올바르지 않습니다");
			System.out.println("\t\t비밀번호: 8~15자, 대소문자+숫자+특수문자(!,~,*,@)");
		}
		
		ViewAll.pause();
		
	} // end of myPageFindPW()

} //end of myPageList()
