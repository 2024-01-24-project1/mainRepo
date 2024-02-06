package com.java.member.employee.stats;

import com.java.common.Data;

public class Stats {
	
	final static String[] date = { "202301", "202302", "202303", "202304", "202305", "202306"
								  , "202307", "202308", "202309", "202310", "202311", "202312" };

	
	// 전체 이용객
	public static long[] allPassenger() {
		
		
		long[] stats = new long[12];
	
		
		
		for(int i = 0; i < 12; i++) {
			final int index = i;
			
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().equals("호선"))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			
			
			
				month += Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("경인선") || count.getLine().equals("경부선"))
															.mapToLong(count -> count.getCounting())
															.sum();
			
			
			stats[i] = month;
			System.out.println(month);
		}
				
		
		return stats;
	}//End of allPassenger
	
	// 호선별 이용객
	// String으로 N호선을 입력받은면 해당 호선의 
	// 1월 ~ 12월 탑승객수가 담긴 12칸 long배열 반환
	public static long[] linePassneger(String lineNum) {
		
		long[] stats = new long[12];
		
		final String line = lineNum;
		
		
		for(int i = 0; i < 12; i++) {
			final int index = i;
			
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().equals(line))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			
		
			
			// 경인선/경부선 추가
			if(line.equals("1호선")) {
				month += Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("경인선") || count.getLine().equals("경부선"))
															.mapToLong(count -> count.getCounting())
															.sum();
			}
						
			
			
			
			stats[i] = month;
			System.out.println(month);
		}
				
		return stats;
	}
	
	// 전체 매출
	public static long[] allSales() {
		long[] stats = new long[12];
		
		
		for(int i = 0; i < 12; i++) {
			final int index = i;
			
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().equals("호선"))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			
			// 경인선/경부선 추가
			month += Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
														.filter(count -> count.getLine().equals("경인선") || count.getLine().equals("경부선"))
														.mapToLong(count -> count.getCounting())
														.sum();
									
			
			
			month = (long)(month * 0.75 * 1400) + (long)(month * 0.10 * 800) ;
			stats[i] = month;
			System.out.println(month);
		}
				
		
		return stats;
	}
	
	// 호선별 매출
	public static long[] lineSales(String lineNum) {
		long[] stats = new long[12];
		
		final String line = lineNum;
		
		
		for(int i = 0; i < 12; i++) {
			final int index = i;
			
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().equals(line))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			
			// 1호선 경인선 추가
			if(line.equals("1호선")) {
				month += Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("경인선") || count.getLine().equals("경부선"))
															.mapToLong(count -> count.getCounting())
															.sum();
			}
									
			
			month = (long)(month * 0.75 * 1400) + (long)(month * 0.10 * 800) ;
			stats[i] = month;
			System.out.println(month);
		}
				
		
		return stats;	
	}
	
	
	
	
}//End of class