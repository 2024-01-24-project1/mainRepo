package mainRepo.com.java.common;

import java.util.ArrayList;

import mainRepo.com.java.calendar.Calendar;
import mainRepo.com.java.common.log.Log;
import mainRepo.com.java.common.lostarticle.LostArticle;
import mainRepo.com.java.member.employee.Employee;
import mainRepo.com.java.member.user.User;
import mainRepo.com.java.member.user.UserVoice;

public class Data {
	
	// 경로
	protected final String passPath = ".\\src\\mainRepo\\data\\pass.csv";				//정기권
	protected final String userPath = ".\\src\\mainRepo\\data\\user.csv";				//고객정보
	protected final String employeePath = ".\\src\\mainRepo\\data\\employee.csv";		//직원정보
	protected final String calendarPath = ".\\src\\mainRepo\\data\\calendar.csv";		//일정
	protected final String logPath = ".\\src\\mainRepo\\data\\log.csv";					//로그
	protected final String lostArticlePath = ".\\src\\mainRepo\\data\\lostarticle.csv";	//분실물
	protected final String stationTimePath = ".\\src\\mainRepo\\data\\stationtime.csv";	//역별 시간표
	protected final String busyPath = ".\\src\\mainRepo\\data\\busy.csv";				//혼잡도
	protected final String userVoicePath = ".\\src\\mainRepo\\data\\uservoice.csv";		//민원
	protected final String stationNamePath = ".\\src\\mainRepo\\data\\stationname.csv"; //역이름
	
	
	public static ArrayList<String> passList = new ArrayList<>();
	public static ArrayList<User> userList = new ArrayList<>();
	public static ArrayList<Employee> employeeList = new ArrayList<>();
	public static ArrayList<Calendar> calendarList = new ArrayList<>();
	public static ArrayList<Log> logList = new ArrayList<>();
	public static ArrayList<UserVoice> userVoiceList = new ArrayList<>();
	public static ArrayList<LostArticle> lostArticleList = new ArrayList<>();
	
	// 모든 역이름
	public final String[] stationNameList = { "석계", "신이문", "외대앞", "회기", "청량리", "제기동", "신설동", "동묘앞"
			, "동대문", "종로5가", "종로3가", "종각", "시청", "서울역", "남영", "용산", "노량진", "대방", "신길", "영등포"
			, "신도림", "구로", "가산디지털단지", "독산", "금천구청", "석수", "관악", "안양", "명학", "성수", "뚝섬", "한양대"
			, "왕십리", "상왕십리", "신당", "동대문역사문화공원", "을지로4가", "을지로3가", "을지로입구", "시청", "충정로", "아현"
			, "이대", "신촌", "홍대입구", "합정", "당산", "영등포구청", "문래", "신도림", "대림", "구로디지털단지", "신대방", "신림"
			, "봉천", "서울대입구", "낙성대", "사당", "방배", "서초", "교대", "강남", "역삼", "선릉", "삼성", "종합운동장"
			, "잠실새내", "잠실", "잠실나루", "강변", "구의", "건대입구", "용답", "신답", "용두", "신설동", "도림천", "양천구청"
			, "신정네거리", "까치산", "안국", "종로3가", "을지로3가", "충무로", "동대입구", "약수", "금호", "옥수", "압구정", "신사", "잠원"
			, "고속터미널", "교대", "남부터미널", "양재", "매봉", "도곡", "대치", "학여울", "대청", "일원", "수서"
			, "가락시장", "혜화", "동대문", "동대문역사문화공원", "충무로", "명동", "회현", "서울역", "숙대입구", "삼각지", "신용산"
			, "이촌", "동작", "총신대입구", "사당", "남태령", "선바위", "양평", "영등포구청", "영등포시장", "신길", "여의도", "여의나루", "마포", "공덕", "애오개", "충정로", "서대문"
			, "광화문", "종로3가", "을지로4가", "동대문역사문화공원", "청구", "신금호", "행당", "왕십리", "마장", "답십리"
			, "장한평", "군자", "아차산", "광나루", "천호", "망원", "합정", "상수", "광흥창", "대흥", "공덕", "효창공원앞", "삼각지", "녹사평", "이태원", "한강진", "버티고개"
			, "약수", "청구", "신당", "동묘앞", "창신", "보문", "안암", "고려대", "월곡", "상월곡", "돌곶이", "석계", "태릉입구"
			, "가산디지털단지", "남구로", "대림", "신풍", "보라매", "신대방삼거리", "장승배기", "상도", "숭실대입구", "남성", "총신대입구"
			, "내방", "고속터미널", "반포", "논현", "학동", "강남구청", "청담", "뚝섬유원지", "건대입구", "어린이대공원", "군자", "중곡"
			, "용마산", "사가정", "면목", "상봉", "암사", "천호", "강동구청", "몽촌토성", "잠실", "석촌", "송파", "가락시장", "문정", "장지", "복정"
			, "선유도", "당산", "국회의사당", "여의도", "샛강", "노량진", "노들", "흑석", "동작", "구반포", "신반포", "고속터미널", "사평"
			, "신논현", "언주", "선정릉", "삼성중앙", "봉은사", "종합운동장", "삼전", "석촌고분", "석촌", "송파나루"}; 
	
}//End of class
