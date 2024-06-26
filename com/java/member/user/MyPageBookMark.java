package com.java.member.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.station.StationNamePage;
import com.java.station.management.StationManagement;
import com.java.view.ViewAll;

/**
 * 고객 마이페이지의 북마크를 나타내는 클래스
 */
public class MyPageBookMark extends BookMarkRoute{
	
	/**
	 * 부모클래스인 BookMarkRoute의 인스턴스
	 */
	private BookMarkRoute bookMarkRoute;

	/**
	 * 마이페이지 북마크 클래스의 생성자
	 */
	public MyPageBookMark() {

		this.bookMarkRoute = new BookMarkRoute();

	}

	/**
	 * 마이페이지 북마크에서 메뉴를 선택하는 메서드
	 */
	public void myPageBookMarkSelMenu() {

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


			while(true) {
				
				ViewAll.lineFavorite();
				ViewAll.chooseNum();
				/**
				 * 사용자의 입력값을 저장하는 변수
				 */
				String sel = reader.readLine();


				if(sel.equals("1")) { //즐겨찾기 목록

					bookMarkRoute.userBookMark = searchMyBookMark();

					if(Validation.is_ExistBookMark(bookMarkRoute.userBookMark)) {
						
						ViewAll.favoriteView();
						System.out.println();
						this.printMyBookMark(bookMarkRoute.userBookMark);
						this.selectBookMark(bookMarkRoute.userBookMark);
						ViewAll.pause();
					}

				}else if(sel.equals("2")) {// 즐겨찾기 편집
					
					
					while(true) {
						
						ViewAll.favoriteChange();
						ViewAll.chooseNum();

						sel = reader.readLine();

						if(sel.equals("1")) { // 즐겨찾기 등록

							addBookMark();

						}else if(sel.equals("2")) { //즐겨찾기 삭제

							ViewAll.favoriteDelete();
							this.userBookMark = searchMyBookMark();
							this.printMyBookMark(bookMarkRoute.userBookMark);

							if(Validation.is_ExistBookMark(bookMarkRoute.userBookMark)) {
								
								deleteBookMark();

							}else {
								ViewAll.pause();
							}


						}else if(sel.equals("")) {
							break;
						}else {

							System.out.println("\t\t\t해당 섹션이 없습니다.");
							System.out.println("\t\t\t다시 입력해주세요.");
							ViewAll.pause();

						}

					}
				}else if(sel.equals("")) {
					break;
					
				}else {
					
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
					
					
				}
			}

		} catch (Exception e) {
			System.out.println("MyPageBookMark.myPageBookMarkSelMenu");
			e.printStackTrace();
		}
		
		
		
		
		
		
	}


	/**
	 * 북마크를 추가하는 메서드
	 */
	private void addBookMark()  {
		
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			/**
			 * 에러 문구를 저장하는 ArrayList
			 */
			ArrayList<String> error = new ArrayList<>();
			/**
			 * 시간을 저장하는 Calendar 변수
			 */
			Calendar calendar = Calendar.getInstance();
			/**
			 * 호선을 저장하는 변수
			 */
			String line = "";
			/**
			 * 시작역을 저장하는 변수
			 */
			String start = "";
			/**
			 * 도착역을 저장하는 변수
			 */
			String end = "";
			/**
			 * 시간대를 저장하는 변수
			 */
			String time = "";

			
			while(true) {

				ViewAll.favoriteAdd();

				System.out.print("\t\t\t호선     : ");
				line = reader.readLine();

				if(line.contains("호선")) {
					line = line.replace("호선", "");
				}

				StationNamePage.stationNamePage(StationManagement.lineRoute(line), line);

				System.out.print("\t\t\t시작역   : ");
				start = reader.readLine(); 

				if(start.endsWith("역")) {
					start = start.substring(0,start.length()-1);
				}

				System.out.print("\t\t\t도착역   : ");
				end = reader.readLine();

				if(end.endsWith("역")) {
					end = end.substring(0,end.length()-1);
				}

				System.out.print("\t\t\t시간(5~24): ");
				time = reader.readLine();

				error = Validation.is_bookMark(line, start, end, time);

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
			
		} catch (Exception e) {
			System.out.println("MyPageBookMark.addBookMark()");
			e.printStackTrace();
		}


	}
	
	/**
	 * 북마크를 제거하는 메서드
	 */
	private void deleteBookMark() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			/**
			 * 사용자가 입력한 값을 저장하는 변수
			 */
			String sel = ""	;
			/**
			 * 사용자가 제거할 즐겨찾기 목록의 번호를 저장하는 변수
			 */
			int index = 0;
			
			while(true) {
				
				try {
					System.out.print("\t\t\t삭제 할 노선(숫자): ");
					sel = reader.readLine();
					
					if(sel.equals("")) return;
					
					index = Integer.parseInt(sel)-1;
					
				} catch (Exception e) {
					
					System.out.println("\t\t\t숫자만 입력하세요.");
					continue;
					
				}
				if(index>=userBookMark.size()) {
					
					System.out.println("\t\t\t범위 내의 숫자만 입력하세요.");
					System.out.println("\t\t\t뒤로 가기를 하려면 엔터를 입력하세요.");
					
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
	
	
	
	
	
}
