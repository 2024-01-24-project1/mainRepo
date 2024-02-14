package com.java.station.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.java.busy.BusyManagement;
import com.java.common.Validation;
import com.java.common.log.LogSave;
import com.java.station.StationNamePage;
import com.java.view.ViewAll;

/**
 * 역 관리중 해당 호선에 열차를 추가하는 클래스
 */
public class AddTrain extends StationManagement {
	
	/**
	 * 부모 클래스인 StationManagement의 인스턴스
	 */
	private StationManagement stationManagement;
	
	/**
	 * 열차 추가 클래스의 생성자
	 */
	public AddTrain() {
		
		this.stationManagement = new StationManagement();
		
	}
	
	/**
	 * 입력값을 받아 열차를 추가하는 메서드
	 */
	public void addTrain() {
		
		// 입력값 > 호선, 추가열차수, 시작역,종료역, 시간대, 요일(평일/주말)
		
					//하는일 : 상행인지 하행인지 확인
					//         입력받은 호선 역 이름 가져오기
					//         각각 역의 시간표데이터 가져오기
					//         시간표데이터에서 시간만 추출하기
					//         시간표 시간을 참고해서 시간 데이터 가져오기 
					// 		   시간을 이용해서 열차 대수 새기
					//         특정 호선, 시간대의 혼잡도를 가져오기
		
		
		try {
			/**
			 * 에러 문구를 저장하는 ArrayList
			 */
			ArrayList<String> error = new ArrayList<>();
			/**
			 * while문을 제어하는 변수
			 */
			boolean loop  = true;
			/**
			 * 호선을 저장하는 변수
			 */
			String line = "";
			/**
			 * 열차 수를 저장하는 변수
			 */
			String trainNums = "";
			/**
			 * 시작역을 저장하는 변수
			 */
			String startStation = "";
			/**
			 * 도착역을 저장하는 변수
			 */
			String endStation = "";
			/**
			 * 시간대를 저장하는 변수
			 */
			String time = "";
			/**
			 * 요일을 저장하는 변수
			 */
			String dayOfWeek = "";
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			
			while(loop) {
				
				ViewAll.trainAddOne();
				System.out.print("\t\t\t호선           : ");
				line = reader.readLine();
				
				if(line.contains("호선")) {
					line = line.replace("호선", "");
				}

				StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);
				
				System.out.print("\t\t\t시작역         : ");
				startStation = reader.readLine();
				
				if(startStation.endsWith("역")) {
					startStation = startStation.substring(0,startStation.length()-1);
				}
				
				System.out.print("\t\t\t종료역         : ");
				endStation = reader.readLine();
				
				if(endStation.endsWith("역")) {
					endStation = endStation.substring(0,endStation.length()-1);
				}
				
				System.out.print("\t\t\t시간대(5~24)   : ");
				time = reader.readLine();
				
				if(time.endsWith("시")) {
					time.subSequence(0,time.length()-1);
				}
				
				System.out.print("\t\t\t요일(평일/주말): ");
				dayOfWeek = reader.readLine();
				
				if(dayOfWeek.equals("주말")) {
					dayOfWeek = "토요일";
				}
				
				System.out.print("\t\t\t추가 열차수    : ");        
				trainNums = reader.readLine();
				
				error = Validation.is_addTrain(line, trainNums, startStation, endStation, time, dayOfWeek);
				
				if(error.get(0).equals("오류없음")) {
					loop = false;
				}else {
					
					if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
						return;                      //false 일 경우 뒤로가기
					}
					
				}
				
			}
			
			stationManagement.way = findLineWay(line, startStation, endStation);
			
			//호선 전체경로 가져오기
			stationManagement.route = StationManagement.lineRoute(line); 
			
			
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
			printBusy(startStation, endStation,stationManagement.specificHourBusy, stationManagement.modifyBusy, stationManagement.convertBusy, stationManagement.convertModifyBusy, stationManagement.way, stationManagement.route);
			
			//혼잡도 수치 수정
			BusyManagement.modifyBusyValue(line,stationManagement.way,time,dayOfWeek,stationManagement.modifyBusy, stationManagement.route);
			
			//추가된 열차수 빼기
			StationManagement.spareTrain -= Integer.parseInt(trainNums);
			
			
			LogSave.logSave(LogSave.ADDTRAIN);
			ViewAll.pause();
			
			
		} catch (Exception e) {
			System.out.println("AddTrain.addTrain");
			e.printStackTrace();
		}



	}


	/**
	 * 해당 시간(time)의 혼잡도를 열차를 추가할 경우 바뀐 혼잡도를 반환하는 메서드
	 * @param time 시간대(05~24)
	 * @param trainsNum 추가할 열차 수
	 * @return 해당 시간의 변경된 혼잡도 ArrayList of Double modifyBusy
	 */
	public ArrayList<Double> calcBusy(String time, String trainsNum) {
		
		/**
		 * 수정된 혼잡도를 저장하는 ArrayList
		 */
		ArrayList<Double> modifyBusy = new ArrayList<>();


		//열차수 들어있고 busyList에는 혼잡도 리스트가 있음

		//열차대수 * 혼잡도 / 추가열차 대수 = 변경 혼잡도
		//추가대수 : 혼잡도 = 원래수 : 변경혼잡도

		for(int i=0; i<stationManagement.route.size(); i++) {

			//역 이름이 일치하면 혼잡도 가져오기

			//10 * 혼잡도(104) / 11
			/**
			 * 수정된 혼잡도 수치를 저장하는 변수
			 */
			double busyValues = 0;

			busyValues = (stationManagement.specificHourTrainCount.get(i) * (stationManagement.busyList.get(i).getCrowded().get(Integer.parseInt(time)-5))) / (Integer.parseInt(trainsNum)+stationManagement.specificHourTrainCount.get(i));
			stationManagement.specificHourBusy.add((stationManagement.busyList.get(i).getCrowded().get((Integer.parseInt(time))-5)));
			modifyBusy.add(busyValues);



		}

		return modifyBusy;

	}



}
