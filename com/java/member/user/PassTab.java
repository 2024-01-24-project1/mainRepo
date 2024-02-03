package com.java.member.user;

import java.util.Calendar;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.view.View;

public class PassTab {

	public static void passTab() {
		
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			View.ticketView();
			String sel = "";
			
			System.out.println("정기권 페이지");
			System.out.println("1 -> 나의 정기권 확인하기");
			System.out.println("2 -> 정기권 코드 입력");
			System.out.println("3 -> 뒤로가기");
			System.out.print("입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 나의 정기권 확인하기
				myTicket();
			}else if (sel.equals("2")) {	// 2. 정기권 코드 입력 
				signPass();
			}else if (sel.equals("3")) {	// 3. 뒤로가기
				
				// 정기권 탭 종료
				break;
			}else {							// 다시 입력받기
				
				//다시입력
				System.out.println();
				System.out.printf("해당 섹션이 없습니다\r\n다시입력해주세요.\r\n");
				View.pause();
			}
			
		}//while루프 종료
		
		
	}//End of passTab
	
	public static void myTicket() { // 1. 내 정기권 확인
		
		View.title("나의 정기권 확인하기");
		
		if ( LoginLogout.pass.equals("있음") ) {
			
			System.out.println(LoginLogout.authName + "님의 정기권");
			System.out.print("사용 기간: ");
			System.out.print(LoginLogout.passExpiry);

		} else {
			System.out.println("정기권 미보유 상태입니다.");
		}
		
		View.pause();
	}//End of myTicket()
	
	public static void signPass() { // 2. 정기권 등록
		
		View.title("정기권 등록");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("         코드: ");
		String ticketCode = scan.nextLine();
		System.out.println();
		
		if ( Validation.is_Pass(ticketCode) && LoginLogout.pass.equals("없음")) { 	   // 새로운 정기권 등록
			
			System.out.println("정상적으로 등록되었습니다.");
	
			View.pause();
			
		} else if ( Validation.is_Pass(ticketCode) && LoginLogout.pass.equals("있음")) {	// 정기권 한달 추가
			
		} else {
			System.out.println("유효하지 않은 정기권입니다.");
	
			View.pause();
		}
	}//End of registramtionTicket
	
	
	
	
	
	
}//End of class