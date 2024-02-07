package com.java.member.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.view.ViewAll;

public class PassTab {

	public static void passTab() {
		
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			ViewAll.ticketView();
			String sel = "";
			
			System.out.println("정기권 페이지");
			System.out.println("1 -> 나의 정기권 확인하기");
			System.out.println("2 -> 정기권 코드 입력");
			System.out.println("뒤로가기 엔터");
			System.out.print("입력: ");
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
				System.out.printf("해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
				ViewAll.pause();
			}
			
		}//while루프 종료
		
		
	}//End of passTab
	
	public static void myTicket() { // 1. 내 정기권 확인
		
		ViewAll.title("나의 정기권 확인하기");
		
		if ( LoginLogout.pass.equals("있음") ) {
			
			System.out.println(LoginLogout.authName + "님의 정기권");
			System.out.println("     <사용 기간> ");
			System.out.println(LoginLogout.passExpiry);
			
		} else {
			System.out.println("정기권 미보유 상태입니다.");
		}
		
		ViewAll.pause();
	}//End of myTicket()
	
	public static void signPass() { // 2. 정기권 등록
		
		ViewAll.title("정기권 등록");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("         코드: ");
		String ticketCode = scan.nextLine();
		System.out.println();
		
		if ( Validation.is_Pass(ticketCode) && LoginLogout.pass.equals("없음")) { 	   // 새로운 정기권 등록
			
			SimpleDateFormat expiry = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			String start = expiry.format(date);		// 정기권 시작일
			cal.add(Calendar.DATE, 30);
			date = cal.getTime();
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
			
			System.out.println("정상적으로 등록되었습니다.");
			
		} else if ( Validation.is_Pass(ticketCode) && LoginLogout.pass.equals("있음")) {	// 정기권 한달 추가
			
			String extend = "";
			
			try {
				
				SimpleDateFormat expiry = new SimpleDateFormat("yyyyMMdd");
				String end = LoginLogout.passExpiry.substring(9);		// 정기권 종료일
				Date temp = expiry.parse(end);							// 문자열을 날짜로
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(temp);										// 정기권 종료일 캘린더에 세팅
				cal.add(Calendar.DATE, 30);								// 30일 연장
				
				temp = cal.getTime();
				extend = expiry.format(temp);							// 정기권 종료일
				
				
			} catch (Exception e) {
				System.out.println("PassTab클래스에서 정기권 날짜 연장 오류");
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
			
			
			System.out.println("정기권 기간이 연장되었습니다");
			
		} else {
			System.out.println("유효하지 않은 정기권입니다.");
	
		}
		ViewAll.pause();
	}//End of registramtionTicket
	
}//End of class