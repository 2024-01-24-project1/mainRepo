package mainRepo.com.java.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import mainRepo.com.java.member.employee.Employee;
import mainRepo.com.java.member.user.User;

public class Data {

	private final String passPath = "\\data\\pass.csv";					//정기권
	private final String userPath = "\\data\\user.csv";					//고객정보
	private final String employeePath = "\\data\\employee.csv";			//직원정보
	private final String calendarPath = "\\data\\calendar.csv";			//일정
	private final String logPath = "\\data\\log.csv";					//로그
	private final String lostArticlePath = "\\data\\lostarticle.csv";	//분실물
	private final String stationTimePath = "\\data\\stationtime.csv";	//역별 시간표
	private final String busyPath = "\\data\\busy.csv";					//혼잡도
	private final String useVoicePath = "\\data\\uservoice.csv";		//민원
	private final String stationNamePath = "\\data\\stationname.csv";   //역이름
	
	try {
		BufferedReader reader = new BufferedReader(new FileReader(userPath));
		
		
		
	}catch(Exception e){
		System.out.println("에러");
	}
	
	
	
	public static ArrayList<String> passStrings;
	public static ArrayList<User> userList;
	public static ArrayList<Employee> employeeList;
	
	
	
	
	
	
	
	
	
}//End of class
