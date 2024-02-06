package com.java.common;

import java.util.Scanner;

import com.java.view.View;

// 아이디, 비밀번호 찾기
public class FindAccount {
	
	public static void findAccount() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			// View에서 출력
			System.out.println("1-> ID찾기");
			System.out.println("2-> PW찾기");
			System.out.println("엔터입력시 뒤로가기");
			System.out.println("입력: ");
			/*
			  	1. ID 찾기
			  	2. PW찾기
			  	3. 뒤로가기
			  	------------
			  	선택(번호): 
			 
			 */
			
			// 사용자 입력 받기
			String sel = "";
			sel = scan.nextLine();
			
			if (sel.equals("1")) { 		  // 1. ID 찾기
				FindAccount.findId();
			} else if (sel.equals("2")) { // 2. PW 찾기
				FindAccount.findPw();
			} else if (sel.equals("")) {  // 3. 뒤로가기
				
				break;
				
			} else { // 이외의 숫자 입력 시
				
				//다시입력
				System.out.println();
				System.out.printf("해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
				View.pause();
			}
			
			
		}// while문 종료
		
		
	}//End of findAccount()
	
	//ID 찾기
	public static void findId() {
		
		// View에서 출력
		//View.title("ID 찾기");
		
		// 입력받은 이름과 주민번호가 존재하는지
		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
		
		Scanner scan = new Scanner(System.in);
		
		// 이름, 주민번호 입력받기
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("주민등록번호: ");
		String registration = scan.nextLine();
		System.out.println();
		
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) && user.getRegistration().equals(registration));
		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) && employee.getRegistration().equals(registration));
							  
		
		if(checkUser) {
			System.out.println("입력받은 이름과 주민번호의 고객 계정 존재");
			
			Data.userList.stream().filter(user -> user.getName().equals(name) && user.getRegistration().equals(registration))
				.forEach(s -> System.out.println(name + "님의 ID는 " + s.getId() + " 입니다."));
			
			
		}else if (checkEmployee) {
			System.out.println("입력받은 이름과 주민번호의 직원계정 존재");
			
			Data.employeeList.stream().filter(employee -> employee.getName().equals(name) && employee.getRegistration().equals(registration))
									  .forEach(employee -> System.out.println(name + "님의 ID는 " + employee.getId() + " 입니다."));
			
		}else {
			System.out.println("입력받은 이름과 주민번호의 아이디가 없음");
		}
		
		View.pause();

	}//End of findId()
	
	
	// PW 찾기
	public static void findPw() {
		
		// 입력받은 이름, 아이디, 주민번호가 존재하는지
		boolean checkUser = false;		// 고객
		boolean checkEmployee = false;	// 직원
		
		View.title("PW 찾기");

		Scanner scan = new Scanner(System.in);

		// 아이디, 이름, 주민번호 입력받기
		System.out.println("아이디: ");
		String id = scan.nextLine();
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("주민등록번호: ");
		String registration = scan.nextLine();
		System.out.println();
		
		checkUser = Data.userList.stream().anyMatch(user -> user.getName().equals(name) 
														 && user.getRegistration().equals(registration)
														 && user.getId().equals(id));
		
		checkEmployee = Data.employeeList.stream().anyMatch(employee -> employee.getName().equals(name) 
																	 && employee.getRegistration().equals(registration)
																	 && employee.getId().equals(id));
							  
		
		if(checkUser) {
			System.out.println("입력받은 이름과 아이디와 주민번호의 고객 계정 존재");
			
			Data.userList.stream().filter(user -> user.getName().equals(name) 
											   && user.getRegistration().equals(registration)
											   && user.getId().equals(id))
								  .forEach(user -> System.out.println(name + "님의 PW는 " + user.getPw() + " 입니다."));
			
			
		}else if (checkEmployee) {
			System.out.println("입력받은 이름과 아이디와 주민번호의 직원계정 존재");
			
			Data.employeeList.stream().filter(employee -> employee.getName().equals(name)
										   && employee.getRegistration().equals(registration)
										   && employee.getPw().equals(id))
									  .forEach(employee -> System.out.println(name + "님의 PW는 " + employee.getPw() + " 입니다."));
			
		}else {
			System.out.println("입력받은 이름과 아이디, 주민번호의 아이디가 없음");
		}
		
		View.pause();
		
	}//End of findPw()

}//End of class
