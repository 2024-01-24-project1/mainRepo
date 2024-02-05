package com.java.station.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.java.busy.BusyManagement;
import com.java.view.ViewAll;


public class AddTrain extends StationManagement {
	
	private StationManagement stationManagement;
	
	public AddTrain() {
		
		this.stationManagement = new StationManagement();
		
	}
	
	//해당호선의 혼잡도를 가져오는 메서드 구현해야함
	
	public void addTrain() {
		
		
		try {
			
			boolean loop  = true;
			boolean check = false;
			String line = "";
			String trainNums = "";
			String startStation = "";
			String endStation = "";
			String time = "";
			String dayOfWeek = "";
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			
			while(loop) {
				
				ViewAll.trainAddOne();
				System.out.print("호선: ");
				line = reader.readLine();
				
				ViewAll.trainAddTwo();
				System.out.print("추가 열차수: ");        
				trainNums = reader.readLine();
				
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
				
				
				check = addTrainVaildation(line, trainNums, startStation, endStation, time, dayOfWeek);
				
				if(check) {
					loop = false;
				}else {
					
					ViewAll.trainAddError();
					
				}
				
			}
			
			
			
			
			// 입력값 > 호선, 추가열차수, 시작역,종료역, 시간대, 요일(평일/주말)
			
			//하는일 : 상행인지 하행인지 확인
			//         입력받은 호선 역 이름 가져오기
			//         각각 역의 시간표데이터 가져오기
			//         시간표데이터에서 시간만 추출하기
			//         시간표 시간을 참고해서 시간 데이터 가져오기 
			// 		   시간을 이용해서 열차 대수 새기
			//         특정 호선, 시간대의 혼잡도를 가져오기
			
			
			stationManagement.way = findLineWay(line, startStation, endStation);
			
			//호선 전체경로 가져오기
			stationManagement.route = stationManagement.lineRoute(line); 
			
			
			for(String s : stationManagement.route) {
				
				stationManagement.timeTable.add(findStationTime(line, s, stationManagement.way, dayOfWeek)); //시간표 ArrayList를 넣기
				
			}
			
			
			//시간표에서 시간만 추출하기
			for(int i=0; i<stationManagement.timeTable.size(); i++) {
				
				stationManagement.trainCount.add(timeExtraction(stationManagement.timeTable.get(i)));
				
			}
			
			//해당 시간만 따로 추출하고 카운트 하기
			stationManagement.specificHourTrainCount = specificHourTrainCount(time,stationManagement.trainCount);
			
			
			//특정 시간대 혼잡도 수치 가져오기
			stationManagement.busyList = BusyManagement.searchBusy(line, (stationManagement.way ? (line.equals("2") ? "외선" : "상행") : (line.equals("2") ? "내선" : "하행")), dayOfWeek, stationManagement.route);
			
			
			
			
			
			//열차 추가해서 혼잡도 계산하는 메서드    
			stationManagement.modifyBusy = calcBusy(time,trainNums);
			
			//혼잡도 수치 한글로 변경
			stationManagement.convertBusy = BusyManagement.convertBusy(stationManagement.specificHourBusy);
			stationManagement.convertModifyBusy = BusyManagement.convertBusy(stationManagement.modifyBusy);
			
			//출력
			ViewAll.trainAddResult();
			printBusy(startStation, endStation, time,stationManagement.specificHourBusy, stationManagement.modifyBusy, stationManagement.convertBusy, stationManagement.convertModifyBusy, stationManagement.way, stationManagement.route);
			
			
			
		} catch (Exception e) {
			System.out.println("AddTrain.addTrain");
			e.printStackTrace();
		}



	}

	private boolean addTrainVaildation(String line, String trainNums, String startStation, String endStation, String time,
			String dayOfWeek) {
		
		if(line.contains("호선")){
			line = line.replace("호선", "");
		}
			
		//호선 입력 확인 (1~9호선)
		if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
				&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
				&& !line.equals("8") && !line.equals("9")) {
			return false;
		}
		
		
		
		//추가 열차수가 예비열차수를 넘기거나 이미 예비열차수가 0인경우
		
		try {
			if(StationManagement.spareTrain==0 && (Integer.parseInt(trainNums)-StationManagement.spareTrain)<=0) {
				return false;
			}
			
		} catch (Exception e) {
			
			return false;
			
		}

		if(!lineRoute(line).contains(startStation) && !lineRoute(line).contains(endStation)) {
			
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

	/**
	 * 해당 시간(time)의 혼잡도를 열차를 추가할 경우 바뀐 혼잡도를 반환하는 메서드
	 * @param time 원하는 시간대
	 * @param trainsNum 추가할 열차 수
	 * @return 해당 시간의 변경된 혼잡도 ArrayList<Double> modifyBusy
	 */
	public ArrayList<Double> calcBusy(String time, String trainsNum) {

		ArrayList<Double> modifyBusy = new ArrayList<>();


		//열차수 들어있고 busyList에는 혼잡도 리스트가 있음

		//열차대수 * 혼잡도 / 추가열차 대수 = 변경 혼잡도
		//추가대수 : 혼잡도 = 원래수 : 변경혼잡도

		for(int i=0; i<stationManagement.route.size(); i++) {

			//역 이름이 일치하면 혼잡도 가져오기

			//10 * 혼잡도(104) / 11
			double avg = 0;

			avg = (stationManagement.specificHourTrainCount.get(i) * (stationManagement.busyList.get(i).getCrowded().get(Integer.parseInt(time)-5))) / (Integer.parseInt(trainsNum)+stationManagement.specificHourTrainCount.get(i));
			stationManagement.specificHourBusy.add((stationManagement.busyList.get(i).getCrowded().get((Integer.parseInt(time))-5)));
			modifyBusy.add(avg);



		}

		return modifyBusy;

	}



}
