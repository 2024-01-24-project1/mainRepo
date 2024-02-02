package com.java.member.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class userVoice {
	
	private static String path;
	static {
		path = "dat\\uservoicelist.csv";
	}
	
	static Scanner scan = new Scanner(System.in);
	private static ArrayList<userVoiceInfo> list = new ArrayList<>();
	
	
	public static void voiceAll(){
		
		while(true) {
			list();	// 민원 리스트
			
			
			System.out.println("1. 민원 내용보기");
			System.out.println("2. ID로 민원 검색");
			
			String num = scan.nextLine();
			if(num.equals("1")) {
				read();
			} else if (num.equals("2")) {
				searchId();
			}
		}
		
	}
	
	
	/**
	 * ID로 민원 찾기
	 */
	public static void searchId() {
		
		try {
			
			boolean exist = false;
			
			BufferedReader reader = new BufferedReader(new FileReader(path));

			System.out.print("민원인 ID를 입력하세요. ");
			String name = scan.nextLine();
			
				
				for(int i=0; i<list.size(); i++) {
					//System.out.println(i);
					if(name.equals(list.get(i).getId())) {
					
						System.out.printf("%s | %s | %s\n", list.get(i).getId(), list.get(i).getTitle(), list.get(i).getRead());
						exist = true;
					}
				}
				if(exist == false) {
					System.out.println("존재하는 ID가 없습니다.");
				}
			
			System.out.println();
			reader.close();
			
		} catch (Exception e) {
			System.out.println("UserVoice.readAll");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 민원 리스트 확인
	 */
	public static void list() {
		
		
		try {
			// 공통 시작
			String line = "";
			int count = 1; // 민원 리스트 앞 번호
			int input = 0; // 민원 리스트 중 읽을 번호입력
			userVoiceInfo info;
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			
			
			while((line = reader.readLine()) != null) { // 민원 한 줄씩 불러오기
				String[] temp = line.split(",");
				userVoiceInfo voice = new userVoiceInfo(temp[0], temp[1], temp[2], temp[3]);
				list.add(voice);
			}
			
			MovePage mp = new MovePage();
			
			mp.showPage(list);
			
			System.out.println();
			reader.close();
			
		} catch (Exception e) {
			System.out.println("UserVoice.readAll");
			e.printStackTrace();
		}
	}
	
	/**
	 * 민원 읽기
	 */
	public static void read() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));

			
			//민원 읽기
			System.out.print("읽은 민원의 번호를 입력하세요.");
			int num = scan.nextInt();
			scan.nextLine();
			
			list.get(num).setRead("읽음"); // 입력 받은 번호 민원을 읽음 표시
			//System.out.printf("%s | %s\n", list.get(num).getGeneral(), list.get(num).getRead()); // 입력 받은 번호 민원 내용 출력 > 찐
			System.out.printf("%s |%s | %s\n", list.get(num).getId(), list.get(num).getGeneral(), list.get(num).getRead()); // > 가짜
			
			reader.close();
		} catch (Exception e) {
			System.out.println("UserVoice.readAll");
			e.printStackTrace();
		}
	}
	
	

//	public static void write() {
//		
//		try {
//			
//			BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
//			
//			for(int i=100; i<120; i++) {
//				String temp = String.format("abc%d,%d,가나다라마바사,0",i,i);
//				writer.write(temp);
//				writer.newLine();
//			}
//			
//			System.out.println("저장완료");
//			writer.close();
//			
//		} catch (Exception e) {
//			System.out.println("UserVoice.write");
//			e.printStackTrace();
//		}
//	}
}





//일반민원정보  아이디,제목,내용,읽음여부(boolean)