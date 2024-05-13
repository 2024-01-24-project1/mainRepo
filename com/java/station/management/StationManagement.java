package com.java.station.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.java.busy.Busy;
import com.java.common.Data;
import com.java.common.Validation;
import com.java.requiredtime.RequiredTime;
import com.java.station.timetable.StationTime;
import com.java.view.ViewAll;

/**
 *  혼잡도를 이용한 길찾기, 역관리에 공통으로 사용하는 멤버들을 공용으로 사용하기 위해 상속하는 클래스
 */
public class StationManagement {

	/**
	 * StationManagement클래스의 기본 생성자
	 */
	public StationManagement() {
	}
	
	/**
	 * 방향을 boolean으로 표현한 변수 상행/외선 true 하행/내선 false
	 */
	protected boolean way = false; 										     //상행 true 하행 false;
	/**
	 * 해당 경로를 저장하는 변수
	 */
	protected ArrayList<String> route = new ArrayList<>(); 				     //경로
	/**
	 * 해당 경로에 있는 각각의 역마다의 시간표를 저장하는 변수
	 */
	protected ArrayList<ArrayList<String>> timeTable = new ArrayList<>();    //시간표
	/**
	 * 시간표를 통해 그 시간의 열차 수를 저장하는 변수
	 */
	protected ArrayList<ArrayList<String>> trainCount = new ArrayList<>();   //열차 수
	/**
	 * 해당 경로의 특정 시간대의 열차 수를 저장하는 변수
	 */
	protected ArrayList<Integer> specificHourTrainCount = new ArrayList<>(); //특정 시간대 열차 수
	/**
	 * 해당 경로의 전체 시간대의 혼잡도 정보를 저장한 변수
	 */
	protected ArrayList<Busy> busyList = new ArrayList<>();			         // 변경 전 전체 혼잡도
	/**
	 * 해당 경로의 특정 시간대의 혼잡도 수치를 저장한 변수
	 */
	protected ArrayList<Double> specificHourBusy = new ArrayList<>(); 	     // 변경 전 혼잡도
	/**
	 * 해당 경로의 특정 시간대의 수정된 혼잡도 수치를 저장한 변수
	 */
	protected ArrayList<Double> modifyBusy = new ArrayList<>(); 		     // 변경 후 혼잡도
	/**
	 * 변경 전의 혼잡도 수치를 한글로 변경한 뒤 저장하는 변수
	 */
	protected ArrayList<String> convertBusy = new ArrayList<>();		     // 변경 전 혼잡도 한글로 변경한 후 저장
	/**
	 * 변경 후의 혼잡도 수치를 한글로 변경한 뒤 저장하는 변수
	 */
	protected ArrayList<String> convertModifyBusy = new ArrayList<>();	     // 변경 후 혼잡도 한글로 변경한 후 저장 
	
	/**
	 * 열차 추가시 사용되는 예비 열차를 저장하는 변수
	 */
	public static int spareTrain = 10;                                       //열차 추가시 사용되는 예비열차
	

	
	
	
	/** 
	 *  해당 호선의 열차 대수가 저장된 ArrayList<String> train 에서
	 *  매개변수로 받은 time 시간대의 열차 대수를 저장하는 메서드
	 * @param time 원하는 시간대 ex)(time = 7)시의 한 호선의 각각의 역 열차 대수
	 */
	protected ArrayList<Integer> specificHourTrainCount(String time,ArrayList<ArrayList<String>> trainCount) {
		
		for(int i=0; i<trainCount.size(); i++) {
			
			int count = 0;
			
			ArrayList<String> train = trainCount.get(i);
			
			for(String s : train) {
				
				String[] temp = s.split(":");
				
				if(Integer.parseInt(temp[0]) == Integer.parseInt(time)) {
					
					count++;
				}
			}
			specificHourTrainCount.add(count);
		}
		
		return specificHourTrainCount;
	}
	
