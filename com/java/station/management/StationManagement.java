package com.java.station.management;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.java.busy.Busy;
import com.java.common.Data;
import com.java.requiredtime.RequiredTime;
import com.java.station.timetable.StationTime;

/**
 * 길찾기, 혼잡도 관련 기능을 공유하는 클래스를 상속하기위한 추상클래스
 */
public class StationManagement {
	
	protected boolean way = false; 										     //상행 true 하행 false;
	protected ArrayList<String> route = new ArrayList<>(); 				     //경로
	protected ArrayList<ArrayList<String>> timeTable = new ArrayList<>();    //시간표
	protected ArrayList<ArrayList<String>> trainCount = new ArrayList<>();   //열차 수
	protected ArrayList<Integer> specificHourTrainCount = new ArrayList<>(); //특정 시간대 열차 수
	protected ArrayList<Busy> busyList = new ArrayList<>();			         // 변경 전 전체 혼잡도
	protected ArrayList<Double> specificHourBusy = new ArrayList<>(); 	     // 변경 전 혼잡도
	protected ArrayList<Double> modifyBusy = new ArrayList<>(); 		     // 변경 후 혼잡도
	protected ArrayList<String> convertBusy = new ArrayList<>();		     // 변경 전 혼잡도 한글로 변경한 후 저장
	protected ArrayList<String> convertModifyBusy = new ArrayList<>();	     // 변경 후 혼잡도 한글로 변경한 후 저장 
	
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
	
	protected void printBusy(String startStation, String endStation, String time, ArrayList<Double> specificHourBusy,
			ArrayList<Double> modifyBusy, ArrayList<String> convertBusy, ArrayList<String> convertModifyBusy ,boolean way, ArrayList<String> route) {
		
		
		
		System.out.printf("시작역(%s)\n", startStation);
		
		if(way) { //true 상행선 or 외선
			
			
			for(int i=route.size()-1; i>=0; i--) {

				boolean loop = false;
				
				if(route.get(i).equals(startStation)) {
					
					loop = true;
					
				}
				
				while(loop) {
					
					if(route.get(i).equals(endStation)) {
						
						System.out.printf("%-15s\t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
						System.out.printf("도착역(%s)\n", endStation);
						loop = false;
						return;
						
					}
					
					System.out.printf("%-15s\t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
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
						
						System.out.printf("%-15s\t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
						System.out.printf("도착역(%s)\n", endStation);
						
						
						loop = false;
						return;
						
					}
					
					System.out.printf("%-15s\t(%-5.1f %-5s) -> (%-5.1f %-5s)\n",route.get(i)+"역",specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					i++;
				}
				
			}
			
			
		}
		
		
		
	}
	

	/**
	 * 입력받은 호선의 전체 경로를 리턴해주는 메서드
	 * Data상 1번인덱스부터 마지막 인덱스 까지의 순서가 상행선 2호선은 외선
	 * 마지막 인덱스부터 1번 인덱스까지 방향이 하행선, 내선
	 * @param line
	 * @return 선택한 호선의 전체 경로 ArrayList<String> route
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
	 * @param line
	 * @param startStation
	 * @param endStation
	 * @return 상행선이면 true 하행선이면 false
	 */
	protected boolean findLineWay(String line,String startStation, String endStation) { //이 메서드를 호출 할 때는 start와 end가 같지 않아야함 > 유효성검사
		
		int startIndex = 0;
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
	 * @param line 호선
	 * @param station 해당역
	 * @param way	true 상행or외선, false 하행or내선
	 * @param dayOfWeek 요일(평일/주말)
	 * @return station의 시간표 (방향은 boolean way에 따라 상행 하행 나뉨) ArrayList<String> stationTimeTable 
	 */
	protected ArrayList<String> findStationTime(String line, String station, boolean way, String dayOfWeek) {
		
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
	 * @return 해당 역의 시간만 추출후 리턴 ArrayList<String> onlyTime
	 */
	protected ArrayList<String> timeExtraction(ArrayList<String> stationTimeTable) {
		
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
