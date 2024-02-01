package com.java.member.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//분실물정보 : 품목,내용,발견위치,보관위치
public class lostArticle {

	private static String path;
	static {
		path = "dat\\lostArticle.csv";
	}
	
	static ArrayList<article> list = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	
//	while(true) {
//	if(num == 1) {
//			
//			
//		System.out.println("1. 분실물 추가");
//		System.out.println("2. 분실물 제거");
//		System.out.println("3. 분실물 검색");
//			
//		System.out.print("숫자 입력: ");
//		num = scan.nextInt();
//		System.out.println();
//			
//		if(num == 1) {
//			lostArticle.articleAdd();
//		} else if(num == 2) {
//			View.lostArticlelist();
//			lostArticle.articleremove();
//		} else if(num == 3) {
//			lostArticle.articleSearch();
//		} else if(num == 9) { // 뒤로가기
//			 break;
//		} else {
//			System.out.println("올바른 값을 입력하세요.");
//		}
	
	
	public static void articlALl() {
		
		String num;
		
		while(true) {
		
			System.out.println("1. 분실물 추가");
			System.out.println("2. 분실물 제거");
			System.out.println("3. 분실물 검색");
			
			System.out.print("숫자 입력: ");
			num = scan.nextLine();
			System.out.println();
			
			if(num.equals("1")) {
				articleAdd();
			} else if(num.equals("2")) {
				articleremove();
			} else if(num.equals("3")) {
				articleSearch();
			}
			
			
			
			
		}
	}
	
	/**
	 * 분실물 검색
	 */
	public static void articleSearch() {
		
		try {
			
			boolean exist = false;
			String line = null;
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			
			while((line = reader.readLine()) != null){
				String[] temp = line.split(",");
				article article = new article(temp[0], temp[1], temp[2], temp[3]);
				list.add(article);
			}
			
			System.out.print("찾고자 하는 물건을 입력하세요 : ");
			String product = scan.nextLine();
			
			
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).type.contains(product)) {
					System.out.printf("%s | %s\n", list.get(i).type, list.get(i).storeLocation);
					exist = true;
				} 
			}
			
			System.out.println();
			
			if(exist == false) {
				System.out.println("찾고자 하는 물건이 없습니다.");
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println("lostArticle.articleSearch");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 분실물 제거
	 */
	public static void articleremove() {
		
		boolean exist= false;
		
		while(true) {
			
			try {
				String line = null;
				String tempType = "";
				String tempSta = "";
				
				BufferedReader reader = new BufferedReader(new FileReader(path));
				
				while((line = reader.readLine()) != null){
				
					String[] temp = line.split(",");
					article article = new article(temp[0], temp[1], temp[2], temp[3]);
					
					list.add(article);
				
				}
				
				
				
				while (true) { // 물건 입력
					
					for(int i=0; i<list.size(); i++) {
					System.out.printf("%s | %s\n", list.get(i).getType(), list.get(i).getStoreLocation());
					}
//					MovePage mp = new MovePage();
//					mp.showPage(list);
					
					System.out.print("삭제할 물건을 입력하세요. ");
					tempType = scan.nextLine();
					
					break;
					// 유효성 검사[]
					
				}
				
				while(true) { // 물건 보관위치 입력
					
					System.out.print("물건의 보관 위치(역)를 입력하세요. ");
					tempSta = scan.nextLine();
					
					break;
					// 유효성 검사[]
				
				}
				
				for(int i=0; i<list.size(); i++) { 
					
					if(list.get(i).type.equals(tempType) && list.get(i).storeLocation.equals(tempSta)) { // 입력한 정보와 일치하는 분실물 검색
						list.remove(i); 																 // 일치하는 분실물 삭제
						System.out.println("분실물 제거가 완료되었습니다.");
						exist = true;
					} 
				}
				
				if(exist == false) {																	 // 존재하지 않을 경우 출력
					System.out.println("제거할 분실물이 존재하지 않습니다.");
				}
				
				System.out.println();
				reader.close();
				break;
				
				
			} catch (Exception e) {
				System.out.println("lostArticle.articleremove");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 분실물 추가
	 */
	public static void articleAdd() {
		
		while(true) { // 전체 반복문
			try {
				
				article article = new article(); // 분실물 객체 생성
				Scanner scan = new Scanner(System.in); // Scan 객체 생성
				
		
				while (true) {
					
					System.out.print("품목을 입력하세요. ");
					article.type = scan.nextLine();
					
					break;
					//유효성 검사[]
					
				}
				
				while (true) {
					
					System.out.print("자세한 설명을 입력하세요. ");
					article.content = scan.nextLine();
					
					break;
					//유효성 검사[]
					
				}
				
				while(true) {
					System.out.print("발견위치를 입력하세요. ");
					article.discoverLocation = scan.nextLine();
					
					break;
					//유효성 검사[]
				}
				
				while(true) {
					System.out.print("보관위치를 입력하세요. ");
					article.storeLocation = scan.nextLine();
					
					break;
					//유효성 검사[]
				}
				
				
				article temp = new article(article.type, article.content, article.discoverLocation, article.storeLocation); // 추가할 분실물 객체 생성
				
				list.add(temp); // 분실물 ArrayList에 추가
				System.out.println("분실물 추가 완료\n");
				break;
		
			
			} catch (Exception e) {
				System.out.println("LostArticle.articleAdd");
				e.printStackTrace();
			}
		}
	}
}




















