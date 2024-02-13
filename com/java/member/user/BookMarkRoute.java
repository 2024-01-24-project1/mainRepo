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

/**
 * 즐겨찾기 목록으로 길찾기를 하는 메서드
 */
public class BookMarkRoute extends FindWay{
	
	/**
	 * 현재 로그인한 고객의 id에 저장된 즐겨찾기 목록을 저장하는 ArrayList
	 */
	protected List<String> userBookMark = new ArrayList<>();
	
	/**
	 * 고객이 즐겨찾기 목록중 경로를 선택하는 메서드
	 */
	public void bookMarkRouteSelMenu() {
		
		
		try {
			
			/**
			 * 고객이 즐겨찾기 목록이 있는지 true false를 저장하는 변수
			 */
			boolean check = true;
			
			ViewAll.roadSearchFavoriteList();
			userBookMark = searchMyBookMark();
			
			check = Validation.is_ExistBookMark(userBookMark);
			
			if(!check) return;
			
			
			//즐겨 찾기 목록 출력
			printMyBookMark(userBookMark);
			selectBookMark(userBookMark);
			ViewAll.pause();
			
			
			
		} catch (Exception e) {
			System.out.println("BookMarkRoute.bookMarkRouteSelMenu");
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * 고객아이디에 저장된 즐겨찾기 노선을 List로 반환하는 메서드
	 * @return 즐겨찾기 노선 List<String>
	 */
	protected List<String> searchMyBookMark() {
		
		userBookMark = Data.bookMarkList.stream()
									    .filter(b -> b.getId().equals(LoginLogout.auth))
									    .flatMap(b -> b.getBookMarkList().stream())
									    .collect(Collectors.toList());
		return userBookMark;
	}
	
	/**
	 * 즐겨찾기 목록을 보고 해당 즐겨찾기 노선으로 길찾기 하는 메서드
	 * @param userBookMark 즐겨찾기 노선
	 */
	protected void selectBookMark(List<String> userBookMark) {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			/**
			 * 현재시간을 저장하는 Calendar 변수
			 */
			Calendar calendar = Calendar.getInstance();
			/**
			 * 사용자가 입력한 값을 저장하는 변수
			 */
			String sel = "";
			/**
			 * 즐겨찾기 노선의 번호를 저장하는 변수
			 */
			int index = 0;
			/**
			 * 년도를 저장하는 변수
			 */
			int year = 0;
			/**
			 * 월을 저장하는 변수
			 */
			int month = 0;
			/**
			 * 일을 저장하는 변수
			 */
			int date = 0;
			/**
			 * 시간을 저장하는 변수
			 */
			int hour = 0;
			/**
			 * 분을 저장하는 변수
			 */
			int minute = 0;
			
			while(true) {
				
				try {
					
					System.out.print("\t\t\t선택 할 노선(숫자): ");
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
			/**
			 * 즐겨찾기 목록을 분리해서 저장하는 배열
			 */
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
	
	/**
	 * 고객의 즐겨찾기 목록에 있는 경로를 출력하는 메서드
	 * @param userBookMark 고객의 즐겨찾기 목록
	 */
	protected void printMyBookMark(List<String> userBookMark) {
		
		/**
		 * 즐겨찾기 목록의 번호를 저장하는 변수
		 */
		int index = 1;
		
		
		for(String route : userBookMark) {
			
			String[] temp = route.split("-");
			
			System.out.printf("\t\t\t       %d. %s -> %s\n\n", index++, temp[1],temp[2]);
			
		}
		System.out.println("╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬═════════════╬╬");
		
	}
	
	
	

}
