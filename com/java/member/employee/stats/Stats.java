package com.java.member.employee.stats;

import com.java.common.Data;

/**
 * 통계정보를 나타내는 클래스
 */
public class Stats {
	
	/**
	 * 날짜 정보가 들어있는 배열
	 */
	final static String[] date = { "202301", "202302", "202303", "202304", "202305", "202306"
								  , "202307", "202308", "202309", "202310", "202311", "202312" };

	
	// 전체 이용객
	/**
	 * 전체 이용객을 구하는 메서드
	 * @return 전체 이용객 long[]
	 */
	public static long[] allPassenger() {
		
		/**
		 * 월별 전체이용객을 저장할 배열
		 */
		long[] stats = new long[12];
		
		
		for(int i = 0; i < 12; i++) {
			/**
			 * 배열 인덱스 저장할 변수
			 */
			final int index = i;
			
			/**
			 * 해당 월의 전체 이용객을 저장하는 변수
			 */
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().contains("호선"))
											   		   		.filter(count -> !(count.getLine().equals("공항철도 1호선")))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			/**
			 * 해당 월의 전체 이용객을 저장하는 변수
			 */
			long monthAdd = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
														.filter(count -> count.getLine().equals("경인선") ||  count.getLine().equals("경부선"))
														.mapToLong(count -> count.getCounting())
														.sum();

			stats[i] = month + monthAdd;
		}
				
		
		return stats;
	}//End of allPassenger
	
	// 호선별 이용객
	// String으로 N호선을 입력받은면 해당 호선의 
	// 1월 ~ 12월 탑승객수가 담긴 12칸 long배열 반환
	/**
	 * 호선별 이용객을 저장하는 메서드
	 * @param lineNum 호선
	 * @return 1월 ~ 12월 탑승객수 long[]
	 */
	public static long[] linePassneger(String lineNum) {
		
		/**
		 * 월별 해당호선 이용객을 저장할 배열
		 */
		long[] stats = new long[12];
		
		/**
		 * 해당 호선을 저장할 변수
		 */
		final String line = lineNum;
		
		
		for(int i = 0; i < 12; i++) {
			/**
			 * 배열 인덱스를 저장할 변수
			 */
			final int index = i;
			
			/**
			 * 해당 호션 월별 이용객 누적 변수
			 */
			long monthAdd = 0;
			
			/**
			 * 해당 호선 월별 이용객 저장 변수
			 */
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().equals(line))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			
			
			if(line.equals("1호선")) {
				monthAdd = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("경인선") || count.getLine().equals("경부선"))
															.mapToLong(count -> count.getCounting())
															.sum();
			}
			
			if(line.equals("9호선")) {

				monthAdd = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("9호선2~3단계"))
															.mapToLong(count -> count.getCounting())
															.sum();

			}
			
			
			
			stats[i] = month + monthAdd;
			
		}
				
		return stats;
	}
	
	// 전체 매출
	/**
	 * 전체 매출을 구하는 메서드
	 * @return 1월 ~ 12월 전체 매출 long[]
	 */
	public static long[] allSales() {
		
		/**
		 * 월별 메출을 저장하는 배열
		 */
		long[] stats = new long[12];
		
		
		for(int i = 0; i < 12; i++) {
			/**
			 * 배열 인덱스를 저장하는 변수
			 */
			final int index = i;
			
			/**
			 * 해당월의 매출을 저장하는변수
			 */
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().contains("호선"))
											   		   		.filter(count -> !(count.getLine().equals("공항철도 1호선")))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			/**
			 * 해당월의 매출을 저장하는변수
			 */
			long monthAdd = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
														.filter(count -> count.getLine().equals("경인선") ||  count.getLine().equals("경부선"))
														.mapToLong(count -> count.getCounting())
														.sum();
			
			month += monthAdd;
			month = (long)(month * 0.75 * 1400) + (long)(month * 0.10 * 800) ;
			
			stats[i] = month;
		}
				
		
		return stats;
	}
	
	// 호선별 매출
	/**
	 * 호선별 매출을 구하는 메서드
	 * @param lineNum 호선
	 * @return 1월 ~ 12월 호선별 매출 long[]
	 */
	public static long[] lineSales(String lineNum) {
		/**
		 * 1월 ~ 12월 호선별 매출을 저장할 배열
		 */
		long[] stats = new long[12];
		
		/**
		 * 호선 이름을 저장할 변수
		 */
		final String line = lineNum;
		
		
		for(int i = 0; i < 12; i++) {
			/**
			 * 배열 인덱스를 저장할 변수
			 */
			final int index = i;
			
			/**
			 * 월 매출을 저장하는 변수
			 */
			long monthAdd = 0;
			
			/**
			 * 월 매출을 저장하는 변수
			 */
			long month = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
											   		   		.filter(count -> count.getLine().equals(line))
											   		   		.mapToLong(count -> count.getCounting())
											   		   		.sum();
			
			if(line.equals("1호선")) {
				monthAdd = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("경인선") || count.getLine().equals("경부선"))
															.mapToLong(count -> count.getCounting())
															.sum();
			}
			
			if(line.equals("9호선")) {

				monthAdd = Data.passengerCountingList.stream().filter(count -> count.getDate().contains(date[index]))
															.filter(count -> count.getLine().equals("9호선2~3단계"))
															.mapToLong(count -> count.getCounting())
															.sum();

			}
			
			month = (long)(month * 0.75 * 1400) + (long)(month * 0.10 * 800) ;
			stats[i] = month + monthAdd;
		}
				
		
		return stats;	
	}
	
	
	
	
}//End of class
