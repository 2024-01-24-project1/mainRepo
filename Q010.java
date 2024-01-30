package com.test.question.day4;

public class Q010 {
	public static void main(String[] args) {
		digit(1);
		digit(56);
		digit(156);
		digit(1256);
		digit(12456);
	}

	private static void digit(int num) {
		//숫자가 10보다 작은지? 작으면 앞에"000"추가  
		//10보다 크면? >> 다시 그 숫자가 100보다 작은지?
		//100보다 작으면 > 앞에 "00"추가 
		//100보다 크면? >> 다시 그 숫자가 1000보다 작은지?
		//1000보다 작으면 > 앞에 "0" 추가
		//1000보다 크다면 > "" 빈문자열 추가하여 String으로 변경
		System.out.printf("%s\n",num < 10 ? "000"+num : num<100 ? "00"+num : num<1000 ? "0"+num : num+"" );
		
	}

}
