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

public class CurrentTimeFindWay extends FindWay {


	public void currentTimeFindWay() {


		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			ArrayList<String> error = new ArrayList<>();
			String sel = "";
			String line = "";
			String start = "";
			String end = "";
			Calendar calendar = Calendar.getInstance();

			while(true) {

				//ViewAll 선택노선 추가하기
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

				error = Validation.is_currentTime(line, start, end);

				if(error.get(0).equals("오류없음")) {
					break;
				}else { 

					if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
						return;                      //false 일 경우 뒤로가기
					}


				}
			}
			if(calendar.get(Calendar.HOUR_OF_DAY)< 5 || calendar.get(Calendar.HOUR_OF_DAY) > 24 ) {
				System.out.println("\t현재 시간에는 열차가 운행하지 않습니다. 다른 날짜보기를 이용해주세요.");
				ViewAll.pause();
				return;
			}

			findWay(line, start, end, calendar);

			ViewAll.pause();

			ViewAll.roadSearchRouteTimeBottom();
			ViewAll.pause();
			System.out.print("\t\t\t\t입력: ");
			sel = reader.readLine();

			while(true) {
				if(sel.equals("1")) {

					registerBookMark(line, start, end, calendar);
					System.out.println("\t\t\t즐겨찾기 등록을 완료했습니다.");
					ViewAll.pause();
					return;

				}
				else if(sel.equals("")) {
					return;
				}else {

					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();


				}
			}

		} catch (Exception e) {
			System.out.println("CurrentTimeFindWay.currentTimeFindWay()");
			e.printStackTrace();
		}





	}
}