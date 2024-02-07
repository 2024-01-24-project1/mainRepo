package com.java.station.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

import com.java.busy.Busy;
import com.java.busy.BusyManagement;
import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.member.user.AnotherDateFindWay;
import com.java.member.user.BookMark;
import com.java.member.user.CurrentTimeFindWay;
import com.java.requiredtime.RequiredTime;
import com.java.view.ViewAll;
import com.java.view.ViewAll;

public class FindWay extends StationManagement {
	

	private StationManagement stationManagement;
	 
	
	public FindWay() {
		
		this.stationManagement = new StationManagement();
		
	}
	
	
	public void findWaySelMenu() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				ViewAll.roadSearchRouteBottom();
				ViewAll.chooseNum();
				
				String sel = reader.readLine();

				if(sel.equals("1")) { //현재시간

					CurrentTimeFindWay currentTimeFindWay = new CurrentTimeFindWay();
					currentTimeFindWay.currentTimeFindWay();

				}else if(sel.equals("2")) { //다른날짜
					
					AnotherDateFindWay anotherDateFindWay = new AnotherDateFindWay();
					anotherDateFindWay.anotherDateFindWay();

				}else if(sel.equals("")) {//뒤로가기
					break;
					
				}else {
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
					
				}
			}

			
		} catch (Exception e) {
			System.out.println("FindWay.findWay()");
			e.printStackTrace();
		}
		
		
		
		
		
		
	}




	protected void findWay(String line, String startStation, String endStation, Calendar calendar) {
		
		ArrayList<RequiredTime> requiredTime = new ArrayList<>();   //보고싶은 경로의 소요시간
		int minute = 0;
		LocalTime localTime = LocalTime.of(0, 0, 0);  				//시간 누적 localTime
		String departureTime = "";
		String dayOfWeek ="";                 						//요일(평일/주말)
		
		
		startStation = startStation.endsWith("역") ? startStation.substring(0,startStation.length()-1) : startStation;
		endStation = endStation.endsWith("역") ? endStation.substring(0,endStation.length()-1) : endStation;
		
	
		
		//상행 하행 구분하기
		stationManagement.way = stationManagement.findLineWay(line,startStation,endStation);
		
		
		//요일 구분하기
		dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK)>1 && calendar.get(Calendar.DAY_OF_WEEK)<7) ? "평일" : "토요일";
		
		
		//해당 호선의 소요시간 리스트 가져오기
		requiredTime = (ArrayList<RequiredTime>) Data.requiredTimeList.stream()
																	  .filter(s-> s.getLine().equals(line))
																	  .collect(Collectors.toList());
		
		//현재 시간과 가장 가까운 열차 시간
		departureTime = departureTrainTime(line, startStation, dayOfWeek ,calendar);
 
		//true면 상행선
		if(stationManagement.way) {

			minute = upwardLine(startStation, endStation, requiredTime, localTime);


		}else { // false 하행선
			
			minute = downwardLine(startStation, endStation, requiredTime,localTime);

		}
		
		//호선, 방향, 요일 (2호선제외)
		if(!line.equals("2")) {
			
			stationManagement.busyList = BusyManagement.searchBusy(line, stationManagement.way ? "상행":"하행", dayOfWeek, stationManagement.route);

		}else {

			stationManagement.busyList = BusyManagement.searchBusy(line, stationManagement.way ? "외선":"내선", dayOfWeek, stationManagement.route);

		}


		for(Busy b : stationManagement.busyList) {

			if(stationManagement.route.contains(b.getStation())) {
				
				stationManagement.specificHourBusy.add(b.getCrowded().get((calendar.get(Calendar.HOUR_OF_DAY)-5)));

			}


		}

		stationManagement.convertBusy = BusyManagement.convertBusy(stationManagement.specificHourBusy); //혼잡도수치 한글로 변경후 저장



		printWay(minute, departureTime);
		
		
		
		
	}
	
	//열차 출발 시간 구하는 메서드
	private String departureTrainTime(String line, String startStation,String dayOfWeek ,Calendar calendar) {
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		
		ArrayList<String> StartStationTime = stationManagement.findStationTime(line, startStation, stationManagement.way, dayOfWeek);
		
		ArrayList<String> onlyTime = timeExtraction(StartStationTime);
		
		for(String compareTime : onlyTime) {
			
			String[] temp = compareTime.split(":");
			
			
			if(hour<=Integer.parseInt(temp[0])) {
				
				if(minute<=Integer.parseInt(temp[1])) {
					
					hour = Integer.parseInt(temp[0]);
					minute = Integer.parseInt(temp[1]);
					
					String result = hour +":"+minute;
					return result;
				}
				
			}
			
		}
		
		
		
		return "";
		
	}//departureTrainTime
	
	/**
	 * 검색한 길찾기 경로를 즐겨찾기 등록하는 메서드
	 * @param lines 호선
	 * @param start 시작역
	 * @param end   도착역
	 * @param calendar  설정한 년도,월,일,시간,분 현재시간의 경우 현재시간을 등록
	 */
	protected void registerBookMark(String lines, String start, String end, Calendar calendar) {
		
		boolean check = false;
		String route = lines+"-"+start+"-"+end+"-"+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.HOUR_OF_DAY)+"-"+calendar.get(Calendar.MINUTE);
		
		//배열확인하여 객체 있는지 확인
		for(BookMark b : Data.bookMarkList) {
			
			if(b.getId().equals(LoginLogout.auth)) {
				b.setBookMarkList(route);
				check = true;
			}
			
		}
		//즐겨찾기한적 없다면 객체생성후 Data.bookMarkList에 저장
		if(!check) {
			
			BookMark b = new BookMark(LoginLogout.auth);
			b.setBookMarkList(route);
			Data.bookMarkList.add(b);
			
		}
		
		
	}
	
		
	

	/**
	 * 하행선일 경우 총 소요시간을 구하면서 가는 경로는 저장하는 메서드
	 * @param startStation 시작역
	 * @param endStation   도착역
	 * @param route        경로
	 * @param requiredTime 소요시간 들어있는 ArrayList
	 * @param localTime    소요시간 누적할 LocalTime 클래스 변수
	 * @return 총 소요 시간(분) int mintue;
	 */
	private int downwardLine(String startStation, String endStation,
			ArrayList<RequiredTime> requiredTime, LocalTime localTime) {
		
		boolean loop = false;
		int minute;
		
		for(int i=0; i<requiredTime.size(); i++) {
			
			while(loop) {

				String temp[] =requiredTime.get(i).getTime().split(":");
				localTime = localTime.plusMinutes(Integer.parseInt(temp[0]));
				localTime = localTime.plusSeconds(Integer.parseInt(temp[1])+30); //30초 정차시간 
				stationManagement.route.add(requiredTime.get(i).getStation());
				
				if(requiredTime.get(i).getStation().equals(endStation)) {
					loop = false;
					localTime = localTime.minusSeconds(30); //마지막역은 정차시간 포함 x
				}else {
					i++;
				}
				

			}//while
			
			if(i<requiredTime.size() && requiredTime.get(i).getStation().equals(startStation)) { //시작역 이후 시간
				loop = true;
				stationManagement.route.add(requiredTime.get(i).getStation()); //시작역 이름 저장
			}
			
		}//for
		
		minute = localTime.getMinute();
		
		if(localTime.getSecond()>0) { //x초 올림
			minute += 1;
		}
		return minute;
		
	}//downwardLine
	
	/**
	 * 상행선일 경우 총 소요시간을 구하면서 가는 경로는 저장하는 메서드
	 * @param startStation 시작역
	 * @param endStation   도착역
	 * @param route        경로
	 * @param requiredTime 소요시간 들어있는 ArrayList
	 * @param localTime    소요시간 누적할 LocalTime 클래스 변수
	 * @return 총 소요 시간(분) int mintue;
	 */
	private int upwardLine(String startStation, String endStation,
			ArrayList<RequiredTime> requiredTime, LocalTime localTime) {
		
		boolean loop = false;
		int minute;
		
		for(int i=requiredTime.size()-1; i>=0; i--) {

			while(loop) {

				String temp[] =requiredTime.get(i).getTime().split(":");
				localTime = localTime.plusMinutes(Integer.parseInt(temp[0]));
				localTime = localTime.plusSeconds(Integer.parseInt(temp[1])+30); //30초 정차시간
				stationManagement.route.add(requiredTime.get(i).getStation());
				
				if(requiredTime.get(i).getStation().equals(endStation)) {
				
					loop = false;
					localTime = localTime.minusSeconds(30); //마지막역은 정차시간 포함 x
					
				}else {
					
					i--;
					
				}
				


			}//while
			
			if(i>0 && requiredTime.get(i).getStation().equals(startStation)) { //시작역 이후 시간
				loop = true;
				stationManagement.route.add(requiredTime.get(i).getStation()); //시작역 이름 저장
			}


		}//for
		
		minute = localTime.getMinute();
		
		if(localTime.getSecond()>0) { //x초 올림
			minute += 1;
		}
		
		return minute;
		
	}//upwardLine
	
	//현재 시간 길찾기 출력 > 출력메서드 
	private void printWay(int minute, String departureTime) {
			
		ViewAll.findWay(stationManagement.route.get(0), stationManagement.route.get(stationManagement.route.size()-1));
		System.out.print("\t");
		for(int i=0; i<stationManagement.route.size(); i++) {
			
			
			System.out.print(" -> "+stationManagement.route.get(i)+"["+stationManagement.convertBusy.get(i)+"]");
			
			if((i+1)%3==0) {
				System.out.println();
				System.out.print("\t");
			}
			
			
		}
		System.out.println();
		
		
		System.out.printf("\t\t\t총 소요시간: %d분\n",minute);
		
		String[] temp = departureTime.split(":");
		
		int departureHour = Integer.parseInt(temp[0]);
		int departureMinute = Integer.parseInt(temp[1]);
		
		System.out.printf("\t\t\t%s역 열차 출발 시간: %02d:%02d\n",stationManagement.route.get(0),departureHour,departureMinute);
		
		if((departureMinute+minute)>=60) {
			minute = (departureMinute+minute) % 60;
			departureHour+=1;
			
		}else {
			
			minute = departureMinute+minute;
			
		}
		
		if(departureHour>=24) {
			departureHour = departureHour % 24;
		}
		System.out.printf("\t\t\t도착 예정 시간: %02d시 %02d분\n", departureHour,minute);
		
		
		
		
	}//printWay
	
	


}

