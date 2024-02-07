package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.common.Validation;
import com.java.station.management.FindWay;
import com.java.view.ViewAll;

public class BookMarkRoute extends FindWay{
	
	protected List<String> userBookMark = new ArrayList<>();
	
	public void bookMarkRouteSelMenu() {
		
		
		try {
			
			boolean check = true;
			
			ViewAll.roadSearchFavoriteList();
			userBookMark = searchMyBookMark();
			
			check = Validation.is_ExistBookMark(userBookMark);
			
			if(!check) return;
			
			
			//즐겨 찾기 목록 출력
			printMyBookMark(userBookMark);
			selectBookMark(userBookMark);
			
			
			
			
		} catch (Exception e) {
			System.out.println("BookMarkRoute.bookMarkRouteSelMenu");
			e.printStackTrace();
		}
		
		
		
	}

	protected List<String> searchMyBookMark() {
		
		userBookMark = Data.bookMarkList.stream()
									    .filter(b -> b.getId().equals(LoginLogout.auth))
									    .flatMap(b -> b.getBookMarkList().stream())
									    .collect(Collectors.toList());
		return userBookMark;
	}
	
	/**
	 * 즐겨찾기 목록을 보고 해당 즐겨찾기 노선으로 길찾기 하는 메서드
	 * @param userBookMark
	 */
	protected void selectBookMark(List<String> userBookMark) {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			Calendar calendar = Calendar.getInstance();
			String sel = "";
			int year = 0;
			int index = 0;
			int month = 0;
			int date = 0;
			int hour = 0;
			int minute = 0;
			
			while(true) {
				
				try {
					
					System.out.print("\t\t\t선택 할 노선: ");
					sel = reader.readLine();
					index = Integer.parseInt(sel)-1;
					
				} catch (Exception e) {
					
					System.out.println("\t\t\t숫자만 입력하세요.");
					continue;
					
				}
				if(index>=userBookMark.size()) {
					
					System.out.println("\t\t\t범위 내의 숫자만 입력하세요.");
					System.out.println("\t\t\t뒤로 가기를 하려면 엔터를 입력하세요.");
					
				}else if(sel.equals("")) {
					return;
				}else {
					break;
				}
				
			}
			
			String[] temp = userBookMark.get(index).split("-");
			year = Integer.parseInt(temp[3]);
			month = Integer.parseInt(temp[4])-1;
			date = Integer.parseInt(temp[5]);
			hour = Integer.parseInt(temp[6]);
			minute = Integer.parseInt(temp[7]);
			calendar.set(year, month, date, hour, minute);
			
			if(calendar.get(Calendar.HOUR_OF_DAY)< 5 || calendar.get(Calendar.HOUR_OF_DAY) > 24 ) {
				System.out.println("\t현재 시간에는 열차가 운행하지 않습니다. 다른 날짜보기를 이용해주세요.");
				ViewAll.pause();
				return;
			}
			
			ViewAll.roadSearchFavoriteList();
			this.findWay(temp[0],temp[1],temp[2],calendar);
		} catch (Exception e) {
			System.out.println("BookMarkRoute.selectBookMark");
			e.printStackTrace();
		}
		
	}

	protected void printMyBookMark(List<String> userBookMark) {
		
		int index = 1;
		
		
		for(String route : userBookMark) {
			
			String[] temp = route.split("-");
			
			System.out.printf("\t\t\t\t%d. %s -> %s\n", index++, temp[1],temp[2]);
			
		}
	}
	
	
	

}
