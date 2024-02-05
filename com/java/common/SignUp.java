package com.java.common;

import java.util.Scanner;

import com.java.member.user.User;
import com.java.view.View;

import com.java.member.employee.Employee;

// 회원가입
public class SignUp {

	public static void signUp() { // 회원가입 초기 화면
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			// View에서 출력
			View.title("회원가입");
			System.out.println("회원가입");
			System.out.println("1-> 고객가입");
			System.out.println("2-> 직원가입");
			System.out.println("엔터입력시 뒤로가기");
			System.out.print("선택 (번호): ");
			
			String sel = ""; // 선택한 번호
			sel = scan.nextLine();
			
			if (sel.equals("1")) { 		  // 1. 개인 회원가입
				
				commonSignUp(sel);
				
			} else if (sel.equals("2")) { // 2. 관리자 회원가입
				
				commonSignUp(sel);
				
			} else if (sel.equals("")) { // 3. 뒤로가기
				
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다.");
				System.out.println("다시 입력해주세요.");
				View.pause();
			
			
			}
		}//while문 종료
		
	}//End of SignUp

	private static void commonSignUp(String sel) {
		
		Scanner scan = new Scanner(System.in);
		
		String id = "";
		String pw = "";
		String name = "";
		String registration = "";
		String phone = "";
		
		String code = "";

		// 아이디
		while (true) {
			String back = "";
			
			
			// View클래스 출력
			if(sel.equals("1")) {
				View.title("개인회원 가입");
			}else if (sel.equals("2")) {
				View.title("직원회원 가입");
			}
			
			// ID, PW, 이름, 주민등록번호, 전화번호 입력받기
			System.out.print("  아이디 (4~12자, 영소문자+숫자, 숫자 시작 X) : ");
			id = scan.nextLine();
			System.out.println();
			
			System.out.print("  비밀번호 (8~15자, 대소문자+숫자+특수문자(!~*)) : ");
			pw = scan.nextLine();
			System.out.println();
			
			System.out.print("  이름 (2~5자, 한글만) : ");
			name = scan.nextLine();
			System.out.println();
			
			System.out.print("  주민등록번호 (“-” 포함/미포함, 앞 6자리 뒤 7자리 숫자 입력): ");
			registration = scan.nextLine();
			System.out.println();
			
			System.out.print("  전화번호 (“-” 포함/미포함, 010-xxxx-xxxx 형식, 현재 회원과 동일한 전화번호는 등록 불가) : ");
			phone = scan.nextLine();
			System.out.println();
			
			if(sel.equals("2")) {
				
			System.out.println("직원 코드 입력");
			System.out.print("CODE: ");
			code = scan.nextLine();
			System.out.println();
			
			}
			
			// 회원가입 유효성 검사
			if( Validation.is_Id(id) ) {
				System.out.println("아이디 형식이 틀렸습니다.");
			}else if ( Validation.is_Duplication_Id(id)) {
				System.out.println("중복된 아이디입니다.");
			}
			
			if( Validation.is_Pw(pw)) {
				System.out.println("비밀번호 형식이 틀렸습니다.");
			}
			if ( Validation.is_Name(name)) {
				System.out.println("이름 형식이 틀렸습니다.");
			}
			if ( Validation.is_RegistrationFormet(registration) ) {
				System.out.println("주민등록번호 형식이 틀렸습니다.");
			}else if ( Validation.is_RegistrationEffect(registration)) {
				System.out.println("유효한 주민등록번호가 아닙니다.");
			}else if ( Validation.is_Duplication_RRN(registration)) {
				System.out.println("중복된 주민등록");
			}
			if ( Validation.is_Phone(phone) ) {
				System.out.println("전화번호 형식이 틀렸습니다.");
			}else if ( Validation.is_Duplication_Phone(phone)) {
				System.out.println("중복된 전화번호입니다.");
			}
			if( Validation.is_Code(code) && sel.equals("2")) {
				System.out.println("회원코드가 틀렸습니다.");
			}
			
			// 회원이 모든 조건을 만족한 입력을 받은경우
			if(sel.equals("1") && !(Validation.is_Id(id) || Validation.is_Pw(pw) || Validation.is_Name(name) || Validation.is_RegistrationEffect(registration) 
					|| Validation.is_RegistrationFormet(registration) || Validation.is_Phone(phone))) {
				
				User user = new User(name, id, pw, registration, phone); // 입력값 저장
				Data.userList.add(user);
				
				System.out.println("회원가입이 완료되었습니다.");
				System.out.println("회원 " +name + "님 환영합니다.");
				View.pause();
				
				break;
			}
			
			// 직원이 모든 조건을 만족한 입력을 받은경우
			if(sel.equals("2") && !(Validation.is_Id(id) || Validation.is_Pw(pw) || Validation.is_Name(name) || Validation.is_RegistrationEffect(registration) 
					|| Validation.is_RegistrationFormet(registration) || Validation.is_Phone(phone) || Validation.is_Code(code))) {
				
				Employee employee = new Employee(name, id, pw, registration, phone); // 입력값 저장
				Data.employeeList.add(employee);
				
				System.out.println("회원가입이 완료되었습니다.");
				System.out.println("직원 " + name + "님 환영합니다.");
				View.pause();
				
				break;
			}
			
			
			System.out.println("다시 입력하시려면 아무키나 입력하세요.");
			System.out.println("뒤로가시려면 엔터를 입력하세요.");
			System.out.printf("입력: ");
			back = scan.nextLine();
			
			if(back.equals("")) {
				
				// 회원가입 종료
				break;
			}
			
			
		}// while종료

		View.pause();
		
	}//End of commonSignUp()

	
}//End of class
