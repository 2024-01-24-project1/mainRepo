package com.java.member.employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.java.common.LoginLogout;
import com.java.station.management.AddTrain;
import com.java.station.management.ChangeNoChairTrain;
import com.java.view.ViewAll;

public class StationManagementTab {
	
	
	public static void stationManagementTab() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			
			if(LoginLogout.level.equals("5") || LoginLogout.level.equals("3")) {
				
				// 역관리
				ViewAll.stationMain();
				ViewAll.chooseNum();
				String sel = reader.readLine();
				
				if(sel.equals("1")) {
					AddTrain addTrain = new AddTrain();
					addTrain.addTrain();
				}else if (sel.equals("2")) {
					ChangeNoChairTrain changeNoChairTrain = new ChangeNoChairTrain();
					changeNoChairTrain.changeNoChairTrain();
				}
				
			}else {
				System.out.println("접근 권한이 없습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("StationManagementTab.stationManagementTab");
			e.printStackTrace();
		}

		
		
		
		
		
		
	}

}
