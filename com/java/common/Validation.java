package com.java.common;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
			boolean check = false;
			
			check = Data.passList.stream().anyMatch(pass -> pass.equals(passCode));
			
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
		
		// 입력받은 문자열이 민원의 제목인지 확인
		// 맞으면 true, 아니면 false
		public static boolean is_UserVoiceTitle(String input) {
			boolean check = false;
			
			check = Data.userVoiceList.stream().anyMatch(title -> title.getTitle().equals(input));
			
			return check;
		}
		
		
		//입력받은 문자열이 분실물의 이름인지 확인
		// 맞으면 true, 아니면 false
		public static boolean is_LostArticle(String input) {
			boolean check = false;
			
			check = Data.lostArticleList.stream().anyMatch(article -> article.getArticle().equals(input));
			
			return check;
		}
		
		// 입력받은 날짜와 스케줄이 존재하는지
		// 맞으면 true, 아니면 false
		public static boolean is_Schedule(String date, String content) {
			boolean check = false;
			
			check = Data.scheduleList.stream().anyMatch(schedule -> 
													schedule.getSchedule().equals(content)
												 && schedule.getTime().equals(date));
			
			return check;
		}
		
		// YYYY-MM-DD의 형식을 검사
		// 맞으면 true, 아니면 false
		public static boolean is_Date(String date) {
	        try {
	            // 날짜를 파싱하여 LocalDate 객체로 변환
	            LocalDate parsedDate = LocalDate.parse(date);

	            // 날짜의 각 구성 요소를 가져옴
	            int year = parsedDate.getYear();
	            int month = parsedDate.getMonthValue();
	            int day = parsedDate.getDayOfMonth();

	            // 년도가 윤년인지 확인
	            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

	            // MM은 01부터 12까지 여야함
	            if (month < 1 || month > 12)
	                return false;

	            // DD는 MM의 값에 따라 제한
	            int maxDayOfMonth = parsedDate.lengthOfMonth();
	            if (day < 1 || day > maxDayOfMonth)
	                return false;

	            // 윤년에 따른 MM의 범위 제한
	            if (month == 2 && day == 29 && !isLeapYear)
	                return false;

	            // 모든 조건을 통과하면 유효한 날짜
	            return true;

	        } catch (DateTimeParseException e) {
	            // 날짜가 올바른 형식이 아닌 경우
	            return false;
	        }
	    }
		
		// 이미 존재하는 아이디인지 체크하는 메서드
		// 있으면 true, 없으면 false
		public static boolean is_Duplication_Id(String input) {
			boolean check = false;
			
			check = Data.userList.stream().anyMatch(user -> user.getId().equals(input)) 
					|| Data.employeeList.stream().anyMatch(employee -> employee.getId().equals(input));
			
			return check;
		}
		
		// 이미 존재하는 주민등록번호인지 체크하는 메서드
		// 있으면 true, 없으면 false
		public static boolean is_Duplication_RRN(String input) {
			boolean check = false;
			
			check = Data.userList.stream().anyMatch(user -> user.getRegistration().equals(input)) 
					|| Data.employeeList.stream().anyMatch(employee -> employee.getRegistration().equals(input));
			
			return check;
		}
		
		// 이미 존재하는 전화번호인지 체크하는 메서드
		// 있으면 true, 없으면 false
		public static boolean is_Duplication_Phone(String input) {
			boolean check = false;
			
			check = Data.userList.stream().anyMatch(user -> user.getPhone().equals(input)) 
					|| Data.employeeList.stream().anyMatch(employee -> employee.getPhone().equals(input));
			
			return check;
		}
		
}//End of class
