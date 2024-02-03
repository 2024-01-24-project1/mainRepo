package com.test.java.stat;

public class GuestStat {

	public static int[] cal(String lines) {
		
		return CongestionStat.TotalCal(lines);
		
		// 달 별로 파일 가져와서 > 계산 > 출력
		
		//1 파일 가져오기
		
		
		
		
		long[] score=new long[12];
		
		for(int i=0; i<score.length;i++) {
			if(score[i]>=(i+1)*10) {
				score[i]="■";
			}else {
				score[i]="";
			}
		}
		
	}

}
