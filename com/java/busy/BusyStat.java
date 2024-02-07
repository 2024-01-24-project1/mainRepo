package com.java.busy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.station.management.StationManagement;

import com.java.view.ViewAll;


/**
 * 혼잡도 통계를 나타내는 클래스
 */

//입력은 호선, 방향 ,평일/주말 입력받아야함

public class BusyStat extends StationManagement{
	
	private StationManagement stationManagement;
	
	public BusyStat() {
		
		this.stationManagement = new StationManagement();
		
	} 
	
	public void busyStat() {
		
		
		try {
			
			boolean check = false;
			String line = "";
			String way = "";
			String dayOfWeek = "";
			String time = "";
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				
				ViewAll.statisticsChaosOne();
				System.out.print("호선: ");
				line = reader.readLine(); 
				
				if(line.contains("호선")) {
					line = line.replace("호선", "" );
				}
				
				if(line.equals("2")) {
					ViewAll.statisticsChaosTwoLine2();
					System.out.print("방향: ");
					
				}else {
					ViewAll.statisticsChaosTwo();
					System.out.print("방향: ");
				}
				way = reader.readLine();
				
				
				ViewAll.statisticsChaosThree();
				System.out.print("요일: ");
				dayOfWeek = reader.readLine();
				
				if(dayOfWeek.equals("주말")) {
					dayOfWeek = "토요일";
				}
				
				ViewAll.statisticsChaosFour();
				System.out.print("시간: ");
				time = reader.readLine();
				
				check = Validation.is_busyStat(line,way,dayOfWeek,time);
				
				if(check) {
					
					break;
					
				}else {
					
					ViewAll.trainAddError();

				}
				
			}
			
			
			this.route = lineRoute(line);
			printBusy(BusyManagement.searchBusy(line, way, dayOfWeek,this.route),line,way,dayOfWeek,time);
			
			ViewAll.pause();
			
			
		} catch (Exception e) {
			System.out.println("BusyStat.busyStat");
			e.printStackTrace();
		}
		
	}
	
	//time은 -5하면 됨.
	//혼잡도 인덱스 총 15개
	private void printBusy(ArrayList<Busy> list, String line, String way, String dayOfWeek, String time) {
		
		ArrayList<String> busyStatPage = new ArrayList<>();
		
		
		String title = String.format("\t\t\t호선: %s, 방향: %s, 요일: %s\n",line,way,dayOfWeek);
		String page = "";
		
		for(Busy b : list) {
			
			page = String.format("\t%-35s  \t: %-3.1f\n",b.getStation()+"역",b.getCrowded().get(Integer.parseInt(time)-5));
			busyStatPage.add(page);
		}
		
		LogSave.logSave(LogSave.BUSYSTAT);
		busyStatPage(busyStatPage, title);
	}
	
	public static void busyStatPage(ArrayList<String> list, String title) {
		// 리스트의 페이지수 계산
		int page = (int)(Math.ceil((double)list.size() / 5));

		int index = 0;		// 문자로 입력받은 숫자를 int로 변환

		Scanner scan = new Scanner(System.in);

		while(true) {

			String sel = "";	// 입력받는 문자열

			// View클래스 출력
			ViewAll.employeeSearch();

			System.out.println(title);
			list.stream().skip(index * 5)
			.limit(5)
			.forEach(busy -> System.out.println(busy));
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("Page| %s / %s\r\n", index + 1, page);
			System.out.println("엔터입력시 리스트보기를 종료합니다.");
			System.out.print("원하는 페이지: ");
			sel = scan.nextLine();

			if(sel.equals("")) {
				break;
			}else if (Validation.is_NumString(sel)) {
				index = Integer.parseInt(sel) - 1;

				if(index < 0 || index >= page) {
					System.out.println("페이지 범위를 벗어났습니다.");
					System.out.println("다시 입력해주세요.");
					index = 0;

				}

			}else {
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
			}

		}//while루프 종료
	}
	
	
	

}
