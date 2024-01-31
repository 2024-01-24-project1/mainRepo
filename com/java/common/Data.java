package mainRepo.com.java.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import mainRepo.com.java.calendar.Calendar;
import mainRepo.com.java.member.employee.Employee;
import mainRepo.com.java.member.user.User;

public class Data {

	private final String passPath = ".\\src\\mainRepo\\data\\pass.csv";			//정기권
	private final String userPath = ".\\src\\mainRepo\\data\\user.csv";			//고객정보
	private final String employeePath = ".\\src\\mainRepo\\data\\employee.csv";	//직원정보
	private final String calendarPath = ".\\src\\mainRepo\\data\\calendar.csv";	//일정
	private final String logPath = "\\data\\log.csv";							//로그
	private final String lostArticlePath = "\\data\\lostarticle.csv";			//분실물
	private final String stationTimePath = "\\data\\stationtime.csv";			//역별 시간표
	private final String busyPath = "\\data\\busy.csv";							//혼잡도
	private final String useVoicePath = "\\data\\uservoice.csv";				//민원
	private final String stationNamePath = "\\data\\stationname.csv";   		//역이름
	
	public static ArrayList<String> passList = new ArrayList<>();
	public static ArrayList<User> userList = new ArrayList<>();
	public static ArrayList<Employee> employeeList = new ArrayList<>();
	public static ArrayList<Calendar> calendarList = new ArrayList<>();
	
	public void loadUserList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(userPath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				User user = new User(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5]);
				userList.add(user);
				
			}
			
		}catch(Exception e){
			System.out.println("유저리스트 로딩 실패");
		}
	}//End of loadUserList()
	
	public void loadEmployeeList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(employeePath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				Employee employee = new Employee(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5]
												, lineArr[6], lineArr[7], lineArr[8]);
				Data.employeeList.add(employee);
				
			}
			
			return;
			
		}catch(Exception e){
			System.out.println("직원리스트 로딩 실패");
		}
	}//End of loadEmployeeList()
	
	public void loadPassList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(passPath));
			
			while((line = reader.readLine()) != null) {
				Data.passList.add(line);
				
			}
			
			return;
			
		}catch(Exception e){
			System.out.println("정기권 코드 로드 실패");
		}
	}//End of loadPass()
	
	public void loadCalendarList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(calendarPath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				Calendar calendar = new Calendar(lineArr[0], lineArr[1], lineArr[2], lineArr[3]);
				
				Data.calendarList.add(calendar);
				
			}
			
			return;
			
		}catch(Exception e){
			System.out.println("캘린더 일정 로드 실패");
		}
	}//End of CalendarList()
	
	
}//End of class
