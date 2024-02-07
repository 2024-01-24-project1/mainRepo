package com.test.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Data {
	//경로 (추후 수정)
	final static String CALDAT = "C:\\class\\code\\java\\CalPack\\src\\com\\test\\java\\caldata.txt"; // 캘린더 데이터 파일 경로
	//파일 받기
	
	//ArrayList 선언
	public static ArrayList<CalInput> list = new ArrayList<CalInput>(); // score.txt 내용을 ArrayList에 저장

	// 불러오기 
	public static void load() {
		
		try {
			
			BufferedReader reader 
				= new BufferedReader(new FileReader(Data.CALDAT)); //읽기 선언
			
			String line = null;
			
			while ((line = reader.readLine()) != null) { 
				
				//텍스트 1줄 > Score 객체 1개
				
				//홍길동,100,90,80
				String[] temp = line.split(",");
				
				CalInput input = new CalInput(temp[0], 
										temp[1]);
									
				
				list.add(input);				 //(???)
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("Data.load");
			e.printStackTrace();
		}
		
	}

//프로그램 종료 > 메모리 > score.txt 저장 
	public static void save() {

		try {
			//쓰기 선언
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.CALDAT));
			// 반복문 
			for (CalInput input : Data.list) {

				String line = String.format("%s,%s\r\n", input.getDay(), input.getContent());

				writer.write(line);

			}
			//쓰기 종료
			writer.close();

		} catch (Exception e) {
			System.out.println("Data.save");
			e.printStackTrace();
		}

	}

}
