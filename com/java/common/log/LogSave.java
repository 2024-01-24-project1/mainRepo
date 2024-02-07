package com.java.common.log;

import java.util.ArrayList;
import java.util.Calendar;

import com.java.common.Data;
import com.java.common.LoginLogout;
import com.java.member.employee.Employee;

public class LogSave {
	
	private static final String[] LOGLIST = {
			"역관리-의자 없는 열차 변경", //
			"역관리-일반 열차 추가",  	//
			"직원 관리-전체 직원 정보 보기-호선으로 직원 검색",   //   
	    	"직원 관리-전체 직원 정보 보기-역 이름으로 직원 검색",//
	    	"직원 관리-전체 직원 정보 보기-이름으로 직원 검색",//
	    	"직원 관리-직원 정보 수정-근무지 변경", //
	    	"직원 관리-직원 정보 수정-직급 변경",	//
	    	"직원 관리-직원 정보 수정-권한 변경",//
	    	"직원 관리-직원 정보 수정-직원 삭제",//
	    	"안전 요원 관리-전체 안전 요원 보기-호선으로 안전 요원 검색", //
	    	"안전 요원 관리-전체 안전 요원 보기-역 이름으로 안전 요원 검색",//
	    	"안전 요원 관리-안전 요원 배치",//
	    	"안전 요원 관리-안전 요원 부서 해제",//
	    	"고객의 소리-전체 분실물 보기-분실물 검색", //
	    	"고객의 소리-전체 분실물 보기-분실물 추가", //
	    	"고객의 소리-전체 분실물 보기-분실물 제거", //
	    	"고객의 소리-전체 민원 보기",//
	    	"행사 캘린더-전체 일정 보기",//
	    	"행사 캘린더-일정 추가",//
	    	"행사 캘린더-일정 제거",//
	    	"통계 정보-혼잡도 통계",//
	    	"통계 정보-이용객 통계",//
	    	"통계 정보-매출 통계",//
	    	"개인 정보-개인 정보 확인",//
	    	"개인 정보-개인 정보 수정",//
	    	"행동 로그 보기"};//
	
	
	public static final int NOCHAIRTRAIN = 0;
	public static final int ADDTRAIN = 1;
	public static final int SEARCHFOREMPLOYEESBYLINE = 2;
	public static final int SEARCHFOREMPLOYEESBYSTATION = 3;
	public static final int SEARCHFOREMPLOYEESBYNAME = 4;
	public static final int CHANGEWORKSPACE = 5;
	public static final int CHANGEPOSITION = 6;
	public static final int CHANGELEVEL = 7;
	public static final int DELETEEMPLOYEE = 8;
	public static final int SEARCHFORSAFYTYMANYSBYLINE = 9;
	public static final int SEARCHFORSAFYTYMANBYSTATION = 10;
	public static final int ARRANGESAFYTYMAN = 11;
	public static final int SAFYTYMANWORKAREACLEAR = 12;
	public static final int LOSTARTICLESEARCH = 13;
	public static final int LOSTARTICLEADD = 14;
	public static final int LOSTARTICLEREMOVE = 15;
	public static final int USERVOICESEARCH = 16;
	public static final int SCHEDULEPAGE = 17;
	public static final int ADDSCHEDULE = 18;
	public static final int REMOVESCHEDULE = 19;
	public static final int BUSYSTAT = 20;
	public static final int USERSTATSTAB = 21;
	public static final int SALESSTATSTAB = 22;
	public static final int MYPAGE = 23;
	public static final int MODIFYMYPAGE = 24;
	public static final int VIEWLOG = 25;
	
	
	
	public static void logSave(int index) {
		
		Calendar calendar = Calendar.getInstance();
		
		String time = String.format("%d-%02d-%02d",calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1),calendar.get(Calendar.DATE));
		
		ArrayList<String> list = new ArrayList<>();
		list.add(LOGLIST[index]);
		
		for(Log log : Data.logList) {
			
			if(log.getId().equals(LoginLogout.auth) && log.getTime().equals(time)) {
				
				log.setAction(LOGLIST[index]);
				return;
			}
		}
		
		Log log = new Log(time,LoginLogout.auth,list);
		Data.logList.add(log);
		
	}
	
	
	/**
	 * 선택지가 호선으로 검색인지 역이름으로 검색인지 사람이름으로 검색인지 확인후 저장
	 * @param sel
	 */
	public static void employeeSearchLog(String sel , ArrayList<Employee> list) {
		
		if(sel.equals("1") || sel.equals("2") || sel.equals("3") || sel.equals("4") || sel.equals("5") ||
				sel.equals("6") || sel.equals("7") || sel.equals("8") || sel.equals("9")) {
			
			if(list.get(0).getPosition().equals("안전요원")) {
				
				logSave(LogSave.SEARCHFORSAFYTYMANYSBYLINE);
				
			}else {
				logSave(LogSave.SEARCHFOREMPLOYEESBYLINE);
				
			}
			
			return;
			
			
		}
		
		for(String station : Data.ALL_STATION_NAME) {
			
			if(station.equals(sel)){
				
				if(list.get(0).getPosition().equals("안전요원")) {
					
					logSave(LogSave.SEARCHFORSAFYTYMANBYSTATION);
					
				}else {
					
					logSave(LogSave.SEARCHFOREMPLOYEESBYSTATION);
					
				}
				return;
				
			}
			
		}
		
		for(Employee e : Data.employeeList) {
			
			
			if(e.getName().equals(sel)) {
				logSave(LogSave.SEARCHFOREMPLOYEESBYNAME);
				return;
			}
			
		}
		
	}
	
	    
	    
	

}
