package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.view.View;
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

				System.out.print("입력: ");
				String sel = reader.readLine();


				if(sel.equals("1")) { //즐겨찾기 목록

					this.userBookMark = searchMyBookMark();

					if(Validation.is_ExistBookMark(this.userBookMark)) {

						this.printMyBookMark(this.userBookMark);
						this.selectBookMark(this.userBookMark);
						View.pause();
					}

				}else if(sel.equals("2")) {// 즐겨찾기 편집

					ViewAll.favoriteChange();

					System.out.print("입력: ");
					sel = reader.readLine();

					if(sel.equals("1")) { // 즐겨찾기 등록

						Calendar calendar = Calendar.getInstance();
						boolean check = false;
						String line = "";
						String start = "";
						String end = "";
						String time = "";


						while(true) {

							ViewAll.favoriteAdd();

							System.out.print("호선: ");
							line = reader.readLine();

							System.out.print("시작역: ");
							start = reader.readLine(); 

							System.out.print("도착역: ");
							end = reader.readLine();

							System.out.print("시간(5~24): ");
							time = reader.readLine();

							check = is_bookMark(line, start, end, time);

							if(check) {
								break;
							}
							else {
								System.out.println("잘못된 입력입니다. 다시 입력하세요.");
							}
						}

						calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time));

						registerBookMark(line, start, end, calendar);
						System.out.println("즐겨찾기 등록을 완료했습니다.");

						View.pause();

					}else if(sel.equals("2")) { //즐겨찾기 삭제

						ViewAll.favoriteDelete();
						this.userBookMark = searchMyBookMark();
						this.printMyBookMark(this.userBookMark);
						
						if(Validation.is_ExistBookMark(this.userBookMark)) {
							
							deleteBookMark();
							
						}else {
							View.pause();
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
				
				System.out.print("선택 할 노선: ");
				sel = reader.readLine();
				index = Integer.parseInt(sel)-1;
				if(index>=userBookMark.size()) {
					
					System.out.println("범위 내의 숫자만 입력하세요.");
					System.out.println("뒤로 가기를 하려면 엔터를 입력하세요.");
					
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
					System.out.println("즐겨찾기 삭제를 완료했습니다.");
					View.pause();
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("MyPageBookMark.deleteBookMark");
			e.printStackTrace();
		}
		
		
	}
	
	public boolean is_bookMark(String line, String startStation, String endStation, String time) {
		
		if(line.contains("호선")){
			line = line.replace("호선", "");
		}
			
		//호선 입력 확인 (1~9호선)
		if(!line.equals("1") && !line.equals("2") && !line.equals("3") 
				&& !line.equals("4") && !line.equals("5") && !line.equals("6") && !line.equals("7") 
				&& !line.equals("8") && !line.equals("9")) {
			return false;
		}
		
		

		if(!lineRoute(line).contains(startStation) && !lineRoute(line).contains(endStation)) {
			
			return false;

		}

		
		return true;
		
	}
	
	
	
	
	
}
