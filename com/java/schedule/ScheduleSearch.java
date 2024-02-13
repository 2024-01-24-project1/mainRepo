package com.java.schedule;

import java.util.List;
import java.util.Scanner;

import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.view.ViewAll;

/**
 * 일정을 검색하는 클래스
 */
public class ScheduleSearch {

	
	/**
	 * 일정을 페이지로 나눠 출력하는 메서드
	 * @param list 일정 리스트
	 */
	public static void schedulePage(List<Schedule> list) {

		// 리스트의 페이지수 계산
		/**
		 * 페이지 수를 나타내는 변수
		 */
		int page = (int)(Math.ceil((double)list.size() / 10));
		/**
		 * 현재 보고있는 페이지를 나타내는 변수
		 */
		int index = 0;		// 문자로 입력받은 숫자를 int로 변환

		Scanner scan = new Scanner(System.in);

		while(true) {
			/**
			 * 입력한 값을 저장 하는 변수
			 */
			String sel = "";	// 입력받는 문자열

			// View클래스 출력
			ViewAll.calList();

			list.stream().skip(index * 10)
			.limit(10)
			.forEach(schedule -> System.out.printf("%-10s| \t\t\t%-15s\t  |%-20s\r\n"
					, schedule.getTime()
					, schedule.getStation() + "역"
					, schedule.getSchedule()));
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("\t\t\tPage| %s / %s\r\n", index + 1, page);
			System.out.println("\t\t\t페이지모드를 종료하시려면 엔터입력");
			System.out.print("\t\t\t원하는 페이지: ");
			sel = scan.nextLine();

			if(sel.equals("")) {
				LogSave.logSave(LogSave.SCHEDULEPAGE);
				break;
			}else if (Validation.is_NumString(sel)) {
				index = Integer.parseInt(sel) - 1;

				if(index < 0 || index >= page) {
					System.out.println("\t\t\t페이지 범위를 벗어났습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					index = 0;
					ViewAll.pause();

				}

			}else {
				System.out.println("\t\t\t잘못된 입력입니다.");
				System.out.println("\t\t\t다시 입력해주세요.");
				ViewAll.pause();
			}

		}//while루프 종료

	}//End of schedulePage()

}//End of class
