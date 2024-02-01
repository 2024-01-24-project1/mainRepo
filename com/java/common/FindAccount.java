package com.java.common;

import java.util.Scanner;

import com.java.view.View;

// 아이디, 비밀번호 찾기
public class FindAccount {
	
	public void findAccount() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			// View에서 출력
			System.out.println("1-> ID찾기");
			System.out.println("1-> PW찾기");
			System.out.println("1-> ID찾기");
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
			
			if (sel.equals("1")) { // 1. ID 찾기
				FindAccount.findId();
			} else if (sel.equals("2")) { // 2. PW 찾기
				FindAccount.findPw();
			} else if (sel.equals("3")) { // 3. 뒤로가기
				
				break;
				
			} else { // 이외의 숫자 입력 시
				
				// View에서 출력
				/*
				 	해당 섹션이 없습니다
				 	다시 입력해주세요.
				 */
				System.out.println("잘못입력");
			}
			
			
		}// while문 종료
		
		return;
		
	}//End of findAccount()
	
	//ID 찾기
	public static void findId() {
		
		// View에서 출력
		//View.title("ID 찾기");
		
		boolean checkUser = false;	// 입력받은 이름과 주민번호가 존재하는지
		boolean checkEmployee = false;	// 입력받은 이름과 주민번호가 존재하는지
		
		Scanner scan = new Scanner(System.in);
		
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
			
			View.pause();
			
		}else if (checkEmployee) {
			System.out.println("입력받은 이름과 주민번호의 직원계정 존재");
			
			Data.employeeList.stream().filter(employee -> employee.getName().equals(name) && employee.getRegistration().equals(registration))
									  .forEach(employee -> System.out.println(name + "님의 ID는 " + employee.getId() + " 입니다."));
			View.pause();
			
		}else {
			System.out.println("입력받은 이름과 주민번호의 아이디가 없음");
		}

	}//End of findId()
	
	
	// PW 찾기
	public static void findPw() {

		View.title("PW 찾기");

		Scanner scan = new Scanner(System.in);

		System.out.println("아이디: ");
		String id = scan.nextLine();
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("주민등록번호: ");
		String registration = scan.nextLine();
		System.out.println();

		Data.userList.stream().filter(s -> s.getName().equals(name))
				.filter(s -> s.getRegistration().equals(registration))
				.filter(s -> s.getId().equals(id))
				.forEach(s -> System.out.println(name + "님의 ID " + s.getId() + "의 PW는 " + s.getPw() + " 입니다."));

		View.pause();
	}

}
