package com.java.common;

import java.util.Scanner;

import com.java.member.user.User;
import com.java.view.ViewAll;

import com.java.member.employee.Employee;

// 회원가입
/**
 * 회원가입을 하는 클래스
 */
public class SignUp {
	
	/**
	 * Singup클래스의 기본 생성자
	 */
	public SignUp() {
	}

	/**
	 * 회원가입 초기화면을 출력하는 메서드
	 */
	public static void signUp() { // 회원가입 초기 화면
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			// View에서 출력
			ViewAll.signupMain();
			ViewAll.chooseNum();
			
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = ""; // 선택한 번호
			sel = scan.nextLine();
			
			if (sel.equals("1")) { 		  // 1. 개인 회원가입
				
				commonSignUp(sel);
				
			} else if (sel.equals("2")) { // 2. 관리자 회원가입
				
				commonSignUp(sel);
				
			} else if (sel.equals("")) { // 3. 뒤로가기
				
				break;
				
			} else { // 이외의 숫자 입력 시
				System.out.println("\t\t\t해당 섹션이 없습니다.");
				System.out.println("\t\t\t다시 입력해주세요.");
				ViewAll.pause();
			
			
			}
		}//while문 종료
		
	}//End of SignUp

	/**
	 * 일반 고객 회원가입하는 메서드
	 * @param sel=1 일반회원
	 */
	private static void commonSignUp(String sel) {
		
		Scanner scan = new Scanner(System.in);
		
		/**
		 * ID를 저장하는 변수
		 */
		String id = "";
		/**
		 * PW를 저장하는 변수
		 */
		String pw = "";
		/**
		 * 이름을 저장하는 변수
		 */
		String name = "";
		/**
		 * 주민등록번호를 저장하는 변수
		 */
		String registration = "";
		/**
		 * 휴대폰 번호를 저장하는 변수
		 */
		String phone = "";
		/**
		 * 직원 코드를 저장하는 변수
		 */
		String code = "";

		while (true) {
			
			// View클래스 출력
			if(sel.equals("1")) {
				ViewAll.signupUser();
			}else if (sel.equals("2")) {
				ViewAll.signupEmployee();
			}
			
			// ID, PW, 이름, 주민등록번호, 전화번호 입력받기
			System.out.print("\t\t아이디        : ");
			id = scan.nextLine();
			
			System.out.print("\t\t비밀번호      : ");
			pw = scan.nextLine();
			
			System.out.print("\t\t이름          : ");
			name = scan.nextLine();
			
			System.out.print("\t\t주민등록번호  : ");
			registration = scan.nextLine();
			
			System.out.print("\t\t전화번호      : ");
			phone = scan.nextLine();
			
			if(sel.equals("2")) {
				
				System.out.println("\t\t\t직원 코드 입력");
				System.out.print("\t\t\tCODE: ");
				code = scan.nextLine();
				System.out.println();
			
			}
			
			
			// 회원가입 유효성 검사
			
			// 아이디 형식 검사
			if( Validation.is_Duplication_Id(id) ) {
				System.out.println("\t\t\t중복된 아이디입니다.");
			}else if ( !Validation.is_Id(id) ) {
				System.out.println("\t\t\t아이디 형식이 틀렸습니다.");
			}
			
			// 비밀번호 형식 검사
			if( !Validation.is_Pw(pw)) {
				System.out.println("\t\t\t비밀번호 형식이 틀렸습니다.");
				
			}
			
			// 이름 형식 검사
			if ( !Validation.is_Name(name)) {
				System.out.println("\t\t\t이름 형식이 틀렸습니다.");
				
			}
			
			// 주민등록번호 형식 검사
			if(Validation.is_RegistrationFormat(registration)) {
				
				// 주민등록번호 XXXXXX-XXXXXXX형태로 변환
				registration = formatRRN(registration);
				
				if(Validation.is_Duplication_Id(registration)) {
					System.out.println("\t\t\t중복된 주민등록번호입니다.");
				}else {
					
					if(!Validation.is_Registration(registration)) {
						System.out.println("\t\t\t유효하지 않은 주민등록번호입니다.");
					}
					
				}
				
			}else {
				System.out.println("\t\t\t주민등록번호 형식이 틀렸습니다.");
			}
			
			// 휴대폰번호 형식 검사
			if ( Validation.is_PhoneFormat(phone) ) {
				
				// 전화번호 XXX-XXXX-XXXX형태로 변환
				phone = formatPhoneNumber(phone);
				
				if(Validation.is_Duplication_Phone(phone)) {
					System.out.println("\t\t\t중복된 전화번호입니다.");
				}else {
					
					if(!Validation.is_Phone(phone)) {
						//010으로 시작하는지
						System.out.println("\t\t\t유효하지 않은 전화번호입니다.");
					}
					
				}
				
				
			}else {
				System.out.println("\t\t\t전화번호 형식이 틀렸습니다.");
			}
			

			
			// 회원코드 검사
			if( !Validation.is_Code(code) && sel.equals("2")) {
				System.out.println("\t\t\t회원코드가 틀렸습니다.");
			}
			
			
			// 회원이 모든 조건을 만족한 입력을 받은경우
			if( sel.equals("1") && Validation.is_Id(id) && Validation.is_Pw(pw) && Validation.is_Name(name) 
					&& Validation.is_Registration(registration) && Validation.is_RegistrationFormat(registration)
					&& Validation.is_Phone(phone) && Validation.is_PhoneFormat(phone)
					&& !Validation.is_Duplication_Id(id) && !Validation.is_Duplication_Phone(phone) && !Validation.is_Duplication_RRN(registration) ) {
				
				/**
				 * 고객의 정보가 들어있는 User 인스턴스
				 */
				User user = new User(name, id, pw, registration, phone); // 입력값 저장
				Data.userList.add(user);
				
				System.out.println("\t\t\t회원가입이 완료되었습니다.");
				System.out.println("\t\t\t회원 " + name + "님 환영합니다.");
				ViewAll.pause();
				
				break;
				
				
				// 회원이 모든 조건을 만족한 입력을 받은경우
			}else if(sel.equals("2") && Validation.is_Id(id) && Validation.is_Pw(pw) && Validation.is_Name(name) 
					&& Validation.is_Registration(registration) && Validation.is_RegistrationFormat(registration)
					&& Validation.is_Phone(phone) && Validation.is_PhoneFormat(phone)
					&& Validation.is_Code(code)
					&& !Validation.is_Duplication_Id(id) && !Validation.is_Duplication_Phone(phone) && !Validation.is_Duplication_RRN(registration) ) {
				
				/**
				 * 직원의 정보가 들어있는 Employee 인스턴스
				 */
				Employee employee = new Employee(name, id, pw, registration, phone); // 입력값 저장
				Data.employeeList.add(employee);
				
				System.out.println("\t\t\t회원가입이 완료되었습니다.");
				System.out.println("\t\t\t직원 " + name + "님 환영합니다.");
				ViewAll.pause();
				
				break;
				
			}else {
				/**
				 * 입력한 값을 저장하는 변수
				 */
				String back = "";
				
				System.out.println("\t\t\t다시 입력하시려면 아무키나 입력하세요.");
				System.out.println("\t\t\t뒤로가시려면 엔터를 입력하세요.");
				back = scan.nextLine();
				
				if(back.equals("")) {
					
					// 회원가입 종료
					break;
				}
				
			}
			
		}// while종료
		
	}//End of commonSignUp()
	
	/**
	 * 휴대폰 번호의 형식을 010-xxxx-xxxx로 변환하는 메서드
	 * @param phoneNumber 휴대폰 번호
	 * @return 형식을 맞춘 휴대폰 번호 String
	 */
	public static String formatPhoneNumber(String phoneNumber) {
		/**
		 * 매개변수로 받은 형식에서 -을 빼고 저장한 변수
		 */
		String cleanedNumber = phoneNumber.replaceAll("[^0-9-]", "");

	    // 숫자와 "-"가 12자리인 경우에만 변환 수행 (첫 번째 "-" 제외)
	    if (cleanedNumber.length() == 11) {
	        // 전화번호 형식에 맞게 "-" 삽입
	        return cleanedNumber.substring(0, 3) + "-" + cleanedNumber.substring(3, 7) + "-" + cleanedNumber.substring(7);
	    } else {
	        // 그 외의 경우는 입력된 전화번호를 그대로 반환
	        return phoneNumber;
	    }
	}
	
	/**
	 * 주민등록번호의 형식을 000000-0000000으로 변환하는 메서드
	 * @param rrn 주민등록번호
	 * @return 형식을 맞춘 주민등록번호 String
	 */
	public static String formatRRN(String rrn) {
		 // 입력된 주민등록번호에서 "-"를 모두 제거하고 숫자만 남김
		/**
		 * 매개변수로 받은 형식에서 -을 빼고 저장한 변수
		 */
        String cleanedNumber = rrn.replaceAll("[^0-9]", "");
        
        // 문자열의 길이가 13인 경우에만 "-"를 삽입하여 리턴
        if (cleanedNumber.length() == 13) {
            return cleanedNumber.substring(0, 6) + "-" + cleanedNumber.substring(6);
        } else {
            // 그 외의 경우는 입력된 전화번호를 그대로 리턴
            return rrn;
        }
    }
	
}//End of class
