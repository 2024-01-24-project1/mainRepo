package com.java.member.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.member.Member;
import com.java.view.ViewAll;

public class MyPageBookMark extends BookMarkRoute{
	
	private BookMarkRoute bookMarkRoute;
	
	public MyPageBookMark() {
		
		this.bookMarkRoute = new BookMarkRoute();
			
	}

	
	public void myPageBookMarkSelMenu() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			ViewAll.lineFavorite();
			String sel = reader.readLine();
			
			
			if(sel.equals("1")) { //즐겨 찾기 목록
				
				this.userBookMark = searchMyBookMark();
				this.printMyBookMark(this.userBookMark);
				this.selectBookMark(this.userBookMark);
				
			}else if(sel.equals("2")) {// 즐겨 찾기 편집
				
				ViewAll.favoriteChange();
				sel = reader.readLine();
				
				if(sel.equals("1")) { // 즐겨 찾기 등록
					
					Calendar calendar = Calendar.getInstance();
					
					ViewAll.favoriteAdd();
					
					System.out.print("호선: ");
					String line = reader.readLine();
					
					System.out.print("시작역: ");
					String start = reader.readLine(); 
					
					System.out.print("도착역: ");
					String end = reader.readLine();
					
					System.out.print("시간(5~24): ");
					String time = reader.readLine();
					
					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time));
					
					registerBookMark(line, start, end, calendar);
					
				}else if(sel.equals("2")) { //즐겨 찾기 삭제
					
					ViewAll.favoriteDelete();
					this.userBookMark = searchMyBookMark();
					this.printMyBookMark(this.userBookMark);
					
					System.out.print("삭제할 즐겨찾기: ");
					sel = reader.readLine();
					
					deleteBookMark(sel);
					
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("MyPageBookMark.myPageBookMarkSelMenu");
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	private void deleteBookMark(String sel) {
		
		int index = Integer.parseInt(sel)-1;
		
		for(BookMark b : Data.bookMarkList) {
			
			if(b.getId().equals(LoginLogout.auth)) {
				
				b.getBookMarkList().remove(index);
				
			}
			
			
		}
		
		
	}
	
	
	
	
	
}
