package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.java.common.Validation;
import com.java.station.StationNamePage;
import com.java.station.management.FindWay;
import com.java.station.management.StationManagement;
import com.java.view.View;
import com.java.view.ViewAll;

public class CurrentTimeFindWay extends FindWay {
	
	
	public void currentTimeFindWay() {
		
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			boolean check = true;
			String sel = "";
			String line = "";
			String start = "";
			String end = "";
			Calendar calendar = Calendar.getInstance();

			while(true) {

				//ViewAll 선택노선 추가하기
				System.out.print("호선: ");
				line = reader.readLine();
				
				
				if(line.contains("호선")) {
					line = line.replace("호선", "");
				}

				ViewAll.roadSearchRouteTop();
				StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);
				
				
				System.out.print("출발역: ");
				start = reader.readLine();
				
				if(start.endsWith("역")) {
					start = start.substring(0,start.length()-1);
				}

				System.out.print("도착역: ");
				end = reader.readLine();
				
				if(end.endsWith("역")) {
					end = end.substring(0,end.length()-1);
				}

				check = Validation.is_currentTime(line, start, end);

				if(check) {
					break;
				}else { 

					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					System.out.println("뒤로 가기를 원한다면 엔터를 입력하세요.");
					System.out.println("다시 진행을 원한다면 엔터제외 아무키나 입력하세요.");
					
					String input = reader.readLine();
					if(input.equals("")) {
						return;
					}
					
				}

			}
			if(calendar.get(Calendar.HOUR_OF_DAY)< 5 || calendar.get(Calendar.HOUR_OF_DAY) > 24 ) {
				System.out.println("현재 시간에는 열차가 운행하지 않습니다. 다른 날짜보기를 이용해주세요.");
				View.pause();
				return;
			}

			findWay(line, start, end, calendar);

			View.pause();

			ViewAll.roadSearchRouteTimeBottom();
			System.out.print("입력: ");
			sel = reader.readLine();

			if(sel.equals("1")) {

				registerBookMark(line, start, end, calendar);
				System.out.println("즐겨찾기 등록을 완료했습니다.");
				View.pause();
				return;

			}
			else if(sel.equals("")) {
				return;
			}


		} catch (Exception e) {
			System.out.println("CurrentTimeFindWay.currentTimeFindWay()");
			e.printStackTrace();
		}
		
		
		
	}

}