	/**
	 * 역 관리에서 혼잡도를 수정한 후 변경 전 혼잡도 수치와 변경 후 혼잡도 수치를 출력하는 출력문을 ArrayList에 저장하는 메서드
	 * 
	 * @param startStation 시작역
	 * @param endStation 도착역
	 * @param specificHourBusy 해당 경로의 특정 시간대의 혼잡도
	 * @param modifyBusy 수정한 후의 해당 경로의 특정 시간대의 혼잡도
	 * @param convertBusy 수정전 해당 경로의 특정 시간대의 혼잡도를 한글로 변경한 수치
	 * @param convertModifyBusy 수정된 해당 경로의 특정 시간대의 혼잡도를 한글로 변경한 수치 
	 * @param way 방향(상행/하행 or 외선/내선)
	 * @param route 해당 호선의 해당 경로
	 */
	protected void printBusy(String startStation, String endStation, ArrayList<Double> specificHourBusy,
			ArrayList<Double> modifyBusy, ArrayList<String> convertBusy, ArrayList<String> convertModifyBusy ,boolean way, ArrayList<String> route) {
		
		/**
		 * 출력문을 저장하는 ArrayList
		 */
		ArrayList<String> busyPage = new ArrayList<>();
		
		/**
		 * 해당 시간대의 역과 변경전 혼잡도 변경후 혼잡도 출력문을 저장하는 변수
		 */
		String page = "";
		/**
		 * 시작역 출력문 저장하는 변수
		 */
		String start = "";
		/**
		 * 도착역 출력문 저장하는 변수
		 */
		String end = "";
		
		start = String.format("시작역(%s)\n", startStation);
		
		if(way) { //true 상행선 or 외선
			
			
			for(int i=route.size()-1; i>=0; i--) {

				boolean loop = false;
				
				if(route.get(i).equals(startStation)) {
					
					loop = true;
					
				}
				
				while(loop) {
					
					if(route.get(i).equals(endStation)) {
						
						page = String.format("%-30s  \t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
						end = String.format("도착역(%s)\n", endStation);
						busyPage.add(page);
						loop = false;
						busyPage(busyPage, start, end);
						return;
						
					}
					
					page = String.format("%-30s  \t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					busyPage.add(page);
					i--;
				}
				
			}
			
		}else if(!way) { //false 하행선 or 내선 사가정> 건대 하행선
			boolean loop = false;
			
			
			for(int i=0; i<route.size(); i++) {
				
				if(route.get(i).equals(startStation)) {
					loop = true;
				}
				
				while(loop) {
					
					if(route.get(i).equals(endStation)) {
						
						page = String.format("%-25s     \t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
						end = String.format("도착역(%s)\n", endStation);
						busyPage.add(page);
						loop = false;
						busyPage(busyPage, start, end);
						
						return;
						
					}
					
					page = String.format("%-25s     \t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					busyPage.add(page);
					i++;
					
				}
				
			}
			
			
		}
		
		
		
	}
	
	/**
	 * ArrayList에 저장해 뒀던 출력문을 5개씩 나눠서 출력하는 메서드
	 * @param list printBusy메서드에서 ArrayList에 저장했던 출력문 리스트
	 * @param start 시작역 출력문
	 * @param end 도착역 출력문
	 */
	public static void busyPage(ArrayList<String> list, String start, String end) {
		/**
		 * 리스트의 페이지 수를 나타내는 변수
		 */
		int page = (int)(Math.ceil((double)list.size() / 5));
		
		/**
		 * 사용자가 보고 있는 페이지를 나타내는 변수
		 */
		int index = 0;		// 문자로 입력받은 숫자를 int로 변환
		

		Scanner scan = new Scanner(System.in);

		while(true) {

			String sel = "";	// 입력받는 문자열

			// View클래스 출력
			ViewAll.trainAddResult();
			System.out.println();
			
			System.out.println(start);
			list.stream().skip(index * 5)
			.limit(5)
			.forEach(busy -> System.out.println(busy));
			System.out.println(end);
			
			
			System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
			// 이름, ID, 전화번호, 직급, 호선, 역이름
			System.out.printf("\n\t\t\t\tPage| %s / %s\r\n", index + 1, page);
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
	

	/**
	 * 입력받은 호선의 전체 경로를 리턴해주는 메서드
	 * Data상 1번인덱스부터 마지막 인덱스 까지의 순서가 상행선 2호선은 외선
	 * 마지막 인덱스부터 1번 인덱스까지 방향이 하행선, 내선
	 * @param line 호선(1~9)
	 * @return 선택한 호선의 전체 경로 ArrayList of String
	 */
	public static ArrayList<String> lineRoute(String line) {
		
		ArrayList<String> route = new ArrayList<>();
		
		if(line.equals("1")) {
			route = Data.LINE1_STATION_NAME;
		}else if(line.equals("2")) {
			route = Data.LINE2_STATION_NAME;
		}else if(line.equals("3")) {
			route = Data.LINE3_STATION_NAME;
		}else if(line.equals("4")) {
			route = Data.LINE4_STATION_NAME;
		}else if(line.equals("5")) {
			route = Data.LINE5_STATION_NAME;
		}else if(line.equals("6")) {
			route = Data.LINE6_STATION_NAME;
		}else if(line.equals("7")) {
			route = Data.LINE7_STATION_NAME;
		}else if(line.equals("8")) {
			route = Data.LINE8_STATION_NAME;
		}else if(line.equals("9")) {
			route = Data.LINE9_STATION_NAME;
		}
		
		return route;
	}
	
	
	/**
	 * 선택한 노선에서 출발역과 도착역의 진행방향이 상행선인지 하행선인지 확인하는 메서드
	 * @param line 호선(1~9)
	 * @param startStation 시작역
	 * @param endStation 도착역
	 * @return 상행선이면 true 하행선이면 false
	 */
	protected boolean findLineWay(String line,String startStation, String endStation) { //이 메서드를 호출 할 때는 start와 end가 같지 않아야함 > 유효성검사
		
		/**
		 * 시작역의 인덱스
		 */
		int startIndex = 0;
		/**
		 * 도착역의 인덱스
		 */
		int endIndex = 0;
		
		List<RequiredTime> list =Data.requiredTimeList.stream()
									 .filter(s -> s.getLine().equals(line))
									 .collect(Collectors.toList());
		
		
		for(RequiredTime r : list) {
			
			if(r.getStation().equals(startStation)) {
				startIndex = list.indexOf(r);
			}else if(r.getStation().equals(endStation)) {
				endIndex = list.indexOf(r);
			}
			
		}
		
		if(startIndex > endIndex) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	/**
	 * 해당역의 시간표를 가져오는 메서드
	 * @param line 호선(1~9)
	 * @param station 역 이름
	 * @param way 방향(상행/외선 true or 하행/내선 false)
	 * @param dayOfWeek 요일(평일/주말)
	 * @return station의 시간표 (방향은 boolean way에 따라 상행 하행 나뉨) ArrayList of String stationTimeTable 
	 */
	protected ArrayList<String> findStationTime(String line, String station, boolean way, String dayOfWeek) {
		
		/**
		 * 조건에 만족하는 해당 역의 시간표를 저장하는 ArrayList
		 */
		ArrayList<String> stationTimeTable = new ArrayList<>();
		
		
		line = line+"호선";
		
		for(StationTime s : Data.stationTimeList) {
			
			if(s.getStationName().equals(station) && s.getLine().equals(line) ) { //호선, 역이름 같은지
				
				if(dayOfWeek.equals("평일")) {
					
					if(way) { //true면 상행선
						
						stationTimeTable=s.getUpNomal();
						
					}else if(!way) { //false면 하행선
						
						stationTimeTable=s.getDownNomal();
						
					}
					
					
					
				}else if(dayOfWeek.equals("토요일")) {
					
					if(way) {
						
						stationTimeTable=s.getUpHoliday();
						
					}else if(!way) {
						
						stationTimeTable=s.getDownHoliday();
						
					}
					
				}
				
			}
			
		}
		
		
		return stationTimeTable;
		
		
	}
	
	/** 해당역의 시간표 ArrayList를 받아서 그 방향의 시간대만 추출해서 새로운 ArrayList로 반환하는 메서드
	 * 
	 * @param stationTimeTable 시작역의 시간표가 들어있는 ArrayList
	 * @return 해당 역의 시간만 추출후 리턴 ArrayList of String onlyTime
	 */
	protected ArrayList<String> timeExtraction(ArrayList<String> stationTimeTable) {
		
		/**
		 * 시간표 ArrayList에서 시간만 따로 저장하는 ArrayList
		 */
		ArrayList<String> onlyTime = new ArrayList<>();
		
		
		Pattern pattern = Pattern.compile("[0-9]{1,2}:\\d{2}");
		 
		
		
		for(String time : stationTimeTable) {
			
			Matcher matcher = pattern.matcher(time);
			if(matcher.find()) {
				onlyTime.add(matcher.group());
			}
					
		}
		return onlyTime;
	}

	
	
	
	
}
