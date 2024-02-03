package com.test.java.stat;


	
	/*
	 1. 혼잡도 통계
	 	
	 2. 이용객 통계
	 	1) 월 전체 이용 인원
	 		호선 전체
	 	2) 특정 호선 이용 인원
	 		-호선 선택 > 호선 하나만
	 	3) 호선별 이용 인원
	 		호선 전체(년 별로)
	 3. 매출 통계
	 	1) 월 전체 매출
	 		호선 전체
	 	2) 특정 호선 매출
	 		-호선 선택 > 호선 하나만
	 	3) 호선별 매출
	 		호선 전체(년 별로)
	 
	 
	 */
	
public class Graph {

    public static void main(String[] args) {
        // 예시 배열1
        long[] array1 = {40000000, 60000000, 80000000, 100000000};

        // 배열2를 만들고 배열1의 값을 20000000으로 나눈 값으로 복사
        long[] array2 = copyArrayWithDivision(array1, 20000000);

        // 결과 출력
        System.out.println("배열1:");
        printArray(array1);

        System.out.println("배열2:");
        printArray(array2);
    }

    private static long[] copyArrayWithDivision(long[] source, long divisor) {
        long[] destination = new long[source.length];

        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i] / divisor;
        }

        return destination;
    }

    private static void printArray(long[] array) {
        for (long value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
