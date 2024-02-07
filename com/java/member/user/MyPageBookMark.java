package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.station.StationNamePage;
import com.java.station.management.StationManagement;
import com.java.view.ViewAll;

public class MyPageBookMark extends BookMarkRoute{
	
	private BookMarkRoute bookMarkRoute;

	public MyPageBookMark() {

		this.bookMarkRoute = new BookMarkRoute();

	}


	public void myPageBookMarkSelMenu() {

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));



			while(true) {
				ViewAll.lineFavorite();

				System.out.print("\t\t\t입력: ");
				String sel = reader.readLine();


				if(sel.equals("1")) { //즐겨찾기 목록

					this.userBookMark = searchMyBookMark();

					if(Validation.is_ExistBookMark(this.userBookMark)) {

						this.printMyBookMark(this.userBookMark);
						this.selectBookMark(this.userBookMark);
						ViewAll.pause();
					}

				}else if(sel.equals("2")) {// 즐겨찾기 편집

					ViewAll.favoriteChange();

					System.out.print("\t\t\t입력: ");
					sel = reader.readLine();

					if(sel.equals("1")) { // 즐겨찾기 등록

						Calendar calendar = Calendar.getInstance();
						boolean check = false;
						String line = "";
						String start = "";
						String end = "";
						String time = "";
						ArrayList<String> error = new ArrayList<>();


						while(true) {

							ViewAll.favoriteAdd();

							System.out.print("\t\t\t호선: ");
							line = reader.readLine();
							
							StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);

							System.out.print("\t\t\t시작역: ");
							start = reader.readLine(); 

							System.out.print("\t\t\t도착역: ");
							end = reader.readLine();

							System.out.print("\t\t\t시간(5~24): ");
							time = reader.readLine();
							
							if(time.endsWith("시")) {
								time = time.substring(0,time.length()-1);
							}

							error = is_bookMark(line, start, end, time);

							if(error.get(0).equals("오류없음")) {
								break;
							}
							else {
								
								if(!ViewAll.errorPrint(error)) { //true 일 경우 다시 진행
									return;                      //false 일 경우 뒤로가기
								}
								
							}
						}

						calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time));

						registerBookMark(line, start, end, calendar);
						System.out.println("\t\t\t즐겨찾기 등록을 완료했습니다.");

						ViewAll.pause();

					}else if(sel.equals("2")) { //즐겨찾기 삭제

						ViewAll.favoriteDelete();
						this.userBookMark = searchMyBookMark();
						this.printMyBookMark(this.userBookMark);
						
						if(Validation.is_ExistBookMark(this.userBookMark)) {
							
							deleteBookMark();
							
						}else {
							ViewAll.pause();
						}
						

					}


				}else if(sel.equals("3")) {
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("MyPageBookMark.myPageBookMarkSelMenu");
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	private void deleteBookMark() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			String sel = ""	;
			int index = 0;
			
			while(true) {
				
				System.out.print("\t\t\t선택 할 노선: ");
				sel = reader.readLine();
				index = Integer.parseInt(sel)-1;
				if(index>=userBookMark.size()) {
					
					System.out.println("\t\t\t범위 내의 숫자만 입력하세요.");
					System.out.println("\t\t\t뒤로 가기를 하려면 엔터를 입력하세요.");
					
				}else if(sel.equals("")) {
					return;
				}else {
					break;
				}
				
			}
			
			index = Integer.parseInt(sel)-1;
			
			for(BookMark b : Data.bookMarkList) {
				
				if(b.getId().equals(LoginLogout.auth)) {
					
					b.getBookMarkList().remove(index);
					System.out.println("\t\t\t즐겨찾기 삭제를 완료했습니다.");
					ViewAll.pause();
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("MyPageBookMark.deleteBookMark");
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<String> is_bookMark(String line, String startStation, String endStation, String time) {
		
		ArrayList<String> error = new ArrayList<>();
		
			
		//호선 입력 확인 (1~9호선)
		if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
				&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
				&& !line.equals("8") && !line.equals("9")) {
			error.add("입력한 호선이 올바르지 않습니다. (1~9호선)");
		}
		
		

		if(!lineRoute(line).contains(startStation) || !lineRoute(line).contains(endStation)) {
			
			error.add(String.format("%s호선에 %s역 또는 %s역이 존재하지 않습니다.", line,startStation,endStation));

		}
		
		if(startStation.equals(endStation)) {
			
			error.add("시작역과 도착역이 같습니다.");
			
		}
		try {
			
			if(Integer.parseInt(time)<5 || Integer.parseInt(time)>24) {
				
				error.add("시간은 5~24사이값만 입력하세요.");
			}
			
		} catch (Exception e) {
			
			error.add("시간 형식이 올바르지 않습니다");

			
		}
		
		error.add("오류없음");
		
		return error;
		
	}
	
	
	
	
	
}
