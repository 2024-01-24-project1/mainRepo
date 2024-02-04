package com.test.java.stat;
import java.util.ArrayList;


public class YearWon {
	
	
	public static ArrayList<Long> sumYear() {
		
		
		
		ArrayList<Long> list = new ArrayList<>();
		
		
		
		for(int i=0; i<=9; i++) {
			
			String temp = String.valueOf(i); 
			long[] line = CongestionStat.ridingPeople(temp);
			
			list.add(i, sum(line));
		}
		return list;
		
	
		
			
	}
	

		
	
		
		
		
		









//	    //1호선 1년합계
//		long[] line1 = CongestionStat.TotalCal("1");
//		//2~9호선 1년합계
//		long[] line2 = CongestionStat.TotalCal("2");
//		long[] line3 = CongestionStat.TotalCal("3");
//		long[] line4 = CongestionStat.TotalCal("4");
//		long[] line5 = CongestionStat.TotalCal("5");
//		long[] line6 = CongestionStat.TotalCal("6");
//		long[] line7 = CongestionStat.TotalCal("7");
//		long[] line8 = CongestionStat.TotalCal("8");
//		long[] line9 = CongestionStat.TotalCal("9");
//			
//		
//		hashMap.put(1, sum(line1));
//		hashMap.put(2, sum(line2));
//		hashMap.put(3, sum(line3));
//		hashMap.put(4, sum(line4));
//		hashMap.put(5, sum(line5));
//		hashMap.put(6, sum(line6));
//		hashMap.put(7, sum(line7));
//		hashMap.put(8, sum(line8));
//		hashMap.put(9, sum(line9));
		
		
	
	
	public static long sum(long[] line) {
		
		long sum = 0;
		
		for(int i=0; i<line.length; i++) {
			
			 sum+=line[i];
			
		}
		
		return sum;
	
	}			
	}