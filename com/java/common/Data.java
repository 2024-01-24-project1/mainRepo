package com.java.common;

import java.util.ArrayList;
import java.util.List;

import com.java.busy.Busy;
import com.java.common.log.Log;
import com.java.common.lostarticle.LostArticle;
import com.java.member.employee.Employee;
import com.java.member.user.BookMark;
import com.java.member.user.User;
import com.java.member.user.UserVoice;
import com.java.requiredtime.RequiredTime;
import com.java.schedule.Schedule;
import com.java.station.PassengerCounting;
import com.java.station.timetable.StationTime;

// 모든 경로, csv파일, 정적데이터 클래스
/**
 * csv 데이터 파일의 경로와 static으로 사용되는 ArrayList가 선언되어 있는 클래스
 */
public final class Data {
	
	// 경로
	/**
	 * 정기권의 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String PASSPATH = ".\\src\\data\\pass.csv";				  //정기권
	/**
	 * 고객의 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String USERPATH = ".\\src\\data\\user.csv";				  //고객정보
	/**
	 * 직원의 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String EMPLOYEEPATH = ".\\src\\data\\employee.csv";		  //직원정보
	/**
	 * 일정이 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String SCHEDULEPATH = ".\\src\\data\\schedule.csv";		  //일정
	/**
	 * 직원의 행동로그가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String LOGPATH = ".\\src\\data\\log.csv";				  //로그
	/**
	 * 분실물 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String LOSTARTICLEPATH = ".\\src\\data\\lostarticle.csv"; //분실물
	/**
	 * 역별 시간표가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String STATIONTIMEPATH = ".\\src\\data\\stationtime.csv"; //역별 시간표
	/**
	 * 혼잡도 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String BUSYPATH = ".\\src\\data\\busy.csv";				  //혼잡도
	/**
	 * 민원 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String USERVOICEPATH = ".\\src\\data\\uservoice.csv";	  //민원
	/**
	 * 역 이름이 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String STATIONNAMEPATH = ".\\src\\data\\stationname.csv"; //역이름
	/**
	 * 역간 소요시간이 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String REQUIREDTIMEPATH = ".\\src\\data\\requiredTime.csv";//역간 소요시간
	/**
	 * 즐겨찾기 목록이 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String BOOKMARKPATH = ".\\src\\data\\bookmark.csv";        //즐겨찾기목록
	
	/**
	 * 모든 역의 이름이 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected final String ALL_LINE_NAME_PATH = ".\\src\\data\\호선별역이름\\모든역이름.csv";
	/**
	 * 각각의 호선별 역 이름이 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected static String line_NamePath = ".\\src\\data\\호선별역이름\\";
	/**
	 * 월간 역별 승하차 인원 정보가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected static String passengerCountPath = ".\\src\\data\\월간_역별_승하차_인원_정보\\";
	
	/**
	 * 호선별 역 시간표가 저장되어 있는 파일의 경로를 저장한 변수
	 */
	protected static String station_TimeTablePath = ".\\src\\data\\지하철호선별_역시간표\\";
	
	//End of 경로
	
	//cvs파일을 읽어만 오고 쓰지는 않는 리스트
	/**
	 * 역의 시간표 객체를 저장한 ArrayList
	 */
	public static ArrayList<StationTime> stationTimeList = new ArrayList<>();
	
	/**
	 * 호선별로 승객수를 저장한 ArrayList
	 */
	public static ArrayList<PassengerCounting> passengerCountingList = new ArrayList<>();
	
	// csv파일을 읽어와서 변경되지 않는 역 리스트
	/**
	 * 모든 역의 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> ALL_STATION_NAME = new ArrayList<>();
	/**
	 * 1호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE1_STATION_NAME = new ArrayList<>();
	/**
	 * 2호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE2_STATION_NAME = new ArrayList<>();
	/**
	 * 3호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE3_STATION_NAME = new ArrayList<>();
	/**
	 * 4호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE4_STATION_NAME = new ArrayList<>();
	/**
	 * 5호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE5_STATION_NAME = new ArrayList<>();
	/**
	 * 6호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE6_STATION_NAME = new ArrayList<>();
	/**
	 * 7호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE7_STATION_NAME = new ArrayList<>();
	/**
	 * 8호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE8_STATION_NAME = new ArrayList<>();
	/**
	 * 9호선의 역 이름이 저장된 ArrayList
	 */
	public static final ArrayList<String> LINE9_STATION_NAME = new ArrayList<>();
	
	//역간 걸리는 시간 저장하는 리스트
	/**
	 * 역간 소요시간을 저장하는 ArrayList
	 */
	public static final ArrayList<RequiredTime> requiredTimeList = new ArrayList<>();
	
	// csv파일 읽어와서 변경되면 종료시 csv파일에 쓰는 리스트
	/**
	 * 파일 쓰기때 사용되는 정기권 코드가 저장된 ArrayList
	 */
	public static ArrayList<String> passList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 고객 정보가 들어있는 ArrayList
	 */
	public static ArrayList<User> userList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 직원 정보가 들어있는 ArrayList
	 */
	public static ArrayList<Employee> employeeList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 일정 정보가 들어있는 ArrayList
	 */
	public static List<Schedule> scheduleList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 행동 로그가 들어있는 ArrayList
	 */
	public static ArrayList<Log> logList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 민원 정보가 들어있는 ArrayList
	 */
	public static ArrayList<UserVoice> userVoiceList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 분실물 정보가 들어있는 ArrayList
	 */
	public static ArrayList<LostArticle> lostArticleList = new ArrayList<>();
	/**
	 * 파일 쓰기때 사용되는 북마크 정보가 들어있는 ArrayList
	 */
	public static ArrayList<BookMark> bookMarkList = new ArrayList<>();
	
	//혼잡도 저장하는 리스트
	/**
	 * 혼잡도 정보를 저장하는 ArrayList
	 */
	public static ArrayList<Busy> busyList = new ArrayList<>();
	
	// 직원 가입 코드
	/**
	 * 직원 가입 코드를 저장하는 변수
	 */
	public final static String EMPLOYEECODE = "SEOULMETRO";	// 예시
	
	
}//End of class
