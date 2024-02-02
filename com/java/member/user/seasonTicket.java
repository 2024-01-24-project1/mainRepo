package com.java.member.user;

import java.util.Scanner;

import com.java.common.Data;
import com.java.view.View;
import java.util.Calendar;

// 고객 > 정기권 
public class seasonTicket {
	public static void Ticket() {
		
		while (true) {
			ticketView();

			Scanner scan = new Scanner(System.in);
			String num = "";
			num = scan.nextLine();

			if (num.equals("1")) { // 1. 나의 정기권 확인하기
				myTicket();
			} else if (num.equals("2")) { // 2. 정기권 등록
				registrationTicket();
			} else if (num.equals("3")) { // 3. 뒤로가기
				break;
			} else { // 이외의 숫자 입력 시
				System.out.println("해당 섹션이 없습니다\r\n다시 입력해주세요.");
				System.out.println();
			} 
			
		} // while

	}
	
	private static void ticketView() {
		View.title("정기권");

		System.out.println();
		System.out.println("          1. 나의 정기권 확인하기");
		System.out.println("          2. 정기권 등록");
		System.out.println("          3. 뒤로가기");
		System.out.println();

		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
	}

	
	public static void myTicket() { // 1. 내 정기권 확인
		
		View.title("나의 정기권 확인하기");
		
		/** 정기권 등록 시 User의 passCheck를 "있음"으로 변경, passList에서 등록한 정기권 코드를 삭제해야 함 -> User의 passCheck가 "있음"이면 아래의 사용 기한 출력 */
		boolean check = true; // 실행을 위해 임시로 만든 변수
		
		if (check) {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			end.add(Calendar.DATE, 30);
			
			System.out.println();
			System.out.printf("사용 기한: %tF ~ %tF", start, end);
			System.out.println();

		} else {
			System.out.println("정기권 미보유 상태입니다.");
		}
		
		View.pause();
	}
	
	
	public static void registrationTicket() { // 2. 정기권 등록
		
		View.title("정기권 등록");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("         코드: ");
		String ticketCode = scan.nextLine();
		System.out.println();
		
		boolean checkTicket = Data.passList.stream().anyMatch(pass -> pass.equals(ticketCode));
		
		if (checkTicket) { 
			System.out.println("정상적으로 등록되었습니다.");

			View.pause();
			
		} else {
			System.out.println("유효하지 않은 정기권입니다.");

			View.pause();
		}
	}

}
