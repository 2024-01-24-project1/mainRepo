package com.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.station.management.StationManagement;
import com.java.view.View;

// 유효성 검사 클래스 
// true false만 반환
public final class Validation {
	
		// id 유효성 검사
		// 형식이 맞으면 true, 아니면 false
		public static boolean is_Id(String id) {
			// 아이디 길이가 4자 이상 12자 이하여야 합니다.
	        if (id.length() < 4 || id.length() > 12) {
	            return false;
	        }
	        
	        // 아이디가 숫자로 시작하는지 확인합니다.
	        if (Character.isDigit(id.charAt(0))) {
	            return false;
	        }
	        
	        // 영어 소문자와 숫자를 포함하는지 확인하는 정규표현식을 사용합니다.
	        String regex = "^(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{4,12}$";
	        
	        // 정규표현식을 사용하여 아이디의 형식을 검사합니다.
	        return id.matches(regex);
	    }
		
		// pw 유효성 검사
		// 형식이 맞으면 true, 아니면 false
		public static boolean is_Pw(String pw) {
			// 비밀번호 길이가 8자 이상 15자 이하여야 합니다.
	        if (pw.length() < 8 || pw.length() > 15) {
	            return false;
	        }
	        
	        // 대문자, 소문자, 숫자, 특수문자를 포함하는지 확인하는 정규표현식을 사용합니다.
	        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!~*?]).+$";
	        
	        // 정규표현식을 사용하여 비밀번호의 형식을 검사합니다.
	        return pw.matches(regex);
	    }
	
		// 이름 유효성 검사
		// 형식이 맞으면 true, 아니면 false
		public static boolean is_Name(String name) {
			// 이름: 2~5자, 한글만

			String regex = "[가-힣]{2,5}$";
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(name);

			return !m1.find();
		}
	
		// 주민등록번호 유효성 검사 메서드
		// 형식이 맞으면 true, 아니면 false
	    public static boolean is_Registration(String registration) {
	        // 형식 검사
	        if (!isValidFormat(registration)) {
	            return false;
	        }

	        // 생년월일 유효성 검사
	        if (!isValidDate(registration.substring(0, 6))) {
	            return false;
	        }

	        // 유효성 검사
	        if (!isValidCheckDigit(registration)) {
	            return false;
	        }

	        return true;
	    }

	    // 주민등록번호 형식 검사 메서드
	    // 형식이 맞으면 true, 아니면 false
	    private static boolean isValidFormat(String registration) {
	        // 정규표현식을 사용하여 형식을 검사합니다.
	        String regex = "\\d{6}-[1-4]\\d{6}|\\d{13}";
	        return registration.matches(regex);
	    }

	    // 생년월일 유효성 검사 메서드
	    // 형식이 맞으면 true, 아니면 false
	    private static boolean isValidDate(String birthDate) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	        dateFormat.setLenient(false); // 엄격한 날짜 포맷 설정

	        try {
	            dateFormat.parse(birthDate);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	    }

	    // 주민등록번호 유효성 검사 메서드
	    // 형식이 맞으면 true, 아니면 false
	    private static boolean isValidCheckDigit(String registration) {
	        // 주민등록번호 가중치
	        int[] weights = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};

	        // 유효성 검사
	        int sum = 0;
	        for (int i = 0; i < 12; i++) {
	            int digit = Character.getNumericValue(registration.charAt(i));
	            sum += digit * weights[i];
	        }

	        int checkDigit = Character.getNumericValue(registration.charAt(12));
	        int remainder = (11 - (sum % 11)) % 10;

	        return checkDigit == remainder;
	    }
		
		// 전화번호 유효성 검사
	    // 형식이 맞으면 true, 아니면 false
		public static boolean is_Phone(String phone) {

		    // 정규표현식을 사용하여 전화번호 형식을 검사합니다.
		    String regex = "(010-\\d{4}-\\d{4})|(010\\d{4}\\d{4})";
		    if (!phone.matches(regex)) {
		        return false;
		    }

		    // '-'를 제외한 문자들이 모두 숫자인지 확인합니다.
		    for (int i = 0; i < phone.length(); i++) {
		        char ch = phone.charAt(i);
		        if ((i == 3 || i == 8) && ch != '-') {
		            return false;
		        } else if (i != 3 && i != 8 && !Character.isDigit(ch)) {
		            return false;
		        }
		    }

		    return true;
		}
	
		// 관리자 가입코드 유효성 검사
		// 코드가 맞으면 true, 아니면 false
		public static boolean is_Code(String code) {

			boolean check = false;

			if (code.equals(Data.EMPLOYEECODE)) {
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
		public static boolean is_ScheduleSame(String date, String content, String station) {
			boolean check = false;
			
			check = Data.scheduleList.stream().anyMatch(schedule -> 
													schedule.getSchedule().equals(content)
												 && schedule.getTime().equals(date)
												 && schedule.getStation().equals(station));
			
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
		
		// 분실물의 이름과 보관역을 받아서 
		// 존재하는 분실물인지 체크하는 메서드
		// 있으면 true, 없으면 false
		public static boolean is_Duplication_LostArticle(String lostArticle, String station) {
			boolean check = false;
			
			check = Data.lostArticleList.stream().anyMatch(article -> article.getArticle().equals(lostArticle) 
																   && article.getFindStation().equals(station)); 
			
			return check;
		}
		
		// 분실물의 이름과 보관역을 받아서 
		// 존재하는 분실물인지 체크하는 메서드
		// 있으면 true, 없으면 false
		public static boolean is_Duplication_UserVoice(String id, String title) {
			boolean check = false;
			
			check = Data.userVoiceList.stream().anyMatch(voice -> voice.getId().equals(id) 
															   && voice.getTitle().equals(title)); 
			
			return check;
		}
		
		public static boolean is_ExistBookMark(List<String> userBookMark) {
			
			if(userBookMark.size()==0) {
				
				System.out.println("즐겨찾기한 목록이 없습니다.");
				View.pause();
				return false;
			}
			return true;
		}
		
		public static boolean is_busyStat(String line,  String way, String dayOfWeek, String timeStr) {
			
			try {
				
				int time = Integer.parseInt(timeStr);
				
				if(time<5 && time > 25) {
					return false;
				}
				
				
			} catch (Exception e) {
				
				return false;
				
			}
			
			
			
			if(line.contains("호선")){
				line = line.replace("호선", "");
			}
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				
				return false;
				
			}
			
			if(!way.equals("상행") && !way.equals("하행") && !way.equals("내선") && !way.equals("외선")) {
				
				return false;
				
			}
			

			if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("주말")) {
				
				return false;
				
			}
			
			
			if(dayOfWeek.equals("주말")) {
				dayOfWeek = "토요일";
			}
			
			
			return true;
			
		}
		public static boolean is_addTrain(String line, String trainNums, String startStation, String endStation, String time,
				String dayOfWeek) {
			
			if(line.contains("호선")){
				line = line.replace("호선", "");
			}
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				return false;
			}
			
			
			
			//추가 열차수가 예비열차수를 넘기거나 이미 예비열차수가 0인경우
			
			try {
				if(StationManagement.spareTrain==0 && (Integer.parseInt(trainNums)-StationManagement.spareTrain)<=0) {
					return false;
				}
				
			} catch (Exception e) {
				
				return false;
				
			}

			if(!StationManagement.lineRoute(line).contains(startStation) && !StationManagement.lineRoute(line).contains(endStation)) {
				
				return false;

			}
			if(startStation.equals(endStation)) {
				return false;
			}
			

			if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("주말")) {
				
				return false;
				
			}
			
			
			if(dayOfWeek.equals("주말")) {
				dayOfWeek = "토요일";
			}
			
			return true;
			
		}
		
		public static boolean is_changeNoChiarTrain(String line, String startStation, String endStation, String time,
				String dayOfWeek) {
			
			if(line.contains("호선")){
				line = line.replace("호선", "");
			}
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				return false;
			}
			
			

			if(!StationManagement.lineRoute(line).contains(startStation) && !StationManagement.lineRoute(line).contains(endStation)) {
				
				return false;

			}

			if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("주말")) {
				
				return false;
				
			}
			
			
			if(dayOfWeek.equals("주말")) {
				dayOfWeek = "토요일";
			}
			
			return true;
			
		}
		
		public static boolean is_currentTime(String line, String startStation, String endStation) {
			
			
			startStation = startStation.endsWith("역") ? startStation.substring(0,startStation.length()-1) : startStation;
			endStation = endStation.endsWith("역") ? endStation.substring(0,endStation.length()-1) : endStation;
			
			if(line.contains("호선")){
				line = line.replace("호선", "");
			}
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				return false;
			}
			
			

			if(!StationManagement.lineRoute(line).contains(startStation) && !StationManagement.lineRoute(line).contains(endStation)) {
				
				return false;

			}

			
			
			return true;
			
		}
		
		public static boolean is_anotherDate(String yearStr, String monthStr, String dateStr, String hourStr, String minuteStr) {
			
			try {
	            int year = Integer.parseInt(yearStr);
	            int month = Integer.parseInt(monthStr);
	            int day = Integer.parseInt(dateStr);
	            int hour = Integer.parseInt(hourStr);
	            int minute = Integer.parseInt(minuteStr);
	            		

	            if (year < 1 || month < 1 || month > 12 || day < 1) {
	                return false; // 년, 월, 일이 유효하지 않음
	            }
	            
	            if (hour < 5 || hour > 24 || minute < 0 || minute > 59) {
	                return false; // 시간 또는 분이 유효하지 않음
	            }

	            int daysInMonth = getDaysInMonth(year, month);
	            return day <= daysInMonth;
	            
	        } catch (NumberFormatException e) {
	            return false; // 정수로 변환할 수 없는 문자열이 입력됨
	        }
			

			
			
			
		}
		
	    public static int getDaysInMonth(int year, int month) {
	        switch (month) {
	            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
	                return 31;
	            case 4: case 6: case 9: case 11:
	                return 30;
	            case 2:
	                return isLeapYear(year) ? 29 : 28;
	            default:
	                return -1; // 잘못된 월 입력
	        }
			

			
			
			
		}
		

	    public static boolean isLeapYear(int year) {
	        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	    }

	    
	 // 입력받은 문자열이 평일,주말인지 확인하는 메서드
	 		// 평일,주말 입력이면 true, 아니면 false
	 		public static boolean is_WeekOf(String input) {
	 			
	 			if(input.equals("평일") || input.equals("주말")) {
	 				return true;
	 			}else {
	 				return false;
	 			}
	 			
	 		}
	 		
	 		// 입력받은 문자열이 열차 운행시간인 5 ~ 24인지 확인하는 메서드
	 		// 맞으면 true, 아니면 false
	 		public static boolean is_OperationTime(String input) {
	 			
	 	        try {
	 	            int number = Integer.parseInt(input);
	 	            return number >= 5 && number <= 24;
	 	        } catch (NumberFormatException e) {
	 	            // 입력이 숫자가 아닌 경우에 대한 예외 처리
	 	            return false;
	 	        }
	 	    }
		
}//End of class
