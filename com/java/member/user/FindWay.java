package com.java.member.user;

import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data;
import com.java.common.RequiredTime;

public class FindWay {
	
	private void findWay(String line, String startStation,String endStation) {
		
		//상행 하행 구분하기
//		boolean way = findLineWay(line,startStation,endStation);
		
		
	}

//	private boolean findLineWay(String line,String startStation, String endStation) {
//		
//		
//		List<RequiredTime> list =Data.requiredTimeList.stream()
//									 .filter(s -> s.getLine().equals(line))
//									 .collect(Collectors.toList());
//		
//		int startIndex = list.indexOf(startStation);
//		int endIndex = list.indexOf(endStation);
//		
//		if(startIndex > endIndex) {
//			return true;
//		}
//		
//		
//	}

}

