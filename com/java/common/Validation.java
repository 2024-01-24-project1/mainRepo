package com.java.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 유효성 검사 클래스 
// true false만 반환
public final class Validation {
	
		// id 유효성 검사
		public static boolean is_Id(String id) {
			// 아이디: 필수값 4~12자 이내, 영소문자+숫자, 숫자 시작 X

			String regex = "^[a-z]{1}[a-z0-9+]{3,11}$";
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(id);

			return !m1.find();
		}
		
		// pw 유효성 검사
		public static boolean is_Pw(String pw) {
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
		public static boolean is_Name(String name) {
			// 이름: 2~5자, 한글만

			String regex = "[가-힣]{2,5}$";
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(name);

			return !m1.find();
		}
	
		// 주민등록번호 유효성 검사
		public static boolean is_RegistrationFormet(String registration) { // 주민등록번호 형식 검사
			// 주민등록번호: “-” 포함/미포함, 앞 6자리 뒤 7자리 숫자 입력

			String regex = "([0-9]{6}-?[0-9]{7})";
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(registration);

			return !m1.find();
		}

		public static boolean is_RegistrationEffect(String registration) { // 주민등록번호 유효성 검사

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
		public static boolean is_Phone(String phone) {

			String regex = "([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})";
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(phone);

			return !m1.find();
		}
	
		// 관리자 가입코드 유효성 검사
		public static boolean is_Code(String code) {

			boolean check = false;

			if (!code.equals(Data.EMPLOYEECODE)) {
				check = true;
			} 
			return check;
		}
		
		// 존재하는 역이름인지 유효성검사
		// 존재하면 true, 없으면 false
		public static boolean is_StationName(String stationName) {
			
			boolean check = false;
			check = Data.ALL_STATION_NAME.stream()
								          .anyMatch(name -> name.equals(stationName));
			
			return check;
			
		}//End of is_StationName()
		
		// 입력된 값이 "N호선"인지 유효성 검사
		// 존재하면 true, 없으면 false
		public static boolean is_Line(String line) {
			
			boolean check = false;
			
			for(int i = 1; i <= 9; i++) {
				
				if(line.equals( i + "호선")) {
					check = true;
				}
				
			}
			
			return check;
		}
		
		// 정기권 코드 유효성 검사
		// 정기권코드면 true, 아니면 false반환
		public static boolean is_Pass(String passCode) {
			
			boolean check = Data.passList.stream().anyMatch(pass -> pass.equals(passCode));
			
			return check;
		}//End of is_Pass()
		
		// 문자열이 숫자로만 이루어진 문자열인지 검사 
		public static boolean is_NumString(String input) {
			
			for(int i = 0; i < input.length(); i++) {
				
				char ch = input.charAt(i);
				// 숫자가 아닌 문자열 발견
				if( ch < '0' || ch > '9') {
					return false;
				}
				
			}
			
			return true;
		}//End of is_NumString()
		
		public static boolean is_UserId(String input) {
			boolean check = false;
			
			check = Data.userList.stream().anyMatch(user -> user.getId().equals(input));
			
			return check;
		}
	
		public static boolean is_EmployeeId(String input) {
			boolean check = false;
			
			check = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(input));
			
			return check;
		}
		
		// 입력받은 문자가 직급인지 확인
		// 맞으면 true, 아니면 false
		public static boolean is_Position(String input) {
		
			String[] position = { "안전요원", "사원", "대리", "과장", "부장", "사장" };
			
			for(int i = 0; i < position.length; i++) {
				
				if(position[i].equals(input)) {
					return true;
				}
				
			}
			
			
			return false;
		}
		
		public static boolean is_Sudo(String input) {
			
			boolean check = false;
			
			check = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(input) 
														 && employee.getLevel().equals("5"));
			
			return check;
		}
		
		// 입력받은 값이 2 또는 3의 일반 권한인지
		public static boolean is_Level(String input) {
			
			if(input.equals("2") || input.equals("3")) {
				return true;
			}
			
			return false;
		} 
		
		// 입력받은 아이디가 안전요원인지
		// 맞으면 true, 아니면 false
		public static boolean is_SafetyMan(String input) {
			
			boolean check = false;
			
			check = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(input) 
																 && employee.getPosition().equals("안전요원"));
			
			return check;
		}
		
		// 배정된 근무지가 없으면 true, 있으면 false
		public static boolean is_WorkArea(String input) {
			boolean check = false;
			
			check = Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(input)
																 && employee.getLine().equals("미정"));
			
			return check;
		}
		
}//End of class
