package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.java.common.Validation;
import com.java.station.StationNamePage;
import com.java.station.management.FindWay;
import com.java.station.management.StationManagement;
import com.java.view.ViewAll;

/**
 * 다른날짜를 기준으로 길찾기를 하는 클래스
 */
public class AnotherDateFindWay extends FindWay {
	
	/**
	 * 입력값을 기준으로 길찾기를 하는 메서드
	 */
	public void anotherDateFindWay() {
		
		

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			/**
			 * 에러 문구를 저장하는 ArrayList
			 */
			ArrayList<String> error = new ArrayList<>();
			
			/**
			 * 년도를 저장하는 변수
			 */
			String year = "";
			/**
			 * 월을 저장하는 변수
			 */
			String month = "";
			/**
			 * 일을 저장하는 변수
			 */
			String date = "";
			/**
			 * 시를 저장하는 변수
			 */
			String hour = "";
			/**
			 * 분을 저장하는 변수
			 */
			String minute = "";
			/**
			 * 분을 저장하는 변수
			 */
			String line = "";
			/**
			 * 출발역을 저장하는 변수
			 */
			String start = "";
			/**
			 * 도착역을 저장하는 변수
			 */
			String end = "";
			/**
			 * 입력받은 날짜를 저장할 Calendar 변수
			 */
			Calendar calendar = Calendar.getInstance();

			while(true) {

				System.out.print("\t\t\t\t년도 입력: ");
				year = reader.readLine();

				System.out.print("\t\t\t\t월 입력  : ");
				month = reader.readLine();

				System.out.print("\t\t\t\t일 입력  : ");
				date = reader.readLine();

				System.out.print("\t\t\t\t시간     : ");
				hour = reader.readLine();

				System.out.print("\t\t\t\t분       : ");
				minute = reader.readLine();
				
				if(hour.endsWith("시")) {
					hour.subSequence(0,hour.length()-1);
				}
				if(minute.endsWith("분")) {
					minute.substring(0,minute.length()-1);
				}
				

				error = Validation.is_anotherDate(year,month,date,hour,minute);

				if(error.get(0).equals("오류없음")) {

					break;

				}else {

					if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
						return;                      //false 일 경우 뒤로가기
					}

				}
			}


			calendar.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
			calendar.set(Calendar.MINUTE, Integer.parseInt(minute));



			while(true) {

				System.out.print("\t\t\t\t호선: ");
				line = reader.readLine();

				if(line.contains("호선")) {
					line = line.replace("호선", "");
				}

				StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);

				System.out.print("\t\t\t\t출발역: ");
				start = reader.readLine();

				if(start.endsWith("역")) {
					start = start.substring(0,start.length()-1);
				}

				System.out.print("\t\t\t\t도착역: ");
				end = reader.readLine();

				if(end.endsWith("역")) {
					end = end.substring(0,end.length()-1);
				}
				error = new ArrayList<>();
				error = Validation.is_currentTime(line, start, end);

				if(error.get(0).equals("오류없음")) {

					break;

				}else {

					if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
						return;                      //false 일 경우 뒤로가기
					}
					

				}

			}

			findWay(line, start, end, calendar);
			ViewAll.pause();

			ViewAll.roadSearchRouteTimeBottom();
			
			while(true) {
				String sel = reader.readLine();

				if(sel.equals("1")) {

					registerBookMark(line, start, end, calendar);
					System.out.println("\t\t\t즐겨찾기 등록을 완료했습니다.");
					ViewAll.pause();
					return;

				} else if(sel.equals("")) {
					return;
					
				} else {
					
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
					
					
				}
				
				
			}


		} catch (Exception e) {
			System.out.println("AnotherDateFindWay.anotherDateFindWay()");
			e.printStackTrace();
		}


		
		
		
		
	}

}
