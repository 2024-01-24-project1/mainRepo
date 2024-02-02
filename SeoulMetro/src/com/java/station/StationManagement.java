package com.java.station;

import java.util.ArrayList;

import com.java.busy.Busy;
import com.java.busy.BusyManangement;
import com.java.busy.BusyStat;
import com.java.common.Data;
import com.java.member.user.FindWay;

/**
 * 역관리중 열차추가, 의자없는 열차 추가하는 메서드
 */
public class StationManagement {
	
	// 입력값 > 호선, 추가열차수, 시작역, 종료역, 시간대, 요일(평일/주말)
	
	public void addTrain(String line, String trainsNum, String startStation, String endStation, String time, String dayOfWeek) {
		
		FindWay findWay = new FindWay();
		boolean way = false; 										  //상행 true 하행 false;
		ArrayList<String> route = new ArrayList<>(); 				  //경로
		ArrayList<ArrayList<String>> timeTable = new ArrayList<>();   //시간표
		ArrayList<ArrayList<String>> trainCount = new ArrayList<>();  //열차 수
		ArrayList<Integer> specificHourTrainCount = new ArrayList<>();//특정 시간대 열차 수
		ArrayList<Busy> busyList = new ArrayList<>();			      // 변경 전 전체 혼잡도
		ArrayList<Double> specificHourBusy = new ArrayList<>(); 	  // 변경 전 혼잡도
		ArrayList<Double> modifyBusy = new ArrayList<>(); 		      // 변경 후 혼잡도
		ArrayList<String> convertBusy = new ArrayList<>();			  // 변경 전 혼잡도 한글로 변경한 후 저장
		ArrayList<String> convertModifyBusy = new ArrayList<>();	  // 변경 후 혼잡도 한글로 변경한 후 저장
		
		//필요한거
		
//		해당 시간표에서 time이 몇번 중첩인지 > 이게 열차수 여기다가 열차수 더하고
//		해당 노선에서 첫 역 시간표 가져오기
		
		way = findWay.findLineWay(line, startStation, endStation);
		
		//호선 전체경로 가져오기
		route = LineRoute(line); 
		
		
		for(String s : route) {
			
			timeTable.add(findWay.findStartStationTime(line, s, way, dayOfWeek)); //시간표 ArrayList를 넣기
			
		}
		
		//route 컬렉션과 timeTable 컬렉션 인덱스에는 해당역(String)과 해당역의 시간표(ArrayList<String>)가 들어있음
		
		//시간표에서 시간만 추출하기
		for(int i=0; i<timeTable.size(); i++) {
			
			trainCount.add(findWay.timeExtraction(timeTable.get(i)));
			
		}
		
		//해당 시간만 따로 추출하고 카운트 하기
		
		for(int i=0; i<trainCount.size(); i++) {
			
			int count = 0;
			
			ArrayList<String> train = trainCount.get(i);
			
			for(String s : train) {
				
				String[] temp = s.split(":");
				
				if(Integer.parseInt(temp[0]) == Integer.parseInt(time)) {
					
					count++;
				}
			}
			specificHourTrainCount.add(count);
		}
		
		//특정 시간대 혼잡도 수치 가져오기
		busyList = BusyStat.searchBusy(line, (way ? (line.equals("2") ? "내선" : "하선") : (line.equals("2") ? "외선" : "상선"))
 , dayOfWeek);
		
		
		//열차 추가해서 혼잡도 계산하는 메서드
		modifyBusy = calcBusy(specificHourTrainCount, busyList,specificHourBusy,time,trainsNum);
		
		
		//한글로 변경
		
		convertBusy = BusyManangement.convertBusy(specificHourBusy);
		convertModifyBusy = BusyManangement.convertBusy(modifyBusy);
		
		printBusy(startStation,endStation,specificHourBusy,modifyBusy,convertBusy,convertModifyBusy,busyList,way,route);
		
	}
	
