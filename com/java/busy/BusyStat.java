package com.java.busy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data3;


/**
 * 혼잡도 통계를 나타내는 클래스
 */

//입력은 호선, 방향 ,평일/주말 입력받아야함

public class BusyStat {
	
	
	
	//time은 -5하면 됨.
	public static ArrayList<Busy> searchBusy(String line, String way, String dayOfWeek) {
		
		
		List<Busy> list =  Data3.busyList.stream()
							.filter(s -> s.getLines().equals(line))
							.filter(s -> s.getWay().equals(way) )
							.filter(s -> s.getDayOfWeek().equals(dayOfWeek))
							.collect(Collectors.toList());
							
		
		return new ArrayList<Busy>(list);
		
	}

	//혼잡도 인덱스 총 15개
	public static void printBusy(ArrayList<Busy> list, String line, String way, String dayOfWeek, String time) {
		
		String result = String.format("호선: %s\n"
			     	                + "방향: %s\n"
				                    + "요일: %s\n",line,way,dayOfWeek);
		
		System.out.println(result);
		
		for(Busy b : list) {
			
			System.out.printf("%s : %.1f",b.getStation(),b.getCrowded().get(Integer.parseInt(time)-5));
			System.out.println();
			
		}
		
	}
	
	

}
