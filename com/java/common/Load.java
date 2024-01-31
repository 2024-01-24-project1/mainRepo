package mainRepo.com.java.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import mainRepo.com.java.calendar.Calendar;
import mainRepo.com.java.common.log.Log;
import mainRepo.com.java.common.lostarticle.LostArticle;
import mainRepo.com.java.member.employee.Employee;
import mainRepo.com.java.member.user.User;
import mainRepo.com.java.member.user.UserVoice;

public class Load {
	Data data = new Data();
	
	public void loadAll() {
		loadUserList();
		loadEmployeeList();
		loadPassList();
		loadCalendarList();
		loadLogList();
		loadUserVoiceList();
		loadLostArticleList();
	}
	
	private void loadLostArticleList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.lostArticlePath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				
				LostArticle article = new LostArticle(lineArr[0], lineArr[1], lineArr[2], lineArr[3]);
				
				Data.lostArticleList.add(article);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("분실물 리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadLostArticleList()
	
	private void loadUserVoiceList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.userVoicePath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				
				UserVoice voice = new UserVoice(lineArr[0], lineArr[1], lineArr[2], lineArr[3]);
				
				Data.userVoiceList.add(voice);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("민원 리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadUserVoiceList()
	
	private void loadLogList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.logPath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				ArrayList<String> actArr = new ArrayList<>();
				
				for(int i = 2; i < lineArr.length; i++) {
					actArr.add(lineArr[i]);
				}
				
				Log log = new Log(lineArr[0], lineArr[1], actArr);
				
				Data.logList.add(log);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("로그리스트 로딩 실패");
			e.printStackTrace();
		}
		
	}//End of loadLogList()
	
	private void loadUserList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.userPath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				User user = new User(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5]);
				Data.userList.add(user);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("유저리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadUserList()
	
	private void loadEmployeeList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.employeePath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				Employee employee = new Employee(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5]
												, lineArr[6], lineArr[7], lineArr[8]);
				Data.employeeList.add(employee);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("직원리스트 로딩 실패");
			e.printStackTrace();
		}
	}//End of loadEmployeeList()
	
	private void loadPassList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.passPath));
			
			while((line = reader.readLine()) != null) {
				Data.passList.add(line);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("정기권 코드 로드 실패");
			e.printStackTrace();
		}
	}//End of loadPass()
	
	private void loadCalendarList() {
		String line = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(data.calendarPath));
			
			while((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				Calendar calendar = new Calendar(lineArr[0], lineArr[1], lineArr[2], lineArr[3]);
				
				Data.calendarList.add(calendar);
				
			}
			
			reader.close();
			return;
			
		}catch(Exception e){
			System.out.println("캘린더 일정 로드 실패");
			e.printStackTrace();
		}
	}//End of loadCalendarList()
	
}//End of Load
