package com.test.java.stat;



public class test {
	public static void main(String[] args) {
		//요구사항] 성적을 입력받아 세로로된 막대그래프로 출력하시오.
		
		// 변수
		
	
	
//		}else {
//			inputScore(koreanScore, englishScore, mathScore, score);
//			printScore(score);
//		}
		
	}//End of main
	
	// 2차원배열을 출력함
	public static void printScore(String[][] score) {
		System.out.println("---------------------");
		for(int i = 0; i < score.length; i++) {
			
			for(int j = 0; j < score[0].length; j++) {
				if(j == score[0].length) {
					System.out.printf("%s", score[i][j]);
				}else {
					System.out.printf("%s\t", score[i][j]);
				}
				
			}
			System.out.println();
		}
		System.out.println("---------------------");
		System.out.printf("국어\t영어\t수학\t\n");
			
	}//End of printScore()
	
	// 점수3개와 2차원 배열을 받아서
	// 순서대로 막대그래프 모양의 String 2차원배열 저장
	public static void inputScore(int kor, int eng, int math, String[][] score) {
		int[] subject = { kor / 10, eng / 10, math / 10};
		
		for(int i = 0; i < score[0].length; i++) {
			
			for(int j = 0; j < score.length; j++) {
				if(j >= score.length -  subject[i]) {
					score[j][i] = "■";
				}else {
					score[j][i] = " ";
				}
				
			}
			
		}
		
		
	}//End of Score()
	
	public static boolean checkScore(int score) {
		
		// 성적범위가 이상할경우 true
		if(score < 0 || score > 100) {
			return true;
		}else {
			return false;
		}
		
	}//End of checkScore()
	
	
	
	
}//End of class
