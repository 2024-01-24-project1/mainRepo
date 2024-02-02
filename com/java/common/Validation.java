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
	
}//End of class
