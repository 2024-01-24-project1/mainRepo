package com.java.common;

import java.util.Scanner;

import com.java.member.user.User;
import com.java.view.ViewAll;
import com.java.member.employee.Employee;

// 회원가입
public class SignUp {

	public static void signUp() { // 회원가입 초기 화면
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			// View에서 출력
			ViewAll.signupMain();
			ViewAll.chooseNum();
			String sel = ""; // 선택한 번호
			sel = scan.nextLine();
			
			if (sel.equals("1")) { 		  // 1. 개인 회원가입
				
				commonSignUp(sel);
				
			} else if (sel.equals("2")) { // 2. 관리자 회원가입
				
				commonSignUp(sel);
				
			} else if (sel.equals("")) { // 3. 뒤로가기
				
				break;
				
			} else { // 이외의 숫자 입력 시
				ViewAll.errorQuestionEmo();
				System.out.println("\t\t\t해당 섹션이 없습니다.");
				System.out.println("\t\t\t다시 입력해주세요.");
				ViewAll.pause();
			
			
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
				
				Employee employee = new Employee(name, id, pw, registration, phone); // 입력값 저장
				Data.employeeList.add(employee);
				
				System.out.println("\t\t\t회원가입이 완료되었습니다.");
				System.out.println("\t\t\t직원 " + name + "님 환영합니다.");
				ViewAll.pause();
				
				break;
				
			}else {
				
				String back = "";
				
				System.out.println("\t\t\t다시 입력하시려면 아무키나 입력하세요.");
				System.out.println("\t\t\t뒤로가시려면 엔터를 입력하세요.");
				System.out.print("\t\t\t입력: ");
				back = scan.nextLine();
				
				if(back.equals("")) {
					
					// 회원가입 종료
					break;
				}
				
			}
			
		}// while종료
		
	}//End of commonSignUp()
	
	public static String formatPhoneNumber(String phoneNumber) {
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
	
	public static String formatRRN(String rrn) {
		 // 입력된 주민등록번호에서 "-"를 모두 제거하고 숫자만 남김
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
