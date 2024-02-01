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
					System.out.printf("%s | %s",list.get(i).type, list.get(i).storeLocation);
					exist = true;
				} 
			}
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
			MovePage mp = new MovePage();
			
			mp.showPage(list);
//			for(int i=0; i<list.size(); i++) {
//				System.out.printf("%s | %s\n", list.get(i).getType(), list.get(i).getStoreLocation());
//			}
			
			while (true) {
				
				System.out.print("삭제할 물건을 입력하세요. ");
				tempType = scan.nextLine();
				
				if (invalidatetype(tempType)) { // 유효성검사
					System.out.println("올바르지 않은 품목입니다.");
				} else {
					break;
				}
				
			}
			
			
			System.out.print("물건의 보관 위치(역)를 입력하세요. ");
			tempSta = scan.nextLine();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).type.equals(tempType) && list.get(i).storeLocation.equals(tempSta)) {
					list.remove(i);
				} else {
					String temp = list.get(i).type + "," + list.get(i).content + "," + 
								  list.get(i).discoverLocation + "," + list.get(i).storeLocation + "\n";
					writer.write(temp);
				}
			}
			
			System.out.println("분실물 제거가 완료되었습니다.");
			System.out.println();
			
			writer.close();
			reader.close();
			
		} catch (Exception e) {
			System.out.println("lostArticle.articleremove");
			e.printStackTrace();
		}
	}
	
	/**
	 * 분실물 추가
	 */
	public static void articleAdd() {
		
		try {
			
			article article = new article();
			Scanner scan = new Scanner(System.in);
			
	
			while (true) {
				
				System.out.print("품목을 입력하세요. ");
				article.type = scan.nextLine();
				
				if (invalidatetype(article.type)) {
					System.out.println("올바르지 않은 품목입니다.");
				} else {
					break;
				}
				
			}
			
			while (true) {
				
				System.out.print("자세한 설명을 입력하세요. ");
				article.content = scan.nextLine();
				
				if (invalidatetype(article.content)) {
					System.out.println("한글 숫자 , . ? ! 만 입력가능합니다.");
				} else {
					break;
				}
				
			}
			
			System.out.print("발견위치를 입력하세요. ");
			article.discoverLocation = scan.nextLine();
			
			System.out.print("보관위치를 입력하세요. ");
			article.storeLocation = scan.nextLine();
			
			
			
			article temp = new article(article.type, article.content, article.discoverLocation, article.storeLocation);
			
			list.add(temp);
	
		
		} catch (Exception e) {
			System.out.println("LostArticle.articleAdd");
			e.printStackTrace();
		}
	}

	private static boolean invalidatetype(String name) {
	
		String regex = "^[가-힣]{1,10}$";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(name);
	
		return !m1.find();
	}
	
	private static boolean invalidatecontent(String content) {
		
		String regex = "A-Za-z0-9가-힣 ";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(content);
	
		return !m1.find();
		
	}
}



















