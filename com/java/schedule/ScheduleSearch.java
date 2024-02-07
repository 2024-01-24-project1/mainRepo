package com.java.schedule;

import java.util.List;
import java.util.Scanner;

import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.view.ViewAll;


public class ScheduleSearch {

	public static void schedulePage(List<Schedule> list) {
			
			// 리스트의 페이지수 계산
			int page = (int)(Math.ceil((double)list.size() / 10));
			
			int index = 0;		// 문자로 입력받은 숫자를 int로 변환
			
			Scanner scan = new Scanner(System.in);
			
			while(true) {
				
				String sel = "";	// 입력받는 문자열
				
				// View클래스 출력
				System.out.println("======================================================");
				System.out.println("                  스케줄");
				System.out.println("======================================================");
				
				list.stream().skip(index * 10)
				 							 .limit(10)
				 							 .forEach(schedule -> System.out.printf("%-10s||%-15s\t|%-20s\r\n"
						 													, schedule.getTime()
						 													, schedule.getStation() + "역"
						 													, schedule.getSchedule()));
				// 이름, ID, 전화번호, 직급, 호선, 역이름
				System.out.printf("Page| %s / %s\r\n", index + 1, page);
				System.out.print("페이지모드를 종료하시려면 엔터입력");
				System.out.print("원하는 페이지: ");
				sel = scan.nextLine();
				
				if(sel.equals("")) { //페이지 종료
					LogSave.logSave(LogSave.SCHEDULEPAGE);
					break;
				}else if (Validation.is_NumString(sel)) {
					index = Integer.parseInt(sel) - 1;
					
					if(index < 0 || index >= page) {
						System.out.println("페이지 범위를 벗어났습니다.");
						System.out.println("다시 입력해주세요.");
						index = 0;
						ViewAll.pause();
						
					}
					
				}else {
					System.out.println("잘못된 입력입니다.");
					System.out.println("다시 입력해주세요.");
					ViewAll.pause();
				}
				
			}//while루프 종료
			
	}//End of schedulePage()
	
}//End of class
