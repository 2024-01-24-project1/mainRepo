package com.java.common;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.member.user.User;
import com.java.view.View;

import com.java.member.employee.Employee;

// 회원가입
public class SignUp {

	public static void signup(String signUp) { // 회원가입 초기 화면
		View.title("회원가입");

		System.out.println();
		System.out.println("           1. 개인 회원가입");
		System.out.println("           2. 관리자 회원가입");
		System.out.println("           3. 뒤로가기");
		System.out.println();

		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");

		Scanner scan = new Scanner(System.in);
		String num = ""; // 선택한 번호
		num = scan.nextLine();

		if (num.equals("1")) { // 1. 개인 회원가입
			SignUp.user();
		} else if (num.equals("2")) { // 2. 관리자 회원가입
			SignUp.employee();
		} else if (num.equals("3")) { // 3. 뒤로가기
			View.mainmenu();
		} else { // 이외의 숫자 입력 시
			System.out.println();
			System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
			System.out.println();
		}
	}

	// 개인 회원 가입
	public static void user() {
		Scanner scan = new Scanner(System.in);
		String id = "";
		String pw = "";
		String name = "";
		String registration = "";
		String phone = "";

		// 아이디
		while (true) {
			View.title("개인회원 가입");

			System.out.println();
			System.out.print("  아이디 (4~12자, 영소문자+숫자, 숫자 시작 X) : ");
			System.out.println();
			id = scan.nextLine();

			if (invalidatedId(id)) { // id 유효성 검사
				System.out.println("아이디 형식이 틀렸습니다.\r\n다시 입력하세요.");
				System.out.println();
			} else {
				break;
			}
		}

		// 비밀번호
		while (true) {
			System.out.print("  비밀번호 (8~15자, 대소문자+숫자+특수문자(!~*)) : ");
			System.out.println();
			pw = scan.nextLine();

			if (invalidatedPw(pw)) { // pw 유효성 검사
				System.out.println("비밀번호 형식이 틀렸습니다.\r\n다시 입력하세요.");
				System.out.println();
			} else {
				break;
			}
		}

		// 이름
		while (true) {
			System.out.print("  이름 (2~5자, 한글만) : ");
			System.out.println();
			name = scan.nextLine();

			if (invalidatedName(name)) {
				System.out.println("이름 형식이 틀렸습니다.\r\n다시 입력하세요.");
			} else {
				break;
			}
		}

		// 주민등록번호
		while (true) {
			System.out.print("  주민등록번호 (“-” 포함/미포함, 앞 6자리 뒤 7자리 숫자 입력): ");
			System.out.println();
			registration = scan.nextLine();

			if (invalidatedRegistration(registration)) {
				System.out.println("주민등록번호 형식이 틀렸습니다.\r\n다시 입력하세요.");
			} else if (registrationCheck(registration) == true) {
				System.out.println("유효한 주민등록번호가 아닙니다.\r\n다시 입력하세요.");
				System.out.println();
			} else {
				break;
			}
		}

		// 전화번호
		while (true) {
			System.out.print("  전화번호 (“-” 포함/미포함, 010-xxxx-xxxx 형식, 현재 회원과 동일한 전화번호는 등록 불가) : ");
			System.out.println();
			phone = scan.nextLine();

			if (invalidatedPhone(phone)) {
				System.out.println("전화번호 형식이 틀렸습니다.\r\n다시 입력하세요.");
			} else {
				break;
			}
		}

		User user = new User(name, id, pw, registration, phone); // 입력값 저장
		Data.userList.add(user);
		
		System.out.println("회원가입이 완료되었습니다.");
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

	// -----------------------------------

	// 관리자 회원 가입
	public static void employee() {
		Scanner scan = new Scanner(System.in);
		String id = "";
		String pw = "";
		String name = "";
		String registration = "";
		String phone = "";
		String code = "";

		// 아이디
		while (true) {
			View.title("관리자 가입");

			System.out.println();
			System.out.print("  아이디 (4~12자, 영소문자+숫자, 숫자 시작 X) : ");
			System.out.println();
			id = scan.nextLine();

			if (invalidatedId(id)) { // id 유효성 검사
				System.out.println("아이디 형식이 틀렸습니다.\r\n다시 입력하세요.");
				System.out.println();
			} else {
				break;
			}
		}

		// 비밀번호
		while (true) {
			System.out.print("  비밀번호 (8~15자, 대소문자+숫자+특수문자(!~*)) : ");
			System.out.println();
			pw = scan.nextLine();

			if (invalidatedPw(pw)) { // pw 유효성 검사
				System.out.println("비밀번호 형식이 틀렸습니다.\r\n다시 입력하세요.");
				System.out.println();
			} else {
				break;
			}
		}

		// 이름
		while (true) {
			System.out.print("  이름 (2~5자, 한글만) : ");
			System.out.println();
			name = scan.nextLine();

			if (invalidatedName(name)) {
				System.out.println("이름 형식이 틀렸습니다.\r\n다시 입력하세요.");
			} else {
				break;
			}
		}

		// 주민등록번호
		while (true) {
			System.out.print("  주민등록번호 (“-” 포함/미포함, 앞 6자리 뒤 7자리 숫자 입력): ");
			System.out.println();
			registration = scan.nextLine();

			if (invalidatedRegistration(registration)) {
				System.out.println("주민등록번호 형식이 틀렸습니다.\r\n다시 입력하세요.");
			} else if (registrationCheck(registration) == true) {
				System.out.println("유효한 주민등록번호가 아닙니다.\r\n다시 입력하세요.");
				System.out.println();
			} else {
				break;
			}
		}

		// 전화번호
		while (true) {
			System.out.print("  전화번호 (“-” 포함/미포함, 010-xxxx-xxxx 형식, 현재 회원과 동일한 전화번호는 등록 불가) : ");
			System.out.println();
			phone = scan.nextLine();

			if (invalidatedPhone(phone)) {
				System.out.println("전화번호 형식이 틀렸습니다.\r\n다시 입력하세요.");
			} else {
				break;
			}
		}

		// 가입 코드
		while (true) {
			System.out.print("  가입 코드: ");
			code = scan.nextLine();

			if (invalidatedCode(code)) {
				System.out.println("가입코드가 유효하지 않습니다.\r\n다시 입력하세요.");
			} else {
				System.out.println("관리자 회원가입이 완료되었습니다.");
				break;
			}
		}

		Employee employee = new Employee(name, id, pw, registration, phone); // 입력값 저장
		Data.employeeList.add(employee);
		
		View.pause();
		
	} // end of employee
	

	// 관리자 가입코드 유효성 검사
	private static boolean invalidatedCode(String code) {

		boolean a = false;

		if (!code.equals(Data.employeeCode)) {
			a = true;
		} 
		return a;
	}

}
