package com.java.busy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.station.StationNamePage;
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
			
			ArrayList<String> error = new ArrayList<>();
			String line = "";
			String way = "";
			String dayOfWeek = "";
			String time = "";
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				
				ViewAll.busyStatMain();
				System.out.print("\t\t\t호선           : ");
				line = reader.readLine(); 
				
				if(line.contains("호선")) {
					line = line.replace("호선", "" );
				}
				
				StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);
				
				if(line.equals("2")) {
					
					System.out.print("\t\t\t방향(외선/내선): ");
					
				}else {
					
					System.out.print("\t\t\t방향(상행/하행): ");
				}
				
				way = reader.readLine();
				
				
				System.out.print("\t\t\t요일(평일/주말): ");
				dayOfWeek = reader.readLine();
				
				if(dayOfWeek.equals("주말")) {
					dayOfWeek = "토요일";
				}
				
				System.out.print("\t\t\t시간(5~24)     : ");
				time = reader.readLine();
				
				error = Validation.is_busyStat(line,way,dayOfWeek,time);
				
				if(error.get(0).equals("오류없음")) {
					
					break;
					
				}else {
					
					if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
						return;                      //false 일 경우 뒤로가기
					}

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
			
			page = String.format("\t%-35s    \t: %-3.1f\n",b.getStation()+"역",b.getCrowded().get(Integer.parseInt(time)-5));
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
			ViewAll.busyStatMain();

			System.out.println(title);
			list.stream().skip(index * 5)
			.limit(5)
			.forEach(busy -> System.out.println(busy));
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("\t\t\tPage| %s / %s\r\n", index + 1, page);
			System.out.println("\t\t\t엔터입력시 리스트보기를 종료합니다.");
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
	}
	
	
	

}
