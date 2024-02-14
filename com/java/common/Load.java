package com.java.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.java.busy.Busy;
import com.java.common.log.Log;
import com.java.common.lostarticle.LostArticle;
import com.java.member.employee.Employee;
import com.java.member.user.BookMark;
import com.java.member.user.User;
import com.java.member.user.UserVoice;
import com.java.requiredtime.RequiredTime;
import com.java.schedule.Schedule;
import com.java.station.PassengerCounting;
import com.java.station.timetable.StationTime;

/**
 * 파일을 읽어 오는 클래스
 */
public final class Load {
	/**
	 * Load 클래스의 기본 생성자
	 */
	public Load() {
	}
	/**
	 * 파일 경로가 저장된 Data 인스턴스
	 */
	Data data = new Data();
	
	// 모든 csv파일 읽어서 
	// Data클래스의 static배열에 로드 
	/**
	 * 모든 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
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
		loadBookMark();
		loadBusy();
		loadRequiredTime();
	}
	
	/**
	 * 승객수 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadPassengerCountingList() {
			/**
			 * 파일 내용 한줄을 저장하는 변수
			 */
			String line = "";
			
			/**
			 * 파일 경로를 저장하는 변수
			 */
			String path = Data.passengerCountPath;
			
			try {
				for(int i = 1; i <= 12; i++) {
					
					BufferedReader reader = new BufferedReader(new FileReader(path + i + "월.csv"));
					
					while((line = reader.readLine()) != null) {
						line = line.replaceAll("\"", "");
						/**
						 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
						 */
						String[] lineArr = line.split(",");
						
						if(lineArr[1].contains("공항철도")) {
							
						}else {
							
							/**
							 * 승객 수가 들어있는 PassengerCounting 인스턴스
							 */
							PassengerCounting count = new PassengerCounting(lineArr[0], lineArr[1], lineArr[2], Long.parseLong(lineArr[3]));
							Data.passengerCountingList.add(count);
							
						}
						
						
					}
					reader.close();
				}	
				return;
				
			}catch(Exception e){
				System.out.println("분실물 리스트 로딩 실패");
				e.printStackTrace();
			}
	}//End of loadPassengerCountingList()
	
	/**
	 * 각 호선별 역 시간표 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadLine_StationTimeTable() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		/**
		 * 전체 역의 시간표를 저장하는 ArrayList
		 */
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
						/**
						 * 파일 경로를 저장하는 변수
						 */
						String path = Data.station_TimeTablePath + i + "호선_시간표\\" + name + ".csv";
						BufferedReader reader = new BufferedReader(new FileReader(path));
						
						/**
						 * 평일 상행선 시간표를 저장하는 ArrayList
						 */
						ArrayList<String> upNomal = new ArrayList<>();
						/**
						 * 평일 하행성 시간표를 저장하는 ArrayList
						 */
						ArrayList<String> downNomal = new ArrayList<>();
						/**
						 * 주말 상행선 시간표를 저장하는 ArrayList
						 */
						ArrayList<String> upHoliday = new ArrayList<>();
						/**
						 * 주말 하행선 시간표를 저장하는 ArrayList
						 */
						ArrayList<String> downHoliday = new ArrayList<>();
						
						while((line = reader.readLine()) != null) {
							/**
							 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
							 */
							String[] lineArr = line.split(",");
							upNomal.add(lineArr[0]);
							downNomal.add(lineArr[1]);
							upHoliday.add(lineArr[2]);
							downHoliday.add(lineArr[3]);
					
					}
						/**
						 * 역의 시간표가 들어있는 StationTime 인스턴스
						 */
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
	
	/**
	 * 각 호선별 역 이름 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadStationName() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.ALL_LINE_NAME_PATH));
			while((line = reader.readLine()) != null) {
				Data.ALL_STATION_NAME.add(line);
			}
			reader.close();
			
			for(int i = 1; i <= 9; i++) {
				/**
				 * 파일 경로를 저장하는 변수
				 */
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
	
	/**
	 * 분실물 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadLostArticleList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.LOSTARTICLEPATH));
			
			while((line = reader.readLine()) != null) {
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");
				
				/**
				 * 분실물 정보가 들어있는 LostArticle 인스턴스
				 */
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
	
	/**
	 * 민원 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadUserVoiceList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.USERVOICEPATH));
			
			while((line = reader.readLine()) != null) {
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");
				
				/**
				 * 민원 정보가 들어있는 UserVoice 인스턴스
				 */
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
	
	/**
	 * 행동 로그 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadLogList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.LOGPATH));
			
			while((line = reader.readLine()) != null) {
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");
				/**
				 * 행동 로그를 저장하는 ArrayList
				 */
				ArrayList<String> actArr = new ArrayList<>();
				
				for(int i = 2; i < lineArr.length; i++) {
					actArr.add(lineArr[i]);
				}
				/**
				 * 행동 로그 정보가 저장된 Log 인스턴스
				 */
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
	
	/**
	 * 고객 정보파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadUserList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		/**
		 * 현재 시간을 저장한 Calendar 변수
		 */
		Calendar now = Calendar.getInstance();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.USERPATH));
			
			while((line = reader.readLine()) != null) {
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");
				
				if(lineArr[5].equals("있음")) {
					// 유저의 정기권 기간 확인
					/**
					 * 만료 날짜 형식을 저장한 변수
					 */
					SimpleDateFormat expiry = new SimpleDateFormat("yyyyMMdd");
					/**
					 * 정기권 종료일을 저장하는 변수
					 */
					Date temp = expiry.parse(lineArr[6].substring(10));		// 정기권 종료일 확인
					
					/**
					 * 정기권 날짜를 비교할 Calendar 변수
					 */
					Calendar end = Calendar.getInstance();
					end.setTime(temp);										// 정기권 종료일 캘린더
				
					if(end.compareTo(now) == -1) {
						// 정기권 기간이 지났으면 없음으로 바꿈
						lineArr[5] = "없음";
						lineArr[6] = "없음";
					}
				
				}
					
					/**
					 * 고객의 정보가 들어있는 User 인스턴스
					 */
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
	
	/**
	 * 직원 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadEmployeeList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.EMPLOYEEPATH));
			
			while((line = reader.readLine()) != null) {
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");
				/**
				 * 직원의 정보가 들어있는 Employee 인스턴스
				 */
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
	/**
	 * 정기권 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadPassList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
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
	
	/**
	 * 일정 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadScheduleList() {
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.SCHEDULEPATH));
			
			while((line = reader.readLine()) != null) {
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");
				/**
				 * 일정 정보가 저장되어 있는 Schedule 인스턴스
				 */
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
	
	/**
	 * 역간 소요시간 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadRequiredTime() {
		
		/**
		 * 파일 내용 한줄을 저장하는 변수
		 */
		String line ="";

		try {

			BufferedReader reader = new BufferedReader(new FileReader(data.REQUIREDTIMEPATH));

			while((line = reader.readLine())!= null) {
				
				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");

				for(String station : Data.ALL_STATION_NAME) {

					if(station.equals(lineArr[2])) {
						
						/**
						 * 역간 소요시간 정보가 들어있는 RequiredTime 인스턴스
						 */
						RequiredTime requiredTime = new RequiredTime(lineArr[1],lineArr[2],lineArr[3]);
						Data.requiredTimeList.add(requiredTime);

					}
				}




			}
			reader.close();
			return;



		} catch (Exception e) {
			System.out.println("역간 소요시간 로드 실패");
			e.printStackTrace();
		}



	}

	/**
	 * 혼잡도 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadBusy() {


		try {

			BufferedReader reader = new BufferedReader(new FileReader(data.BUSYPATH));

			/**
			 * 파일 내용 한줄을 저장하는 변수
			 */
			String line = "";

			while((line=reader.readLine())!=null ) {

				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");

				/**
				 * 혼잡도 수치를 저장하는 ArrayList
				 */
				ArrayList<Double> list = new ArrayList<>();
				
				/**
				 * 30분 단위 혼잡도를 더해서 평균을 저장하는 변수
				 */
				double avg = 0;
				/**
				 * 30분 단위 혼잡도를 더한 값을 저장하는 변수
				 */
				double sum = 0;

				if(lineArr[6].contains("분")) lineArr[6] = "0";
				if(lineArr[6].equals("")) lineArr[6] = "0";

				list.add(Double.parseDouble(lineArr[6]));


				for(int i=7; i<=43; i+=2) {

					if(lineArr[i].equals("")) {
						lineArr[i]="0";
					}
					if(lineArr[i+1].equals("")) {
						lineArr[i+1]="0";
					}

					if(lineArr[i].contains("분") || lineArr[i+1].contains("분")) {
						lineArr[i]=lineArr[i+1]="0";
					}

					/**
					 * 30분 단위 혼잡도 수치를 저장한 변수
					 */
					double n1 = Double.parseDouble(lineArr[i]);
					/**
					 * 30분 단위 혼잡도 수치를 저장한 변수
					 */
					double n2 = Double.parseDouble(lineArr[i+1]);

					sum = n1 + n2;
					avg = sum/2;
					list.add(avg);

				}


				/**
				 * 혼잡도 정보가 들어있는 Busy 인스턴스
				 */
				Busy busy = new Busy(lineArr[1],lineArr[2],lineArr[4],lineArr[5],list);
				Data.busyList.add(busy); 

			}








		} catch (Exception e) {
			System.out.println("혼잡도 로드 실패");
			e.printStackTrace();
		}


	}

	/**
	 * 북마크 정보 파일을 읽어서 ArrayList에 저장하는 메서드
	 */
	private void loadBookMark() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(data.BOOKMARKPATH));
			/**
			 * 파일 내용 한줄을 저장하는 변수
			 */
			String line = "";

			while((line=reader.readLine())!= null) {

				/**
				 * csv 파일을 읽어서 ,를 기준으로 나눈 배열
				 */
				String[] lineArr = line.split(",");

				/**
				 * 북마크 정보가 들어있는 BookMark 인스턴스
				 */
				BookMark bookMark = new BookMark(lineArr[0]);
				for(int i=1; i<lineArr.length; i++) {

					bookMark.setBookMarkList(lineArr[i]);

				}
				Data.bookMarkList.add(bookMark);


			}


		} catch (Exception e) {
			System.out.println("Load.loadBookMark");
			e.printStackTrace();
		}




	}

}//End of Load
