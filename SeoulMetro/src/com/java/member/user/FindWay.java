package com.java.member.user;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.java.busy.Busy;
import com.java.busy.BusyManangement;
import com.java.busy.BusyStat;
import com.java.common.Data;
import com.java.common.RequiredTime;
import com.java.station.timetable.StationTime;

public class FindWay {
	
	
	
	public void findWay(String line, String startStation,String endStation, Calendar calendar) {
		
		//경로 저장
		
		ArrayList<String> route = new ArrayList<>();      //해당 경로
		ArrayList<Busy> busyRoute = new ArrayList<>();    //경로의 혼잡도
		ArrayList<Double> busyValue = new ArrayList<>();  //혼잡도 수치 저장 
		ArrayList<String> convertBusy = new ArrayList<>();//혼잡도 값 한글로 저장
		ArrayList<RequiredTime> requiredTime = new ArrayList<>(); 
		
		//상행 하행 구분하기
		boolean way = findLineWay(line,startStation,endStation);
		
		int minute = 0;
		
		LocalTime localTime = LocalTime.of(0, 0, 0);
		
		//요일 구분하기
		String dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK)>1 && calendar.get(Calendar.DAY_OF_WEEK)<7) ? "평일" : "주말";
		
		
		requiredTime = (ArrayList<RequiredTime>) Data.requiredTimeList.stream().filter(s-> s.getLine().equals(line)).collect(Collectors.toList());
		
		//현재 시간과 가장 가까운 열차 시간
		String departureTime = departureTrainTime(line, startStation, way, dayOfWeek ,calendar);

		//true면 상행선
		if(way) {

			minute = upwardLine(startStation, endStation, route, requiredTime, localTime);


		}else { // false 하행선
			
			minute = downwardLine(startStation, endStation, route, requiredTime,localTime);

		}
		
		//호선, 방향, 요일 (2호선제외)
		if(!line.equals("2")) {
			
			busyRoute = BusyStat.searchBusy(line, way ? "상선":"하선", dayOfWeek);
			
		}else {
			
			busyRoute = BusyStat.searchBusy(line, way ? "외선":"내선", dayOfWeek);
			
		}
		

		        for(Busy b : busyRoute) {
			
			if(route.contains(b.getStation())) {
				busyValue.add(b.getCrowded().get(calendar.get(Calendar.HOUR)-5));
				
			}
			
			
		}
		
		convertBusy = BusyManangement.convertBusy(busyValue); //혼잡도수치 한글로 변경후 저장
		
		
		
		printWay(route,convertBusy,minute, departureTime);
		
	}
	//열차 출발 시간 구하는 메서드
	private String departureTrainTime(String line, String startStation, boolean way, String dayOfWeek ,Calendar calendar) {
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		
		ArrayList<String> StartStationTime = findStartStationTime(line, startStation, way, dayOfWeek);
		
		ArrayList<String> onlyTime = timeExtraction(StartStationTime);
		
		for(String compareTime : onlyTime) {
			
			String[] temp = compareTime.split(":");
			
			
			if(hour<=Integer.parseInt(temp[0])) {
				
				if(minute<=Integer.parseInt(temp[1])) {
					
					hour = Integer.parseInt(temp[0]);
					minute = Integer.parseInt(temp[1]);
					
					String result = hour +":"+minute;
					return result;
				}
				
			}
			
		}
		
		
		
		return "";
		
	}
	
	/**시작역의 시간표 ArrayList를 받아서 그 방향의 시간대만 추출해서 새로운 ArrayList로 반환하는 메서드
	 * 
	 * @param StartStationTime 시작역의 시간표가 들어있는 ArrayList
	 * @return 해당 역의 시간만 추출후 ArrayList로 담아서 리턴
	 */
	public ArrayList<String> timeExtraction(ArrayList<String> StartStationTime) {
		ArrayList<String> onlyTime = new ArrayList<>();
		
		
		Pattern pattern = Pattern.compile("[0-9]{1,2}:\\d{2}");
		
		
		
		for(String time : StartStationTime) {
			
			Matcher matcher = pattern.matcher(time);
			if(matcher.find()) {
				onlyTime.add(matcher.group());
			}
					
		}
		return onlyTime;
	}
	
	
	/**
	 * startStation의 시간표를 가져오는 메서드
	 * @param line 호선
	 * @param startStation 시작역
	 * @param way	true 상선, false 하선
	 * @param dayOfWeek 평일/주말
	 * @return startStation의 시간표 (방향은 boolean way에 따라 상행 하행 나뉨) ArrayList 반환
	 */
	public ArrayList<String> findStartStationTime(String line, String startStation, boolean way, String dayOfWeek) {
		
		ArrayList<String> StartStationTime = new ArrayList<>();
		
		
		line = line+"호선";
		
		for(StationTime s : Data.stationTimeList) {
			
			if(s.getStationName().equals(startStation) && s.getLine().equals(line) ) { //호선, 역이름 같은지
				
				if(dayOfWeek.equals("평일")) {
					
					if(way) { //true면 상행선
						
						StartStationTime=s.getUpNomal();
						
					}else if(!way) { //false면 하행선
						
						StartStationTime=s.getDownNomal();
						
					}
					
					
					
				}else if(dayOfWeek.equals("주말")) {
					
					if(way) {
						
						StartStationTime=s.getUpHoliday();
						
					}else if(!way) {
						
						StartStationTime=s.getDownHoliday();
						
					}
					
				}
				
			}
			
		}
		
		
		return StartStationTime;
		
		
	}

	/**
	 * 하행선일 경우 총 소요시간을 구하면서 가는 경로는 저장하는 메서드
	 * @param startStation 시작역
	 * @param endStation   도착역
	 * @param route        경로
	 * @param requiredTime 소요시간 들어있는 ArrayList
	 * @param localTime    소요시간 누적할 LocalTime 클래스 변수
	 * @return 총 소요 시간(분) int mintue;
	 */
	private int downwardLine(String startStation, String endStation, ArrayList<String> route,
			ArrayList<RequiredTime> requiredTime, LocalTime localTime) {
		
		boolean loop = false;
		int minute;
		
		for(int i=0; i<requiredTime.size(); i++) {
			
			while(loop) {

				String temp[] =requiredTime.get(i).getTime().split(":");
				localTime = localTime.plusMinutes(Integer.parseInt(temp[0]));
				localTime = localTime.plusSeconds(Integer.parseInt(temp[1])+30); //30초 정차시간 
				route.add(requiredTime.get(i).getStation());
				
				if(requiredTime.get(i).getStation().equals(endStation)) {
					loop = false;
					localTime = localTime.minusSeconds(30); //마지막역은 정차시간 포함 x
				}else {
					i++;
				}
				

			}
			if(requiredTime.get(i).getStation().equals(startStation)) { //시작역 이후 시간
				loop = true;
				route.add(requiredTime.get(i).getStation()); //시작역 이름 저장
			}
			
		}
		
		minute = localTime.getMinute();
		
		if(localTime.getSecond()>0) { //x초 올림
			minute += 1;
		}
		return minute;
	}
	
	/**
	 * 하행선일 경우 총 소요시간을 구하면서 가는 경로는 저장하는 메서드
	 * @param startStation 시작역
	 * @param endStation   도착역
	 * @param route        경로
	 * @param requiredTime 소요시간 들어있는 ArrayList
	 * @param localTime    소요시간 누적할 LocalTime 클래스 변수
	 * @return 총 소요 시간(분) int mintue;
	 */
	private int upwardLine(String startStation, String endStation, ArrayList<String> route,
			ArrayList<RequiredTime> requiredTime, LocalTime localTime) {
		
		boolean loop = false;
		int minute;
		
		for(int i=requiredTime.size()-1; i>=0; i--) {

			while(loop) {

				String temp[] =requiredTime.get(i).getTime().split(":");
				localTime = localTime.plusMinutes(Integer.parseInt(temp[0]));
				localTime = localTime.plusSeconds(Integer.parseInt(temp[1]));
				route.add(requiredTime.get(i).getStation());
				
				if(requiredTime.get(i).getStation().equals(endStation)) {
					loop = false;
				}
				
				i--;


			}
			if(requiredTime.get(i).getStation().equals(startStation)) { //시작역 이후 시간
				loop = true;
				route.add(requiredTime.get(i).getStation()); //시작역 이름 저장
			}


		}
		
		minute = localTime.getMinute();
		
		if(localTime.getSecond()>0) { //x초 올림
			minute += 1;
		}
		return minute;
	}
	
	//현재 시간 길찾기 출력 > 출력메서드 
	private void printWay(ArrayList<String> route, ArrayList<String> convertBusy, int minute, String departureTime) {
		
		for(int i=0; i<route.size(); i++) {
			
			if(i==route.size()-1) {
				
				System.out.print(route.get(i)+"["+convertBusy.get(i)+"]");
				break;
			}
			
			System.out.print(route.get(i)+"["+convertBusy.get(i)+"]"+"-");
			
			
		}
		System.out.println();
		
		
		System.out.printf("총 소요시간: %d분\n",minute);
		
		String[] temp = departureTime.split(":");
		
		int departureHour = Integer.parseInt(temp[0]);
		int departureMinute = Integer.parseInt(temp[1]);
		
		if((departureMinute+minute)>=60) {
			minute = (departureMinute+minute) % 60;
			departureHour+=1;
			
		}else {
			
			minute = departureMinute+minute;
			
		}
		
		if(departureHour==24) {
			departureHour =0;
		}
		
		System.out.printf("도착 예정 시간: %d시 %d분\n", departureHour,minute);
		
		//printWay
	}


	
	/**
	 * 선택한 노선에서 출발역과 도착역의 진행방향이 상행선인지 하행선인지 확인하는 메서드
	 * @param line
	 * @param startStation
	 * @param endStation
	 * @return 상행선이면 true 하행선이면 false
	 */
	public boolean findLineWay(String line,String startStation, String endStation) { //이 메서드를 호출 할 때는 start와 end가 같지 않아야함 > 유효성검사
		
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

}

