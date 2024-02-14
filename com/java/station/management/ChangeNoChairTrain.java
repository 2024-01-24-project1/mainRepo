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
 * 역 관리중 해당 호선에 있는 열차를 의자 없는 열차로 변경하는 클래스
 */
public class ChangeNoChairTrain extends StationManagement{
	
	/**
	 * 부모 클래스인 StationManagement의 인스턴스
	 */
	private StationManagement stationManagement;
	
	/**
	 * ChangeNoChairTrain클래스 생성자
	 */
	public ChangeNoChairTrain() {
		
		stationManagement = new StationManagement();
		
	}
	
	
	/**
	 * 입력 값을 받아 의자없는 열차로 변경하는 메서드
	 */
	public void changeNoChairTrain() {
		
		
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
			 *  호선을 저장하는 변수
			 */
			String line = "";
			/**
			 * 출발역을 저장하는 변수
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
				
				ViewAll.changeNoChairTrain();
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
				
				error = Validation.is_changeNoChiarTrain(line, startStation, endStation, time, dayOfWeek);
				
				if(error.get(0).equals("오류없음")) {
					
					loop = false;
					
				}else {
					
					if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
						return;                      //false 일 경우 뒤로가기
					}
					
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
			
			
			
			printBusy(startStation, endStation, stationManagement.specificHourBusy, stationManagement.modifyBusy, stationManagement.convertBusy, stationManagement.convertModifyBusy, stationManagement.way, stationManagement.route);
			
			//혼잡도 수치 조정
			BusyManagement.modifyBusyValue(line,stationManagement.way,time,dayOfWeek,stationManagement.modifyBusy, stationManagement.route);
			
			LogSave.logSave(LogSave.NOCHAIRTRAIN);
			ViewAll.pause();
			
		} catch (Exception e) {
			System.out.println("ChangeNoChairTrain.changeNoChairTrain");
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 해당 시간(time)의 혼잡도를 의자 없는 열차로 변경할 경우 바뀐 혼잡도를 반환하는 메서드
	 * @param time 시간대(05~24)
	 * @return 해당 시간의 변경된 혼잡도 ArrayList of Double
	 */
	public ArrayList<Double> calcBusy(String time) {
		
		/**
		 * 수정된 혼잡도를 저장하는 ArrayList
		 */
		ArrayList<Double> modifyBusy = new ArrayList<>();
		
//		열차한칸인원(160) * 혼잡도 / 변경후 인원수(202명) = 변경 혼잡도
		//202명 : 혼잡도 = 160명 : 변경혼잡도
		for(int i=0; i<stationManagement.busyList.size(); i++) {
			
			/**
			 * 수정된 혼잡도 수치를 저장하는 변수
			 */
			double busyValues = 0;
			
			busyValues = (160 * (stationManagement.busyList.get(i).getCrowded().get(Integer.parseInt(time)-5))) / 202 ;
			stationManagement.specificHourBusy.add(stationManagement.busyList.get(i).getCrowded().get(Integer.parseInt(time)-5));
			modifyBusy.add(busyValues);
			
		}
		
		return modifyBusy;
		
	}
	
}