	private void printBusy(String startStation, String endStation, ArrayList<Double> specificHourBusy,
			ArrayList<Double> modifyBusy, ArrayList<String> convertBusy, ArrayList<String> convertModifyBusy, ArrayList<Busy> busyList ,boolean way, ArrayList<String> route) {
		
		
		System.out.printf("시작역(%s)\n", startStation);
		
		if(way) { //true 상행선 or 외선
			
			for(int i=route.size()-1; i>=0; i--) {
				
				
				if(route.get(i).equals(endStation)) {
					
					System.out.printf("%s역\t\t(%.1f %s) -> (%.1f %s)\n",route.get(i),specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					System.out.printf("도착역(%s)\n", endStation);
					return;
					
					
				}
				
				
				if(route.get(i).equals(startStation)) {
					
					System.out.printf("%s역\t\t(%.1f %s) -> (%.1f %s)\n",route.get(i),specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					
				}
				
				
			}
			
			
			
		}else if(!way) { //false 하행선 or 내선
			
			for(int i=route.size()-1; i>=0; i--) {
				
				
				if(route.get(i).equals(endStation)) {
					
					System.out.printf("%s역\t\t(%.1f %s) -> (%.1f %s)\n",route.get(i),specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					System.out.printf("도착역(%s)\n", endStation);
					return;
					
					
				}
				
				
				if(route.get(i).equals(startStation)) {
					
					System.out.printf("%s역\t\t(%.1f %s) -> (%.1f %s)\n",route.get(i),specificHourBusy.get(i),convertBusy.get(i),modifyBusy.get(i),convertModifyBusy.get(i)); // 역이름, 변경전 혼잡도, 변경전 혼잡도 한글, 변경후 혼잡도, 변경후 혼잡도 한글
					
				}
				
			}
			
			
		}
		
		
		
	}

	private ArrayList<Double> calcBusy(ArrayList<Integer> specificHourTrainCount, ArrayList<Busy> busyList,ArrayList<Double> specificHourBusy ,String time, String trainsNum) {
		
		ArrayList<Double> modifyBusy = new ArrayList<>();
		
		
		//열차수 들어있고 busyList에는 혼잡도 리스트가 있음
		
//		열차대수 * 혼잡도 / 추가열차 대수 = 변경 혼잡도
		
		for(int i=0; i<specificHourTrainCount.size(); i++) {
			
			double avg = 0;
			
			avg = specificHourTrainCount.get(i) * busyList.get(i).getCrowded().get(Integer.parseInt(time)-5) / Integer.parseInt(trainsNum);
			specificHourBusy.add(busyList.get(i).getCrowded().get(Integer.parseInt(time)-5));
			modifyBusy.add(avg);
		}
		
		return modifyBusy;
		
	}

	/**
	 * 입력받은 호선의 전체 경로를 리턴해주는 메서드
	 * @param line
	 * @return 선택한 호선의 전체 경로 ArrayList
	 */
	private ArrayList<String> LineRoute(String line) {
		
		ArrayList<String> route = new ArrayList<>();
		
		if(line.equals("1")) {
			route = Data.LINE1_STATION_NAME;
		}else if(line.equals("2")) {
			route = Data.LINE2_STATION_NAME;
		}else if(line.equals("3")) {
			route = Data.LINE3_STATION_NAME;
		}else if(line.equals("4")) {
			route = Data.LINE4_STATION_NAME;
		}else if(line.equals("5")) {
			route = Data.LINE5_STATION_NAME;
		}else if(line.equals("6")) {
			route = Data.LINE6_STATION_NAME;
		}else if(line.equals("7")) {
			route = Data.LINE7_STATION_NAME;
		}else if(line.equals("8")) {
			route = Data.LINE8_STATION_NAME;
		}else if(line.equals("9")) {
			route = Data.LINE9_STATION_NAME;
		}
		
		return route;
	}
	
	public void addNoChairTrain() {
		
		
		
		
		
	}
}
