package com.java.busy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data;

/**
 *  특정 조건의 혼잡도를 검색하고 혼잡도 수치를 수정하는 클래스
 */
public final class BusyManagement {
	
	/**
	 * 입력받은 호선, 방향, 요일(평일/주말)을 필터링한 ArrayList를 반환함
	 * @param line 호선
	 * @param way 방향(상행,외선 / 하행,내선)
	 * @param dayOfWeek 요일(평일/주말)
	 * @return ArrayList<Busy> 입력받은 값만 포함되어 있는 list
	 */
	public static ArrayList<Busy> searchBusy(String line, String way, String dayOfWeek, ArrayList<String> route) {
		
		
		List<Busy> list = Data.busyList.stream()
							  .filter(s -> s.getLines().equals(line))
							  .filter(s -> s.getWay().equals(way) )
							  .filter(s -> s.getDayOfWeek().equals(dayOfWeek))
							  .filter(s -> route.stream().anyMatch(routeStation -> routeStation.equals(s.getStation())))
							  .collect(Collectors.toList());
							
		
		return new ArrayList<Busy>(list);
		
	}
	
	/**
	 * 수정한 혼잡도를 혼잡도가 저장된 ArrayList에 저장하는 메서드
	 * 
	 * @param line 호선
	 * @param way  방향(상행,외선 / 하행,내선)
	 * @param time 시간대(5~24)
	 * @param dayOfWeek  요일(평일/주말)
	 * @param modifyBusy 수정한 혼잡도 ArrayList
	 * @param route 선택한 호선의 경로 ArrayList
	 */
	public static void modifyBusyValue(String line, boolean way, String time, String dayOfWeek, ArrayList<Double> modifyBusy, ArrayList<String> route) {
		
		String temp ="";
		
		if(line.equals("2")){
			temp = way ? "외선" : "내선";
		}else {
			temp = way ? "상행" : "하행";
		}
		
		for(Busy b : Data.busyList) {
			
			if(b.getLines().equals(line) && b.getWay().equals(temp) && b.getDayOfWeek().equals(dayOfWeek) && route.contains(b.getStation())) {
				
				for(int i=0; i<route.size(); i++) {
					
					if(b.getStation().equals(route.get(i))) {
						b.getCrowded().set((Integer.parseInt(time)-5), modifyBusy.get(i));
					}
					
					
				}
				
				
			}
			
			
		}
		
		
		
	}
	
	/**
	 * 혼잡도 수치인 숫자를 한글로 변환해주는 메서드
	 *  매우혼잡  130이상
		혼잡      110이상 ~ 130미만
		다소 혼잡 80이상 ~ 110미만
		보통      60이상 ~ 80미만
		다소한산  30이상 ~ 60미만
		여유로움  30미만
	 */
	public static ArrayList<String> convertBusy(ArrayList<Double> list) {
		
		ArrayList<String> convertBusy = new ArrayList<>();
		
		for(double d : list) {
			
			if(d>=130) {
				convertBusy.add("매우혼잡");
			}else if(d>=110) {
				convertBusy.add("혼잡");
			}else if(d>=80) {
				convertBusy.add("다소혼잡");
			}else if(d>=60) {
				convertBusy.add("보통");
			}else if(d>=30) {
				convertBusy.add("다소한산");
			}else {
				convertBusy.add("여유로움");
			}
			
		}
		return convertBusy;
	}
	
	
	

}
