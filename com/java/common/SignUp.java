package com.java.common;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			System.out.println("3-> 뒤로가기");
			System.out.print("선택 (번호): ");
			
			String sel = ""; // 선택한 번호
			sel = scan.nextLine();
			
			if (sel.equals("1")) { // 1. 개인 회원가입
				userSignUp();
			} else if (sel.equals("2")) { // 2. 관리자 회원가입
				employeeSignUp();
			} else if (sel.equals("3")) { // 3. 뒤로가기
				
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다.");
				System.out.println("다시 입력해주세요.");
				View.pause();
			
			
			}
		}//while문 종료
		
	}//End of SignUp

	private static void employeeSignUp() {
		Scanner scan = new Scanner(System.in);
		
		String id = "";
		String pw = "";
		String name = "";
		String registration = "";
		String phone = "";
		
		String code = "";

		// 아이디
		while (true) {
			
			// View클래스 출력
			View.title("개인회원 가입");
			
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
			
			System.out.println("직원 코드 입력");
			code = scan.nextLine();
			System.out.println();
			
			if(invalidatedId(id)) {
				System.out.println("아이디 형식이 틀렸습니다.");
			}
			if(invalidatedPw(pw)) {
				System.out.println("비밀번호 형식이 틀렸습니다.");
			}
			if (invalidatedName(name)) {
				System.out.println("이름 형식이 틀렸습니다.");
			}
			if (invalidatedRegistration(registration)) {
				System.out.println("주민등록번호 형식이 틀렸습니다.");
			}else if (registrationCheck(registration)) {
				System.out.println("유효한 주민등록번호가 아닙니다.");
			}
			if (invalidatedPhone(phone)) {
				System.out.println("전화번호 형식이 틀렸습니다.");
			} 
			if(is_Code(code)) {
				System.out.println("회원코드가 틀렸습니다.");
			}
			
			if(!(invalidatedId(id) || invalidatedPw(pw) || invalidatedName(name) || invalidatedRegistration(registration) 
					|| registrationCheck(registration) || invalidatedPhone(phone) || is_Code(code))) {
				break;
			}
			System.out.println("다시 입력하세요.");
			System.out.println();
			View.pause();
		}// while종료

		Employee employee = new Employee(name, id, pw, registration, phone); // 입력값 저장
		Data.employeeList.add(employee);
		
		System.out.println("회원가입이 완료되었습니다.");
		System.out.println("직원 " + name + "님 환영합니다.");
		View.pause();
	}

	// 개인 회원 가입
	public static void userSignUp() {
		Scanner scan = new Scanner(System.in);
		
		String id = "";
		String pw = "";
		String name = "";
		String registration = "";
		String phone = "";

		// 아이디
		while (true) {
			
			// View클래스 출력
			View.title("개인회원 가입");
			
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
			
			if(invalidatedId(id)) {
				System.out.println("아이디 형식이 틀렸습니다.");
			}
			if(invalidatedPw(pw)) {
				System.out.println("비밀번호 형식이 틀렸습니다.");
			}
			if (invalidatedName(name)) {
				System.out.println("이름 형식이 틀렸습니다.");
			}
			if (invalidatedRegistration(registration)) {
				System.out.println("주민등록번호 형식이 틀렸습니다.");
			}else if (registrationCheck(registration)) {
				System.out.println("유효한 주민등록번호가 아닙니다.");
			}
			if (invalidatedPhone(phone)) {
				System.out.println("전화번호 형식이 틀렸습니다.");
			} 
			
			if(!(invalidatedId(id) || invalidatedPw(pw) || invalidatedName(name) || invalidatedRegistration(registration) 
					|| registrationCheck(registration) || invalidatedPhone(phone))) {
				break;
			}
			System.out.println("다시 입력하세요.");
			System.out.println();
			View.pause();
		}// while종료

		User user = new User(name, id, pw, registration, phone); // 입력값 저장
		Data.userList.add(user);
		
		System.out.println("회원가입이 완료되었습니다.");
		System.out.println("회원 " +name + "님 환영합니다.");
		View.pause();

	} // end of user
	

	// id 유효성 검사
	private static boolean invalidatedId(String id) {
		// 아이디: 필수값 4~12자 이내, 영소문자+숫자, 숫자 시작 X

		String regex = "^[a-z]{1}[a-z0-9+]{3,11}$";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(id);

		return !m1.find();
	}

	// pw 유효성 검사
	private static boolean invalidatedPw(String pw) {
		// 비밀번호: 8~15자, 대소문자+숫자+특수문자(!~*)

		if (pw.equals("") || pw == null) {
			return true;
		}

		if (pw.length() < 8 || pw.length() > 15) {
			return true;
		}

		return false;
	}

	// 이름 유효성 검사
	private static boolean invalidatedName(String name) {
		// 이름: 2~5자, 한글만

		String regex = "[가-힣]{2,5}$";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(name);

		return !m1.find();
	}

	// 주민등록번호 유효성 검사
	private static boolean invalidatedRegistration(String registration) { // 주민등록번호 형식 검사
		// 주민등록번호: “-” 포함/미포함, 앞 6자리 뒤 7자리 숫자 입력

		String regex = "([0-9]{6}-?[0-9]{7})";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(registration);

		return !m1.find();
	}

	private static boolean registrationCheck(String registration) { // 주민등록번호 유효성 검사

		int sum = 0;
		registration = registration.replace("-", "");
		boolean a = false;

		for (int i = 0; i < 12; i++) {
			sum += Integer.parseInt(registration.substring(i, i + 1)) * (i % 8 + 2);
		}

		sum %= 11;
		sum = 11 - (sum % 10);

		if (sum == Integer.parseInt(registration.substring(registration.length() - 1, registration.length()))) {
			System.out.println("올바른 주민등록번호입니다.");
		} else {
			a = true;
		}

		return a;

	}

	// 전화번호 유효성 검사
	private static boolean invalidatedPhone(String phone) {

		String regex = "([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(phone);

		return !m1.find();
	}

	// 관리자 가입코드 유효성 검사
	private static boolean is_Code(String code) {

		boolean check = false;

		if (!code.equals(Data.EMPLOYEECODE)) {
			check = true;
		} 
		return check;
	}

}
