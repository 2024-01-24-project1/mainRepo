package com.test.java.stat;

import java.io.BufferedReader;
import java.io.FileReader;

public class CongestionStat {

	
	
	
	

	public static long[] TotalCal(String lines) {
		
		int count=1;
		int month = 1;
		long[] AllCalData = new long[12];
		
		if(lines.equals("1")) {
			lines = "1호선-경인선-경부선";
		}else {
			lines += "호선";

		}
		
		
		try {
			
			while(month<13) {
				String path = "dat\\riding_" + month + ".txt"; 
				BufferedReader reader
				= new BufferedReader(new FileReader(path));
				
				String line=null;
				
				while((line=reader.readLine())!=null) {
					
					String[]temp=line.split(","); //쪼개기
					
					for(int i=0; i<temp.length; i++) {
						
						temp[i] = temp[i].replace("\"", "");
						
					}
					
						//선택 호선 이용객 수 월별
					if(lines.contains(temp[1])) {
						
						AllCalData[month-1] += Integer.parseInt(temp[3]);
						
						//전체 이용객 수 월별
					}else if(lines.equals("0호선") && count !=1) {
						
						AllCalData[month-1] += Integer.parseInt(temp[3]);
						
					}			
					count++;
//				
				}
			
				month++;
                count=1;
				
			}
			month=1;
			
		} catch (Exception e) {
			System.out.println("CongestionStat.TotalCal");
			e.printStackTrace();
		}
		
		
		return AllCalData;
		
	}
	
	
	
	
	
	public static void ChooseCal(long[] asd) { //이용객*이용료>매출
		
		long[] sales = asd;
		long[] money = new long[12];
		
		
		
		for(int i=0; i<sales.length; i++) {
			
			money[i]=(long) (sales[i]*(1400*0.75+800*0.1));
//			 System.out.println((i+1)+"월 매출합계 : " + money[i] );
			 System.out.printf("%d월 매출 합계: %,d원\n",i+1,money[i]);
			  
		}
		
		
	}
	public static void sum(long[] asd , int month) { //월 이용객
		
		long[] sales = asd;
		long[] sum = new long[12];
		
		
		
		for(int i=0; i<sales.length; i++) {
			
			sum[i]=sales[i];
//			System.out.println((i+1)+"월 이용객 합계 : " + sum[i] );
			System.out.printf("%d월 이용객 합계: %,d명\n",i+1,sum[i]);
		}
		month=1;
		
		
	}
}
