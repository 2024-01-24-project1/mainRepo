package com.java.busy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.java.common.Validation;
import com.java.common.log.LogSave;
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
			String line = "";
			String way = "";
			String dayOfWeek = "";
			String time = "";
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				
				ViewAll.statisticsChaosOne();
				System.out.print("호선: ");
				line = reader.readLine(); 
				
				if(line.contains("호선")) {
					line = line.replace("호선", "" );
				}
				
				if(line.equals("2")) {
					ViewAll.statisticsChaosTwoLine2();
					System.out.print("방향: ");
					
				}else {
					ViewAll.statisticsChaosTwo();
					System.out.print("방향: ");
				}
				way = reader.readLine();
				
				
				ViewAll.statisticsChaosThree();
				System.out.print("요일: ");
				dayOfWeek = reader.readLine();
				
				if(dayOfWeek.equals("주말")) {
					dayOfWeek = "토요일";
				}
				
				ViewAll.statisticsChaosFour();
				System.out.print("시간: ");
				time = reader.readLine();
				
				check = Validation.is_busyStat(line,way,dayOfWeek,time);
				
				if(check) {
					
					break;
					
				}else {
					
					ViewAll.trainAddError();

				}
				
			}
			
			
			this.route = lineRoute(line);
			printBusy(BusyManagement.searchBusy(line, way, dayOfWeek,this.route),line,way,dayOfWeek,time);
			
			ViewAll.pause();
			
			
		} catch (Exception e) {
			System.out.println("BusyStat.busyStat");
			e.printStackTrace();
		}
		
	}
	
	//time은 -5하면 됨.
	//혼잡도 인덱스 총 15개
	private void printBusy(ArrayList<Busy> list, String line, String way, String dayOfWeek, String time) {
		
		String result = String.format("호선: %s, 방향: %s, 요일: %s\n",line,way,dayOfWeek);
		
		System.out.println(result);
		
		for(Busy b : list) {
			
			System.out.printf("%-35s  \t: %-3.1f",b.getStation()+"역",b.getCrowded().get(Integer.parseInt(time)-5));
			System.out.println();
			
		}
		
		LogSave.logSave(LogSave.BUSYSTAT);
		
	}
	
	
	

}
