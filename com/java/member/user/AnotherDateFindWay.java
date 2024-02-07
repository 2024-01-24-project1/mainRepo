package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.java.common.Validation;
import com.java.station.management.FindWay;
import com.java.view.ViewAll;

public class AnotherDateFindWay extends FindWay {
	
	public void anotherDateFindWay() {
		
		

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			ArrayList<String> error = new ArrayList<>();
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

				System.out.print("년도 입력: ");
				year = reader.readLine();

				System.out.print("월 입력: ");
				month = reader.readLine();

				System.out.print("일 입력: ");
				date = reader.readLine();

				System.out.print("시간: ");
				hour = reader.readLine();

				System.out.print("분: ");
				minute = reader.readLine();

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

				System.out.print("\t\t\t호선: ");
				line = reader.readLine();

				if(line.contains("\t\t\t호선")) {
					line = line.replace("호선", "");
				}

				System.out.print("\t\t\t출발역: ");
				start = reader.readLine();

				if(start.endsWith("역")) {
					start = start.substring(0,start.length()-1);
				}

				System.out.print("\t\t\t도착역: ");
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
			System.out.println("\t\t\t뒤로 가기를 원한다면 엔터를 입력하세요.");
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
			System.out.println("AnotherDateFindWay.anotherDateFindWay()");
			e.printStackTrace();
		}


		
		
		
		
	}

}
