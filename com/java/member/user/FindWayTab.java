package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.java.station.management.FindWay;
import com.java.view.ViewAll;

public class FindWayTab {
	
	public static void findWayTab() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// 길찾기
			
			while(true) {
				
				ViewAll.roadSearchMain();
				ViewAll.chooseNum();
				String sel = reader.readLine();
				
				
				if(sel.equals("1")) {
					FindWay findWay = new FindWay();
					findWay.findWaySelMenu();
				}else if (sel.equals("2")) {
					BookMarkRoute bookMarkRoute = new BookMarkRoute();
					bookMarkRoute.bookMarkRouteSelMenu();
				}else if(sel.equals("")) {
					break;
				}else {
					
					System.out.println("\t\t\t해당 섹션이 없습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					ViewAll.pause();
					
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("FindWayTab.findWayTab()");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}
