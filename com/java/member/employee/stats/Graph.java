package com.java.member.employee.stats;

import com.java.view.ViewAll;

/**
 * 통계 정보를 그래프로 출력하는 클래스
 */
public class Graph {
	
	/**
	 * Graph클래스의 기본 생성자
	 */
	public Graph() {
	}
	
	// 12칸짜리 통계와 통계의 단위를 받아서 그래프를 출력하는 메서드
	/**
	 * 12칸짜리 통계와 통계의 단위를 받아서 그래프를 출력하는 메서드
	 * @param list 1월부터 12월까지의 값이 담긴 배열
	 * @param unit 평균내기 위해 나누는 값
	 */
	public static void drawGraph(long[] list, long unit ) {
		// list는 1월부터 12월까지의 값이 담긴 배열
		
		/**
		 * 출력할 모양이 담기는 배열
		 */
		String[][] graph = new String[15][12];		// 출력할 모양이 담기는 배열
		/**
		 * unit으로 나눈 1월부터 12월까지의 값이 담긴 배열
		 */
		long[] parted = new long[12];// unit으로 나눈 1월부터 12월까지의 값이 담긴 배열
		
	
		ViewAll.clear();
		
		for(int i = 0; i < 12; i++) {
			
			parted[i] = list[i] / unit;
		}
		
		
		
		// graph배열에 막대그래프 모양으로 저장
		for(int i = 0; i < graph[0].length; i++) {
			
			for(int j = 0; j < graph.length; j++) {
				if(j >= graph.length -  parted[i]) {
					graph[j][i] = "■";
				}else {
					graph[j][i] = " ";
				}
				
			}
			
		}
		
		// 그래프 출력
		for(int i = 0; i < graph.length-5; i++) {
			
			for(int j = 0; j < graph[0].length; j++) {
				if(j == graph[0].length) {
					System.out.printf("%s     ", graph[i][j]);
				}else {
					System.out.printf("%s     ", graph[i][j]);
				}
				
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("1월  2월   3월   4월   5월   6월   7월   8월   9월  10월   11월  12월");
		System.out.println();
		System.out.println();
		
		

		
		
		for(int i=0;i<=list.length/2-1;i++) {
			String front1 = "";
			String front2 = "";
			String back1 = "";
			String back2 = "";
			
			if(list[i]>100000000000L) { 
				front1 = (list[i]/100000000)+ "억 ";
				front2 = (list[i+6]/100000000)+ "억 ";
				back1 =	(list[i]%10000) + "만";
				back2 =	(list[i+6]%10000) + "만";
			}else if(list[i]>10000000000L) {
				front1 = (list[i]/100000000) + "억 ";
				front2 = (list[i+6]/100000000) + "억 ";
				back1 = (list[i]%10000) + "만";
				back2 = (list[i+6]%10000) + "만";
			}else if(list[i]>100000000) {
				front1 = (list[i]/100000000) + "억 ";
				front2 = (list[i+6]/100000000) + "억 ";
				back1 = (list[i]%10000) + "만";
				back2 = (list[i+6]%10000) + "만";
			}else {
				front1 = " ";
				front2 = " ";
				back1 = (list[i]/10000) + "만";
				back2 = (list[i+6]/10000) + "만";
			}
			
			System.out.printf("\t\t%-2d월: %-3s%-7s\t%-2d월: %-3s%-7s\n", i+1, front1, back1, i+7, front2, back2);
		}
			
			
			
				
		
		ViewAll.pause();
	}//End of drawGraph()
	
	
	
}//End of class
