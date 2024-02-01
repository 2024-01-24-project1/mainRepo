package com.java.busy;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 혼잡도 수정 or 검색된 호선/시간대의 혼잡도를 보내는 역할
 */
public final class BusyManangement {
	
	
	public static void modifyBusy() {
		
		
	}
	
	/**
	 * 고객이 길찾기를 할때 시작노선과 도착노선까지의 혼잡도를 저장하는 메서드
	 */
	public static void filterBusy(ArrayList<String> route, Calendar calendar) {
		
//		String startStation = route.get(0);
//		String endStation = route.get(route.size()-1);
		
//		calendar.HOUR
		
		
		
		
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
	

}
