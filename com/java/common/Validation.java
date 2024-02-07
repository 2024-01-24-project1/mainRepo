package com.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.station.management.StationManagement;

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
		    
		    // 비밀번호는 !, ~, *, ?, @ 중 하나 이상을 포함해야 합니다.
		    if (!pw.matches(".*[!~*?@].*")) {
		        return false;
		    }
		    
		    // 비밀번호는 숫자를 하나 이상 포함해야 합니다.
		    if (!pw.matches(".*\\d.*")) {
		        return false;
		    }
		    
		    // 비밀번호는 대문자나 소문자 중 하나 이상을 포함해야 합니다.
		    if (!pw.matches(".*[A-Z].*") && !pw.matches(".*[a-z].*")) {
		        return false;
		    }

		    // 모든 조건을 만족하면 true를 반환합니다.
		    return true;
		}
	
		// 이름 유효성 검사
		// 형식이 맞으면 true, 아니면 false
		public static boolean is_Name(String name) {
			// 이름: 2~5자, 한글만

			String regex = "[가-힣]{2,5}$";
			// 정규표현식을 이용하여 이름 유효성 검사
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(name);

			return matcher.matches();
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
	    
	    public static boolean is_RegistrationFormat(String registration) {
	        // 숫자와 "-"만을 포함하는지 확인
	        if (!registration.matches("\\d{13}|\\d{6}-\\d{7}")) {
	            return false;
	        }
	        // '-'를 제거하고 나머지 문자열이 전부 숫자로만 이루어져 있는지 확인
	        String digitsOnly = registration.replaceAll("[^0-9]", "");
	        return digitsOnly.length() == 13;
	    }

	    // 주민등록번호 형식 검사 메서드
	    // 형식이 맞으면 true, 아니면 false
	    private static boolean isValidFormat(String registration) {
	    	// 정규표현식을 사용하여 형식을 검사합니다.
	        String regex = "\\d{6}-\\d{7}";
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
	    	
	    	registration = registration.replaceAll("-", "");
	    	
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
	    
	    public static boolean is_PhoneFormat(String phoneNumber) {
	        // "-"를 포함한 형식인지 확인
	        if (phoneNumber.matches("\\d{3}-\\d{4}-\\d{4}")) {
	            return true;
	        }
	        // "-"를 포함하지 않은 형식인지 확인
	        if (phoneNumber.matches("\\d{11}")) {
	            return true;
	        }
	        return false;
	    }
	    
	    public static boolean is_Phone(String phoneNumber) {
	        String pattern = "^010-\\d{4}-\\d{4}$"; // 010으로 시작하는 000-0000-0000 형태의 정규 표현식
	        
	        // 정규 표현식을 이용하여 패턴 매칭 여부 확인
	        return phoneNumber.matches(pattern);
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
				return false;
			}
			return true;
		}
		
		public static ArrayList<String> is_busyStat(String line,  String way, String dayOfWeek, String timeStr) {
			
			ArrayList<String> error = new ArrayList<>();
			
			try {
				
				int time = Integer.parseInt(timeStr);
				
				if(time<5 && time > 25) {
					error.add("시간은 5~24사이값만 입력하세요.");
				}
				
				
			} catch (Exception e) {
				
				error.add("시간 형식이 올바르지 않습니다");
				
			}
			
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				
				error.add("입력한 호선이 올바르지 않습니다. (1~9호선)");
				
			}
			
			if(!way.equals("상행") && !way.equals("하행") && !way.equals("내선") && !way.equals("외선")) {
				
				error.add("방향 입력이 올바르지 않습니다.");
				
			}
			

			if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("토요일")) {
				
				error.add("요일(평일/주말)입력이 올바르지 않습니다.");
				
			}
			error.add("오류없음");
			
			
			
			return error;
			
		}
		public static ArrayList<String> is_addTrain(String line, String trainNums, String startStation, String endStation, String time,
				String dayOfWeek) {
			
			ArrayList<String> error = new ArrayList<>();
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				error.add("입력한 호선이 올바르지 않습니다. (1~9호선)");
			}
			
			if(startStation.equals(endStation)) {
				
				error.add("시작역과 도착역이 같습니다.");
				
			}
			
			
			
			//추가 열차수가 예비열차수를 넘기거나 이미 예비열차수가 0인경우
			
			try {
				if(StationManagement.spareTrain==0 && (Integer.parseInt(trainNums)-StationManagement.spareTrain)<=0) {
					error.add("예비 열차가 없습니다");
				}
				
			} catch (Exception e) {
				
				error.add("열차 추가시 숫자로만 입력하세요.");
				
			}

			if(!StationManagement.lineRoute(line).contains(startStation) || !StationManagement.lineRoute(line).contains(endStation)) {
				
				error.add(String.format("%s호선에 %s역 또는 %s역이 존재하지 않습니다.", line,startStation,endStation));

			}
			if(startStation.equals(endStation)) {
				error.add("시작역과 도착역이 같습니다.");
			}
			

			if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("주말")) {
				
				error.add("요일(평일/주말)입력이 올바르지 않습니다.");
				
			}
			
			
			try {
				
				if(Integer.parseInt(time)<5 || Integer.parseInt(time)>24) {
					
					error.add("시간은 5~24사이값만 입력하세요.");
				}
				
			} catch (Exception e) {
				
				error.add("시간 형식이 올바르지 않습니다");

				
			}
			
			
			
			error.add("오류없음");
			
			return error;
					
		}
		
		public static ArrayList<String> is_changeNoChiarTrain(String line, String startStation, String endStation, String time,
				String dayOfWeek) {
			
			ArrayList<String> error = new ArrayList<>();
			
			
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				
				error.add("입력한 호선이 올바르지 않습니다. (1~9호선)");
			}
			
			

			if(!StationManagement.lineRoute(line).contains(startStation) || !StationManagement.lineRoute(line).contains(endStation)) {
				
				error.add(String.format("%s호선에 %s역 또는 %s역이 존재하지 않습니다.", line,startStation,endStation));				
			}

			if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("주말")) {
				
				error.add("요일(평일/주말)입력이 올바르지 않습니다.");
				
			}
			if(startStation.equals(endStation)) {
				
				error.add("시작역과 도착역이 같습니다.");
				
			}
			
			try {
				
				if(Integer.parseInt(time)<5 || Integer.parseInt(time)>24) {
					
					error.add("시간은 5~24사이값만 입력하세요.");
				}
				
			} catch (Exception e) {
				
				error.add("시간 형식이 올바르지 않습니다");

				
			}
			
			
			error.add("오류없음");
			return error;
			
		}
		
		public static ArrayList<String> is_currentTime(String line, String startStation, String endStation) {
			
			ArrayList<String> error = new ArrayList<>();
			
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				error.add("입력한 호선이 올바르지 않습니다. (1~9호선)");

			}
			
			

			if(!StationManagement.lineRoute(line).contains(startStation) && !StationManagement.lineRoute(line).contains(endStation)) {
				
				error.add(String.format("%s호선에 %s역 또는 %s역이 존재하지 않습니다.", line,startStation,endStation));

			}
			
			if(startStation.equals(endStation)) {
				
				error.add("시작역과 도착역이 같음");
				
			}

			error.add("오류없음");

			return error;
			
		}
		
		public static ArrayList<String> is_anotherDate(String yearStr, String monthStr, String dateStr, String hourStr, String minuteStr) {
			
			ArrayList<String> error = new ArrayList<>();
			try {
				
	            int year = Integer.parseInt(yearStr);
	            int month = Integer.parseInt(monthStr);
	            int day = Integer.parseInt(dateStr);
	            int hour = Integer.parseInt(hourStr);
	            int minute = Integer.parseInt(minuteStr);
	            		

	            if (year < 1 || month < 1 || month > 12 || day < 1) {
	                error.add("년, 월, 일 형식이 올바르지 않음"); // 년, 월, 일이 유효하지 않음
	            }
	            
	            if (hour < 5 || hour > 24 || minute < 0 || minute > 59) {
	                error.add("시간 또는 분 형식이 올바르지 않음"); // 시간 또는 분이 유효하지 않음
	            }

	            int daysInMonth = getDaysInMonth(year, month);
	            if(day > daysInMonth) {
	            	error.add(String.format("입력하신 %d월에는 %d일이 없습니다.",month, day));
	            }
	            
	        } catch (NumberFormatException e) {
	            error.add("날짜 형식이 올바르지 않습니다.");
	            
	        }
			
			error.add("오류없음");
			
			return error;
			
			
			
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
	    
	    public static boolean is_bookMark(String line, String startStation, String endStation, String time) {
			
			
				
			//호선 입력 확인 (1~9호선)
			if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
					&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
					&& !line.equals("8") && !line.equals("9")) {
				return false;
			}
			
			

			if(!StationManagement.lineRoute(line).contains(startStation) || !StationManagement.lineRoute(line).contains(endStation)) {
				
				return false;

			}
			
			int selTime = Integer.parseInt(time);
			
			if(selTime>24 || selTime<5) {
				return false;
			}

			
			return true;
			
		}
		
}//End of class
