package com.java.schedule;

import java.util.Collections;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.view.ViewAll;

/**
 * 일정 메뉴를 나타내는 클래스
 */
public class ScheduleTab {
	

	/**
	 * ScheduleTab클래스의 기본 생성자
	 */
	public ScheduleTab() {
	}
	
	/**
	 * 일정 메뉴를 나타내는 메서드
	 */
	public static void scheduleTab() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = ""; // 선택한 번호
			
			
			ViewAll.calMain();
			System.out.print("\t\t\t선택 (번호): ");
			sel = scan.nextLine();
			
			if(sel.equals("1")) {			// 1. 전체 일정 보기
				ScheduleSearch.schedulePage(Data.scheduleList);
			} else if(sel.equals("2")) {	// 2. 일정 추가
				addSchedule();
			} else if(sel.equals("3")) {	// 3. 일정 제거
				removeSchedule();
			} else if (sel.equals("")) {	// 3. 뒤로가기
				
				// 분실물탭 종료
				break;
					
			} else { // 이외의 숫자 입력 시
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
			}
			
		}//while루프 종료
		
	}//End of scheduleTab()
	/**
	 * 일정을 추가하는 메서드
	 */
	public static void addSchedule() {
		
		Scanner scan = new Scanner(System.in);
		 
		while(true) {
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = "";
			
			/**
			 * 일정의 날짜를 저장하는 변수
			 */
			String date = "";
			/**
			 * 역 이름을 저장하는 변수
			 */
			String station = "";
			/**
			 * 일정 내용을 저장하는 변수
			 */
			String content = "";
		
			System.out.println("\t\t\t추가할 스케줄을 입력해주세요.");
			System.out.printf("\t\t\t날짜(YYYY-MM-DD): ");
			date = scan.nextLine();
			System.out.printf("\t\t\t역              : ");
			station = scan.nextLine();
			ViewAll.calAdd();
			System.out.printf("\t\t\t스케줄내용      : ");
			content = scan.nextLine();
			
			if (station.endsWith("역")) {
	            // '역'을 제거한 문자열을 반환합니다.
				station = station.substring(0, station.length() - 1);
	        } 
			
			
			if( !Validation.is_ScheduleSame(date, content, station) && Validation.is_StationName(station) && Validation.is_Date(date)) {
						
				/**
				 * 일정 정보가 저장되어 있는 Schedule 인스턴스
				 */
				Schedule sch = new Schedule(date, station, content);
				Data.scheduleList.add(sch);
				
				//일정리스트 정렬
				Collections.sort(Data.scheduleList, Schedule.timeComparator);
				
				LogSave.logSave(LogSave.ADDSCHEDULE);
				System.out.println("\t\t\t스케줄이 추가되었습니다.");
				
				// 일정추가 종료
				break;
				
			}
			
			System.out.println("\t\t\t잘못된 입력입니다");
			System.out.println("\t\t\t다시 입력하시려면 아무키나 입력하세요");
			System.out.println("\t\t\t뒤로가시려면 엔터입력");
			System.out.printf("\t\t\t입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				
				// 일정 추가 종료
				break;
			}
		
		}//while루프 종료
		
	}//End of addSchedule
	/**
	 * 일정을 제거하는 메서드
	 */
	public static void removeSchedule() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			/**
			 * 입력한 값을 저장하는 변수
			 */
			String sel = "";
			/**
			 * 일정 날짜를 저장하는 변수
			 */
			String date = "";
			/**
			 * 일정 내용을 저장하는 변수
			 */
			String content = "";
			/**
			 * 역 이름을 저장하는 변수
			 */
			String station = "";
		
			ScheduleSearch.schedulePage(Data.scheduleList);
			
			System.out.println("\t\t\t삭제할 날짜(YYYY-MM-DD): ");
			date = scan.nextLine();
			System.out.println("\t\t\t삭제할 일정(정확하게)  : ");
			content = scan.nextLine();
			System.out.println("\t\t\t삭제할 일정의 역       : ");
			station = scan.nextLine();
			
			if (station.endsWith("역")) {
	            // '역'을 제거한 문자열을 반환합니다.
				station = station.substring(0, station.length() - 1);
	        } 
			
			// 삭제할 날짜와 일정이 스케줄에 있는지
			if( Validation.is_ScheduleSame(date, content, station) ) {
				
				for(Schedule schedule : Data.scheduleList) {
					
					if(schedule.getSchedule().equals(content) && schedule.getTime().equals(date)
							&& schedule.getStation().equals(station)) {
						
						Data.scheduleList.remove(schedule);
						LogSave.logSave(LogSave.REMOVESCHEDULE);
						System.out.println("스케줄이 삭제되었습니다.");
						break;	// schedule객체 탐색 종료
					}
					
				}
				
				// 스케줄 삭제 종료
				break;
			}
		
			
			System.out.println("\t\t\t잘못된 입력입니다.");
			System.out.println("\t\t\t다시 입력하시려면 아무키나 입려하세요");
			System.out.println("\t\t\t뒤로가시려면 엔터입력");
			System.out.print("\t\t\t입력: ");
			sel = scan.nextLine();
			
			if(sel.equals("")) {
				
				// 스케줄 삭제 종료
				break;
			}
		
		}//while루프 종료
		
	}//End of removeSchedule()
	
}//End of classs
