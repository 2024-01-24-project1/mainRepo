package com.java.member.employee.stats;

import com.java.view.View;

public class Graph {
	
	// 12칸짜리 통계와 통계의 단위를 받아서 그래프를 출력하는 메서드 
	public static void drawGraph(long[] list, long unit) {
		// list는 1월부터 12월까지의 값이 담긴 배열
		String[][] graph = new String[15][12];		// 출력할 모양이 담기는 배열
		long[] parted = new long[12];				// unit으로 나눈 1월부터 12월까지의 값이 담긴 배열
		

		
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
		System.out.println("---------------------");
		for(int i = 0; i < graph.length-5; i++) {
			
			for(int j = 0; j < graph[0].length; j++) {
				if(j == graph[0].length) {
					System.out.printf("%s", graph[i][j]);
				}else {
					System.out.printf("%s\t", graph[i][j]);
				}
				
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("1월\t2월\t3월\t4월\t5월\t6월\t7월\t8월\t9월\t10월\t11월\t12월\t");
		View.pause();
	}//End of drawGraph()
	
	
	
}//End of class
