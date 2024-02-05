package com.java.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.java.common.log.Log;
import com.java.common.lostarticle.LostArticle;
import com.java.member.employee.Employee;
import com.java.member.user.User;
import com.java.member.user.UserVoice;
import com.java.schedule.Schedule;
import com.java.station.PassengerCounting;
import com.java.station.timetable.StationTime;

public final class Load {
	Data data = new Data();
	
	// 모든 csv파일 읽어서 
	// Data클래스의 static배열에 로드 
	public void loadAll() {
		loadUserList();
		loadEmployeeList();
		loadPassList();
		loadScheduleList();
		loadLogList();
		loadUserVoiceList();
		loadLostArticleList();
		loadStationName();
		loadLine_StationTimeTable();
		loadPassengerCountingList();
	}
	
	private void loadPassengerCountingList() {
			String line = "";
			
			String path = Data.passengerCountPath;
			
			try {
				for(int i = 1; i <= 12; i++) {
					
					BufferedReader reader = new BufferedReader(new FileReader(path + i + "월.csv"));
					
					while((line = reader.readLine()) != null) {
						line = line.replaceAll("\"", "");
						String[] lineArr = line.split(",");
						
						PassengerCounting count = new PassengerCounting(lineArr[0], lineArr[1], lineArr[2], Long.parseLong(lineArr[3]));
						Data.passengerCountingList.add(count);
						
					}
					reader.close();
				}	
				return;
				
			}catch(Exception e){
				System.out.println("분실물 리스트 로딩 실패");
				e.printStackTrace();
			}
	}//End of loadPassengerCountingList()
	
	private void loadLine_StationTimeTable() {
		String line = "";
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		list.add(new ArrayList<String>());
		list.add(Data.LINE1_STATION_NAME);
		list.add(Data.LINE2_STATION_NAME);
		list.add(Data.LINE3_STATION_NAME);
		list.add(Data.LINE4_STATION_NAME);
		list.add(Data.LINE5_STATION_NAME);
		list.add(Data.LINE6_STATION_NAME);
		list.add(Data.LINE7_STATION_NAME);
		list.add(Data.LINE8_STATION_NAME);
		list.add(Data.LINE9_STATION_NAME);
		
		try {
			
				for(int i = 1; i <= 9; i++) {
			
					// 1호선의 역의 이름을 반복
					for(String name : list.get(i)) {
						String path = Data.station_TimeTablePath + i + "호선_시간표\\" + name + ".csv";
						BufferedReader reader = new BufferedReader(new FileReader(path));
						
						ArrayList<String> upNomal = new ArrayList<>();
						ArrayList<String> downNomal = new ArrayList<>();
						ArrayList<String> upHoliday = new ArrayList<>();
						ArrayList<String> downHoliday = new ArrayList<>();
						
						while((line = reader.readLine()) != null) {
							String[] lineArr = line.split(",");
							upNomal.add(lineArr[0]);
							downNomal.add(lineArr[1]);
							upHoliday.add(lineArr[2]);
							downHoliday.add(lineArr[3]);
					
					}
						StationTime time = new StationTime(i + "호선", name, upNomal, downNomal, upHoliday, downHoliday);
						Data.stationTimeList.add(time);
					
					
						reader.close();
					
					}
				}
				return;
			
		}catch(Exception e){
			System.out.println("역별시간표 로딩 실패");
			e.printStackTrace();
		}
	}//loadStationTimeTable()
	
	private void loadStationName() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.ALL_LINE_NAME_PATH));
			while((line = reader.readLine()) != null) {
				Data.ALL_STATION_NAME.add(line);
			}
			reader.close();
			
			for(int i = 1; i <= 9; i++) {
				String path = data.line_NamePath;
				path += i + "호선역이름.csv";
				BufferedReader stReader = new BufferedReader(new FileReader(path));
				
				while ((line = stReader.readLine()) != null) {
					
					if(i == 1) {
						Data.LINE1_STATION_NAME.add(line);
					}else if (i == 2) {
						Data.LINE2_STATION_NAME.add(line);
						
					}else if (i == 3) {
						Data.LINE3_STATION_NAME.add(line);
						
					}else if (i == 4) {
						Data.LINE4_STATION_NAME.add(line);
						
					}else if (i == 5) {
						Data.LINE5_STATION_NAME.add(line);
						
					}else if (i == 6) {
						Data.LINE6_STATION_NAME.add(line);
						
					}else if (i == 7) {
						Data.LINE7_STATION_NAME.add(line);
						
					}else if (i == 8) {
						Data.LINE8_STATION_NAME.add(line);
						
					}else if (i == 9) {
						Data.LINE9_STATION_NAME.add(line);
						
					}
					
				}
				
				
			}
			
			return;
			
		}catch(Exception e){
			System.out.println("역 이름 로딩 실패");
			e.printStackTrace();
		}
		
	}//End of loadStationName()
	
	private void loadLostArticleList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.LOSTARTICLEPATH));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				
				LostArticle article = new LostArticle(lineArr[0], lineArr[1], lineArr[2], lineArr[3]);
				
				Data.lostArticleList.add(article);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("분실물 리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadLostArticleList()
	
	private void loadUserVoiceList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.USERVOICEPATH));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				
				UserVoice voice = new UserVoice(lineArr[0], lineArr[1], lineArr[2], lineArr[3]);
				
				Data.userVoiceList.add(voice);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("민원 리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadUserVoiceList()
	
	private void loadLogList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.LOGPATH));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				ArrayList<String> actArr = new ArrayList<>();
				
				for(int i = 2; i < lineArr.length; i++) {
					actArr.add(lineArr[i]);
				}
				
				Log log = new Log(lineArr[0], lineArr[1], actArr);
				
				Data.logList.add(log);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("로그리스트 로딩 실패");
			e.printStackTrace();
		}
		
	}//End of loadLogList()
	
	private void loadUserList() {
		String line = "";
		Calendar now = Calendar.getInstance();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.USERPATH));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				
				if(lineArr[5].equals("있음")) {
					// 유저의 정기권 기간 확인
					SimpleDateFormat expiry = new SimpleDateFormat("yyyyMMdd");
					Date temp = expiry.parse(lineArr[6].substring(10));		// 정기권 종료일 확인
					
					Calendar end = Calendar.getInstance();
					end.setTime(temp);										// 정기권 종료일 캘린더
				
					if(end.compareTo(now) == -1) {
						// 정기권 기간이 지났으면 없음으로 바꿈
						lineArr[5] = "없음";
						lineArr[6] = "없음";
					}
				
				}
					
					User user = new User(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5], lineArr[6]);
					Data.userList.add(user);
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("유저리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadUserList()
	
	private void loadEmployeeList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.EMPLOYEEPATH));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				Employee employee = new Employee(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5]
												, lineArr[6], lineArr[7], lineArr[8]);
				Data.employeeList.add(employee);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("직원리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadEmployeeList()
	
	private void loadPassList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.PASSPATH));
			
			while((line = reader.readLine()) != null) {
				Data.passList.add(line);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("정기권 코드 로드 실패");
			e.printStackTrace();
		}
	}//End of loadPass()
	
	private void loadScheduleList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.SCHEDULEPATH));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				Schedule schedule = new Schedule(lineArr[0], lineArr[1], lineArr[2]);
				
				Data.scheduleList.add(schedule);
				
			}
			
			reader.close();
			
			//일정리스트 정렬
			Collections.sort(Data.scheduleList, Schedule.timeComparator);
			
			return;
			
		}catch(Exception e){
			System.out.println("일정 로드 실패");
			e.printStackTrace();
		}
	}//End of loadScheduleList()
	
}//End of Load
