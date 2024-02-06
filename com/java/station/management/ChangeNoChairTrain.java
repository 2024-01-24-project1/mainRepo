package com.java.station.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.java.busy.BusyManagement;
import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.view.ViewAll;

public class ChangeNoChairTrain extends StationManagement{
	
	private StationManagement stationManagement;
	
	public ChangeNoChairTrain() {
		
		stationManagement = new StationManagement();
		
	}
	
	public void changeNoChairTrain() {
		
		
		
		
		try {
			
			boolean loop  = true;
			boolean check = false;
			String line = "";
			String startStation = "";
			String endStation = "";
			String time = "";
			String dayOfWeek = "";
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(loop) {
				
				ViewAll.changeNoChairTrain();
				System.out.print("호선: ");
				line = reader.readLine();
				
				ViewAll.trainAddThree();
				System.out.print("시작역: ");
				startStation = reader.readLine();
				
				ViewAll.trainAddFour();
				System.out.print("종료역: ");
				endStation = reader.readLine();
				
				ViewAll.trainAddFive();
				System.out.print("시간대: ");
				time = reader.readLine();
				
				ViewAll.trainAddSix();
				System.out.print("요일(평일/주말): ");
				dayOfWeek = reader.readLine();
				
				check = Validation.is_changeNoChiarTrain(line, startStation, endStation, time, dayOfWeek);
				
				if(check) {
					
					loop = false;
					
				}else {
					
					ViewAll.trainAddError();
					
				}
			}
			
			
			
			
			//방향 찾기
			stationManagement.way = findLineWay(line, startStation, endStation);
			
			//입력받은 호선 역 이름 가져오기
			stationManagement.route = lineRoute(line);
			
			//특정 시간대 혼잡도 수치 가져오기
			stationManagement.busyList = BusyManagement.searchBusy(line, (stationManagement.way ? (line.equals("2") ? "외선" : "상행") : (line.equals("2") ? "내선" : "하행")), dayOfWeek, stationManagement.route);			
			
			//의자없는열차 변경 후 혼잡도 계산하는 메서드
			stationManagement.modifyBusy = calcBusy(time);
			
			//혼잡도 수치 한글로 변경
			stationManagement.convertBusy = BusyManagement.convertBusy(stationManagement.specificHourBusy);
			stationManagement.convertModifyBusy = BusyManagement.convertBusy(stationManagement.modifyBusy);
			
			
			
			ViewAll.changeNoChairTrainResult();
			printBusy(startStation, endStation, time, stationManagement.specificHourBusy, stationManagement.modifyBusy, stationManagement.convertBusy, stationManagement.convertModifyBusy, stationManagement.way, stationManagement.route);
			
			//혼잡도 수치 조정
			BusyManagement.modifyBusyValue(line,stationManagement.way,time,dayOfWeek,stationManagement.modifyBusy, stationManagement.route);
			
			LogSave.logSave(LogSave.NOCHAIRTRAIN);
			System.out.println("계속하려면 엔터를 입력하세요.");
			reader.readLine();
			
		} catch (Exception e) {
			System.out.println("ChangeNoChairTrain.changeNoChairTrain");
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public ArrayList<Double> calcBusy(String time) {
		
		ArrayList<Double> modifyBusy = new ArrayList<>();
		
//		열차한칸인원(160) * 혼잡도 / 변경후 인원수(202명) = 변경 혼잡도
		//202명 : 혼잡도 = 160명 : 변경혼잡도
		for(int i=0; i<stationManagement.busyList.size(); i++) {
			
			double avg = 0;
			
			avg = (160 * (stationManagement.busyList.get(i).getCrowded().get(Integer.parseInt(time)-5))) / 202 ;
			stationManagement.specificHourBusy.add(stationManagement.busyList.get(i).getCrowded().get(Integer.parseInt(time)-5));
			modifyBusy.add(avg);
			
		}
		
		return modifyBusy;
		
	}
	
}
