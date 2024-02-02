package com.java.common.lostarticle;

import java.io.BufferedReader;
import java.io.FileReader;

import com.java.common.Data;
import com.java.view.View;

public class LostArticleTab {
	public static void lostArticle() {
	
		View.title(" 분실물 목록 확인");
		
		System.out.println();
		
		// lostArticle csv 탐색, 한 줄씩 출력하기
		
		Data.lostArticleList.stream().
		
//		String path = "C:\\Users\\user\\Desktop\\work\\seoulsubway\\src\\data";
//		
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(path));
//			
//			String line = null;
//			
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
//			}
//			
//			reader.close();
//			
//		} catch (Exception e) {
//			System.out.println("분실물 목록 확인에서 에러");
//			e.printStackTrace();
//		}
		

		System.out.println();
		System.out.println("	 1. 분실물 검색하기");
		System.out.println("	 2. 다음 페이지");
		System.out.println("	 3. 이전 페이지");
		System.out.println("	 4. 뒤로 가기");
		System.out.println();
		
		System.out.println("--------------------------------------");
		System.out.print("선택 (번호): ");
		System.out.println();
		
		while (true) {
			
		}
		
		
	
	}

	
	
	
	
	
	
	
	

}
