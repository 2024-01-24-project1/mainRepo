package com.java.member.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.view.ViewAll;

/**
 * 정기권 메뉴를 나타내는 클래스
 */
public class PassTab {

	
	/**
	 * PassTab클래스의 기본 생성자
	 */
	public PassTab() {
	}

	/**
	 * 정기권 메뉴를 나타내는 메서드
	 */
	public static void passTab() {
		
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			//ViewAll.ticketView();
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = "";
			
			ViewAll.print("정기권 페이지");
			System.out.println();
			System.out.println("\t\t\t   1. 나의 정기권 확인하기");
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t   2. 정기권 코드 입력");
			System.out.println();
			System.out.println("\t\t\t       뒤로가기 엔터");
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			System.out.print("\t\t\t\t입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 나의 정기권 확인하기
				myTicket();
			}else if (sel.equals("2")) {	// 2. 정기권 코드 입력 
				signPass();
			}else if (sel.equals("")) {	// 3. 뒤로가기
				
				// 정기권 탭 종료
				break;
			}else {							// 다시 입력받기
				
				//다시입력
				System.out.println();
				System.out.printf("\t\t\t해당 섹션이 없습니다\r\n\t\t\t다시입력해주세요.\r\n");
				ViewAll.pause();
			}
			
		}//while루프 종료
		
		
	}//End of passTab
	
	/**
	 * 고객의 정기권을 확인하는 메서드
	 */
	public static void myTicket() { // 1. 내 정기권 확인
		
		//ViewAll.title("나의 정기권 확인하기");
		
		if ( LoginLogout.pass.equals("있음") ) {
			
			System.out.println("\t\t\t"+LoginLogout.authName + "님의 정기권");
			System.out.println("\t\t\t     <사용 기간> ");
			System.out.println("\t\t\t" + LoginLogout.passExpiry);
			
		} else {
			System.out.println("\t\t\t정기권 미보유 상태입니다.");
		}
		
		ViewAll.pause();
	}//End of myTicket()
	
	/**
	 * 정기권을 등록하는 메서드
	 */
	public static void signPass() { // 2. 정기권 등록
		
		//ViewAll.title("정기권 등록");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("\t\t\t코드: ");
		/**
		 * 입력한 정기권 코드 값을 저장하는 변수
		 */
		String ticketCode = scan.nextLine();
		System.out.println();
		
		if ( Validation.is_Pass(ticketCode) && LoginLogout.pass.equals("없음")) { 	   // 새로운 정기권 등록
			
			/**
			 * 만료일 형식 지정을 위한 SimpleDateFormat 객체
			 */
			SimpleDateFormat expiry = new SimpleDateFormat("yyyyMMdd");
			/**
			 * 현재 날짜를 가져오기 위한 Calendar 객체
			 */
			Calendar cal = Calendar.getInstance();
			/**
			 * 현재 날짜를 Date 객체로 변환한 변수
			 */
			Date date = cal.getTime();
			/**
			 * 정기권 시작일을 문자열 형식으로 저장한 변수
			 */
			String start = expiry.format(date);		// 정기권 시작일
			cal.add(Calendar.DATE, 30);
			date = cal.getTime();
			/**
			 * 정기권 종료일을 계산하여 문자열 형식으로 저장한 변수
			 */
			String end = expiry.format(date);		// 정기권 종료일
			
			// 유저의 정기권 여부와 기간 추가
			for(User user : Data.userList) {
				
				if(user.getId().equals(LoginLogout.auth)) {
					user.setPassCheck("있음");
					user.setPassExpiry(start + "-" + end);
					break;
				}
				
			}
		
			//로그인에도 반영
			LoginLogout.pass = "있음";
			LoginLogout.passExpiry = start + "-" + end;
			
			// 정기권 코드 리스트에서 사용한 정기권 삭제
			for(int i = 0; i < Data.passList.size(); i++) {
				
				if(Data.passList.get(i).equals(ticketCode)) {
					Data.passList.remove(i);
					break;
				}
				
			}
			
			System.out.println("\t\t\t정상적으로 등록되었습니다.");
			
		} else if ( Validation.is_Pass(ticketCode) && LoginLogout.pass.equals("있음")) {	// 정기권 한달 추가
			
			String extend = "";
			
			try {
				/**
				 * 만료일 형식 지정을 위한 SimpleDateFormat 객체
				 */
				SimpleDateFormat expiry = new SimpleDateFormat("yyyyMMdd");
				/**
				 * 정기권 종료일을 저장하는 변수
				 */
				String end = LoginLogout.passExpiry.substring(9);		// 정기권 종료일
				/**
				 * 정기권 종료일을 Date 객체로 변환한 변수
				 */
				Date temp = expiry.parse(end);							// 문자열을 날짜로
				/**
				 * 현재 날짜를 가져오기 위한 Calendar 객체
				 */
				Calendar cal = Calendar.getInstance();
				cal.setTime(temp);										// 정기권 종료일 캘린더에 세팅
				cal.add(Calendar.DATE, 30);								// 30일 연장
				
				temp = cal.getTime();
				extend = expiry.format(temp);							// 정기권 종료일
				
				
			} catch (Exception e) {
				System.out.println("\t\t\tPassTab클래스에서 정기권 날짜 연장 오류");
				e.printStackTrace();
			}
			
			
			// 유저의 정기권 연장
			for(User user : Data.userList) {
				
				if(user.getId().equals(LoginLogout.auth)) {
					
					// 로그인에 반영
					LoginLogout.passExpiry = LoginLogout.passExpiry.substring(0, 10) + extend;
					
					// 리스트에 반영
					user.setPassExpiry(LoginLogout.passExpiry);
					
					break;	// User객체 탐색 종료
				}
				
			}
			
			
			// 정기권 코드 리스트에서 사용한 정기권 삭제
			for(int i = 0; i < Data.passList.size(); i++) {
				
				if(Data.passList.get(i).equals(ticketCode)) {
					Data.passList.remove(i);
					break;
				}
				
			}
			
			
			System.out.println("\t\t\t정기권 기간이 연장되었습니다");
			
		} else {
			System.out.println("\t\t\t유효하지 않은 정기권입니다.");
	
		}
		ViewAll.pause();
	}//End of registramtionTicket
	
}//End of class