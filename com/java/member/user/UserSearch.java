package com.java.member.user;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Validation;
import com.java.view.ViewAll;

public class UserSearch {

	
	public static void userPage(ArrayList<User> list) {
		
		// 리스트의 페이지수 계산
		int page = (int)(Math.ceil((double)list.size() / 10));
		
		int index = 0;		// 문자로 입력받은 숫자를 int로 변환
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			String sel = "";	// 입력받는 문자열
			
			// View클래스 출력
			ViewAll.userSearch();
			
			list.stream().skip(index * 10)
			 			 .limit(10)
			 			 .forEach(user -> System.out.printf("%-8s|%-20s|%-13s|%-2s\r\n"
					 										, user.getName()
					 										, user.getId()
					 										, user.getPhone()
					 										, user.getPassCheck()));
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("\t\t\tPage| %s / %s\r\n", index + 1, page);
			System.out.print("\t\t\t엔터입력시 뒤로갑니다.");
			System.out.print("\t\t\t원하는 페이지: ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				break;
			}else if (Validation.is_NumString(sel)) {
				index = Integer.parseInt(sel) - 1;
				
				if(index < 0 || index >= page) {
					System.out.println("\t\t\t페이지 범위를 벗어났습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					index = 0;
					
				}
				
			}else {
				System.out.println("\t\t\t잘못된 입력입니다.");
				System.out.println("\t\t\t다시 입력해주세요.");
			}
			
		}//while루프 종료
		
	}//End of userPage
	
	
}//End of class
