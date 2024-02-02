package com.test.java.stat;

import java.util.ArrayList;

public class SalesStat {
	public static void ChooseCal(long[] asd) { //이용객*이용료>매출
		
		
		
		
		long[] sales = asd;
		long[] money = new long[12];
		
		for(int i=0; i<sales.length; i++) {
			
			money[i]=(long) (sales[i]*(1400*0.75+800*0.1));
			 System.out.println((i+1)+"월 매출합계 : " + money[i] );
			  
		}

		

	
	YearWon yearWon=new YearWon();
	ArrayList<Long> receivedList=YearWon.sumYear();
	
	for(int i=1; i<receivedList.size(); i++) {
		
		System.out.printf("%d호선 이용객 합계: %,d명\n", i, receivedList.get(i));
		
	}
	
	for(int i=1; i<receivedList.size(); i++) {
		
		System.out.printf("%d호선 연 매출: %,.0f원\n", i,receivedList.get(i)*(1400*0.75+800*0.1));
	}
	


}}


