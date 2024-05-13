package com.java.member.employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.java.common.LoginLogout;
import com.java.station.management.AddTrain;
import com.java.station.management.ChangeNoChairTrain;
import com.java.view.ViewAll;

/**
 * 역 관리 메뉴 나타내는 클래스
 */
public class StationManagementTab {
	
	/**
	 * StationManagementTab클래스의 기본 생성자
	 */
	public StationManagementTab() {
	}
	
	/**
	 * 역 관리 메뉴 나타내는 메서드
	 */
	public static void stationManagementTab() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			
			if(LoginLogout.level.equals("5") || LoginLogout.level.equals("3")) {

				// 역관리

				while(true) {

					ViewAll.stationMain();
					ViewAll.chooseNum();
					String sel = reader.readLine();
					if(sel.equals("1")) {
						AddTrain addTrain = new AddTrain();
						addTrain.addTrain();
					}else if (sel.equals("2")) {
						ChangeNoChairTrain changeNoChairTrain = new ChangeNoChairTrain();
						changeNoChairTrain.changeNoChairTrain();
					}else if(sel.equals("")) {
						break;
					}
				}
			}else {
				System.out.println("\t\t\t접근 권한이 없습니다.");
				ViewAll.pause();
			}
			
		} catch (Exception e) {
			System.out.println("StationManagementTab.stationManagementTab");
			e.printStackTrace();
		}

		
		
		
		
		
		
	}

}
