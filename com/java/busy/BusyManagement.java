package com.java.busy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data;

/**
 * 혼잡도 수정 or 검색된 호선/시간대의 혼잡도를 보내는 역할
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
	 * 혼잡도 수치인 숫자를 한글로 변환해주는 메서드
	 * 매우혼잡  130이상
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
	
	//해당 호선의 혼잡도를 가져오기
	
	
	

}
