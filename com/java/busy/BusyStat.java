package com.java.busy;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;

import com.java.station.management.StationManagement;
import com.java.view.ViewAll;


/**
 * 혼잡도 통계를 나타내는 클래스
 */

//입력은 호선, 방향 ,평일/주말 입력받아야함

public class BusyStat extends StationManagement{
	
	private StationManagement stationManagement;
	
	public BusyStat() {
		
		this.stationManagement = new StationManagement();
		
	} 
	
	public void busyStat() {
		
		
		try {
			
			boolean check = false;
			String lines = "";
			String way = "";
			String dayOfWeek = "";
			String time = "";
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				
				ViewAll.statisticsChaosOne();
				System.out.print("호선: ");
				lines = reader.readLine(); 
				
				ViewAll.statisticsChaosTwo();
				if(lines.equals("2") || lines.equals("2호선")) {
					
					System.out.print("방향(내선/외선): ");
					
				}else {
					
					System.out.print("방향(상행/하행): ");
				}
				way = reader.readLine();
				
				
				ViewAll.statisticsChaosThree();
				System.out.print("요일: ");
				dayOfWeek = reader.readLine();
				
				ViewAll.statisticsChaosFour();
				System.out.print("시간: ");
				time = reader.readLine();
				
				check = busyStatVaildation(lines,way,dayOfWeek,time);
				
				if(check) {
					
					break;
					
				}else {
					
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");

				}
				
			}
			
			
			this.route = lineRoute(lines);
			printBusy(BusyManagement.searchBusy(lines, way, dayOfWeek,this.route),lines,way,dayOfWeek,time);
			
			
		} catch (Exception e) {
			System.out.println("BusyStat.busyStat");
			e.printStackTrace();
		}
		
	}
	
	//time은 -5하면 됨.
	//혼잡도 인덱스 총 15개
	private void printBusy(ArrayList<Busy> list, String line, String way, String dayOfWeek, String time) {
		
		String result = String.format("호선: %s\n"
			     	                + "방향: %s\n"
				                    + "요일: %s\n",line,way,dayOfWeek);
		
		System.out.println(result);
		
		for(Busy b : list) {
			
			System.out.printf("%s : %.1f",b.getStation(),b.getCrowded().get(Integer.parseInt(time)-5));
			System.out.println();
			
		}
		
	}
	
	private boolean busyStatVaildation(String line,  String way, String dayOfWeek, String timeStr) {
		
		try {
			
			int time = Integer.parseInt(timeStr);
			
			if(time<5 && time > 25) {
				return false;
			}
			
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		
		
		if(line.contains("호선")){
			line = line.replace("호선", "");
		}
			
		//호선 입력 확인 (1~9호선)
		if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
				&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
				&& !line.equals("8") && !line.equals("9")) {
			
			return false;
			
		}
		
		if(!way.equals("상행") && !way.equals("하행") && !way.equals("내선") && !way.equals("외선")) {
			
			return false;
			
		}
		

		if(!dayOfWeek.equals("평일") && !dayOfWeek.equals("주말")) {
			
			return false;
			
		}
		
		
		if(dayOfWeek.equals("주말")) {
			dayOfWeek = "토요일";
		}
		
		
		return true;
		
		//addTrainVaildation
	}
	
	

}
