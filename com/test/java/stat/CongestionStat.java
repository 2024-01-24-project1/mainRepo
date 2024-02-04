package com.test.java.stat;

import java.io.BufferedReader;
import java.io.FileReader;

public class CongestionStat {

	
	
	
	

	public static long[] ridingPeople(String lines) { //이용객 수
		
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
			System.out.println("CongestionStat.ridingPeople");
			e.printStackTrace();
		}
		
		
		return AllCalData;
		
	}
	
	
	
	
	
	public static void ridingWon(long[] asd) { //매출
		
		long[] sales = asd; //=allcaldata
		long[] money = new long[12];
//		long[] moneyGraph = new long[12];
		
		
		
		for(int i=0; i<sales.length; i++) {
			
			money[i]=(long) (sales[i]*(1400*0.75+800*0.1)/1000);
//			sales[i]=money[i];
//			moneyGraph[i]=money[i]/1000;
//			 System.out.println((i+1)+"월 매출합계 : " + money[i] );
			 System.out.printf("%d월 매출 합계: %,d원\n",i+1,money[i]);
			
			  
		}
		
//		for(int i=0;i<money.length;i++) {
//			moneyGraph[i]=money[i]/1000;
//		}
//		
		
	}
//	public static void sum(long[] asd , int month) { //월 이용객
//		
//		long[] sales = asd;
//		long[] sum = new long[12];
//		String[][] people = new String[15][12];
//		
//	
//		
//		
//		month=1;
//		
//		
//	}





	public static void drawGraph(long[] asd, int month,long sub,long div) {
		// 그래프 그리는 메서드
		
		long[] sales = asd;
		long[] sum = new long[12];
		String[][] graph = new String[20][12];
		
		
		
//		for(int i=0;i<sum.length;i++) {
//			if(sales[i]>5000000) {
//				sum[i]=(sales[i]-4000000)/100000;
//			}else if(sales[i]>8000000) {
//				sum[i]=(sales[i]-8100000)/100000;
//			}else if(sales[i]>)
//			sum[i]=(sales[i]-sub)/div; // 나누는 값 변수
//		}
		
		
		
		for(int i=0; i<graph[0].length;i++) {
			
			for(int j=0;j<graph.length;j++) {
				if(j>=graph.length-sum[i]) {
					graph[j][i]="■";
				}else {
					graph[j][i]=" ";
				}
			}
		}
		
		
		for(int i = 0; i < graph.length; i++) {
			
			for(int j = 0; j < graph[0].length; j++) {
				if(j == graph[0].length) {
					System.out.printf("%s", graph[i][j]);
				}else {
					System.out.printf("%s\t", graph[i][j]);
				}
				
			}
			System.out.println();
		}
//		
//		for(int i=0;i<sales.length;i++) {
//			System.out.println(sales[i]);
//		}
		
		month=1;
		
		
		
		
		
		
	}
}