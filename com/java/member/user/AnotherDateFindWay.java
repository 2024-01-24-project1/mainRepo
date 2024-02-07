package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.java.common.Validation;
import com.java.station.StationNamePage;
import com.java.station.management.FindWay;
import com.java.station.management.StationManagement;

import com.java.view.ViewAll;

public class AnotherDateFindWay extends FindWay {
	
	public void anotherDateFindWay() {
		
		

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			boolean check = true;
			String year = "";
			String month = "";
			String date = "";
			String hour = "";
			String minute = "";
			String line = "";
			String start = "";
			String end = "";
			Calendar calendar = Calendar.getInstance();

			while(true) {
				ViewAll.roadSearchRouteOtherTimeTop();
				System.out.print("\t\t\t년도 입력: ");
				year = reader.readLine();

				System.out.print("\t\t\t월 입력: ");
				month = reader.readLine();

				System.out.print("\t\t\t일 입력: ");
				date = reader.readLine();

				System.out.print("\t\t\t시간: ");
				hour = reader.readLine();

				System.out.print("\t\t\t분: ");
				minute = reader.readLine();

				check = Validation.is_anotherDate(year,month,date,hour,minute);

				if(check) {

					break;

				}else {
					ViewAll.errorQuestionEmo();	
					System.out.println("\t\t\t잘못된 입력입니다. 다시 입력하세요.");
					System.out.println("\t\t\t뒤로 가기를 원한다면 엔터를 입력하세요.");
					System.out.println("\t\t\t다시 진행을 원한다면 엔터제외 아무키나 입력하세요.");
					
					String input = reader.readLine();
					if(input.equals("")) {
						return;
					}

				}
			}


		

			calendar.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
			calendar.set(Calendar.MINUTE, Integer.parseInt(minute));



			while(true) {
				ViewAll.roadSearchRouteTop();
				System.out.print("\t\t\t호선: ");
				line = reader.readLine();

				if(line.contains("\t\t\t호선")) {
					line = line.replace("\t\t\t호선", "");
				}
				
				StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);

				System.out.print("\t\t\t출발역: ");
				start = reader.readLine();

				if(start.endsWith("\t\t\t역")) {
					start = start.substring(0,start.length()-1);
				}

				System.out.print("\t\t\t도착역: ");
				end = reader.readLine();

				if(end.endsWith("역")) {
					end = end.substring(0,end.length()-1);
				}

				check = Validation.is_currentTime(line, start, end);

				if(check) {

					break;

				}else {

					System.out.println("\t\t\t잘못된 입력입니다. 다시 입력하세요.");
					System.out.println("\t\t\t뒤로 가기를 원한다면 엔터를 입력하세요.");
					System.out.println("\t\t\t다시 진행을 원한다면 엔터제외 아무키나 입력하세요.");

					String input = reader.readLine();
					if(input.equals("")) {
						return;
					}

				}

			}

			findWay(line, start, end, calendar);
			ViewAll.pause();

			ViewAll.roadSearchRouteTimeBottom();
			System.out.print("\t\t\t입력: ");
			String sel = reader.readLine();

			if(sel.equals("1")) {

				registerBookMark(line, start, end, calendar);
				System.out.println("\t\t\t즐겨찾기 등록을 완료했습니다.");
				ViewAll.pause();
				return;

			}
			else if(sel.equals("")) {
				return;
			}



		} catch (Exception e) {
			ViewAll.successThumbUpEmo();
			System.out.println("AnotherDateFindWay.anotherDateFindWay()");
			e.printStackTrace();
		}


		
		
		
		
	}

}
