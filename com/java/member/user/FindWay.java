package com.java.member.user;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.java.busy.Busy;
import com.java.busy.BusyManangement;
import com.java.busy.BusyStat;
import com.java.common.Data;
import com.java.common.RequiredTime;

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
		boolean loop = false;
		int minute = 0;
		int second = 0;
		LocalTime localTime = LocalTime.of(0, 0, 0);
		
		
		requiredTime = (ArrayList<RequiredTime>) Data.requiredTimeList.stream().filter(s-> s.getLine().equals(line)).collect(Collectors.toList());
		
		

		//true면 상행선
		if(way) {

			for(int i=requiredTime.size()-1; i>=0; i--) {

				while(loop) {

					String temp[] =requiredTime.get(i).getTime().split(":");
					localTime = localTime.plusMinutes(Integer.parseInt(temp[0]));
					localTime = localTime.plusSeconds(Integer.parseInt(temp[1]));
					route.add(requiredTime.get(i).getStation());

				}
				if(requiredTime.get(i).getStation().equals(startStation)) { //시작역 이후 시간
					loop = true;
					route.add(requiredTime.get(i).getStation()); //시작역 이름 저장
				}
				if(requiredTime.get(i).getStation().equals(endStation)) {
					loop = false;
				}


			}
			
			minute = localTime.getMinute();
			
			if(localTime.getSecond()>0) { //x초 올림
				minute += 1;
			}


		}else { // false 하행선
			
			for(int i=0; i<requiredTime.size(); i++) {
				
				while(loop) {

					String temp[] =requiredTime.get(i).getTime().split(":");
					localTime = localTime.plusMinutes(Integer.parseInt(temp[0]));
					localTime = localTime.plusSeconds(Integer.parseInt(temp[1]));
					route.add(requiredTime.get(i).getStation());
					
					if(requiredTime.get(i).getStation().equals(endStation)) {
						loop = false;
					}
					i++;

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

		}
		
		//호선, 방향, 요일 (2호선제외)
		if(!line.equals("2")) {
			
			String dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK)>1 && calendar.get(Calendar.DAY_OF_WEEK)<7) ? "평일" : "주말";
			
			busyRoute = BusyStat.searchBusy(line, way ? "상선":"하선", dayOfWeek);
			
		}else {
			
			busyRoute = BusyStat.searchBusy(line, way ? "외선":"내선", (calendar.get(Calendar.DAY_OF_WEEK)>1&&calendar.get(Calendar.DAY_OF_WEEK)<7)?"평일":"주말");
			
		}
		
		for(Busy b : busyRoute) {
			
			if(route.contains(b.getStation())) {
				busyValue.add(b.getCrowded().get(calendar.get(Calendar.HOUR)-5));
				
			}
			
			
		}
		
		convertBusy = BusyManangement.convertBusy(busyValue); //혼잡도수치 한글로 변경후 저장
		
		printWay(route,convertBusy,minute);
		
	}
	
	//현재 시간 길찾기 출력
	private void printWay(ArrayList<String> route, ArrayList<String> convertBusy, int minute) {
		
		for(int i=0; i<route.size(); i++) {
			
			if(i==route.size()-1) {
				
				System.out.print(route.get(i)+"["+convertBusy.get(i)+"]");
				break;
			}
			
			System.out.print(route.get(i)+"["+convertBusy.get(i)+"]"+"-");
			
			
		}
		System.out.println();
		System.out.printf("총 소요시간: %d분\n",minute);
		
		
		//printWay
	}

//	private LocalTime calcTime(String startStation, String endStation, LocalTime localTime, int i, ArrayList<String> route) {
//		
//		while(loop) {
//
//			String temp[] =Data.requiredTimeList.get(i).getTime().split(":");
//			localTime.plusMinutes(Integer.parseInt(temp[0]));
//			localTime.plusSeconds(Integer.parseInt(temp[1]));
//			route.add(Data.requiredTimeList.get(i).getStation());
//
//		}
//		if(Data.requiredTimeList.get(i).getStation().equals(startStation)) { //시작역 이후 시간
//			loop = true;
//			route.add(Data.requiredTimeList.get(i).getStation()); //시작역 이름 저장
//		}
//		if(Data.requiredTimeList.get(i).getStation().equals(endStation)) {
//			loop = false;
//		}
//		
//		return localTime;
//		
//	}

	
	/**
	 * 선택한 노선에서 출발역과 도착역의 진행방향이 상행선인지 하행선인지 확인하는 메서드
	 * @param line
	 * @param startStation
	 * @param endStation
	 * @return 상행선이면 true 하행선이면 false
	 */
	private boolean findLineWay(String line,String startStation, String endStation) { //이 메서드를 호출 할 때는 start와 end가 같지 않아야함 > 유효성검사
		
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

