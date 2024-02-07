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
				System.out.print("입력:");
				String sel = reader.readLine();
				
				
				if(sel.equals("1")) {
					FindWay findWay = new FindWay();
					findWay.findWaySelMenu();
				}else if (sel.equals("2")) {
					BookMarkRoute bookMarkRoute = new BookMarkRoute();
					bookMarkRoute.bookMarkRouteSelMenu();
				}else if(sel.equals("3") || sel.equals("")) {
					break;
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("FindWayTab.findWayTab()");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}
