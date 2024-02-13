package com.java.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;

import com.java.common.log.Log;
import com.java.common.lostarticle.LostArticle;
import com.java.member.employee.Employee;
import com.java.member.user.BookMark;
import com.java.member.user.User;
import com.java.member.user.UserVoice;
import com.java.schedule.Schedule;

/**
 * 파일 쓰기를 하는 클래스
 */
public final class Exit {

	/**
	 * 경로가 저장되어 있는 Data 인스턴스
	 */
	Data data = new Data();
	
	// 모든 csv파일 쓰기
	/**
	 * 모든 파일을 쓰는 역할을 하는 메서드
	 */
	public void writeAll() {
		writeUserList();
		writeEmployeeList();
		writePassList();
		writeScheduleList();
		writeLogList();
		writeUserVoiceList();
		writeLostArticleList();
		writeBookMarkList();
		
	}
	
	/**
	 * 북마크 정보를 파일에 쓰는 메서드
	 */
	private void writeBookMarkList() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.BOOKMARKPATH, Charset.forName("UTF-8") ) );
			
			for(BookMark bookMark : Data.bookMarkList) {
				writer.write(bookMark.getId());
				for(String route : bookMark.getBookMarkList()) {
					writer.write(","+route);
				}
				writer.newLine();
				
			}
			writer.close();
			return;
			
			
		} catch (Exception e) {
			System.out.println("Exit.writeBookMarkList()");
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * 분실물 정보를 파일에 쓰는 메서드
	 */
	private void writeLostArticleList() {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.LOSTARTICLEPATH, Charset.forName("UTF-8") ) );
			
			for(LostArticle article : Data.lostArticleList) {
				writer.write(article.getArticle() + ",");
				writer.write(article.getContent() + ",");
				writer.write(article.getLostStation() + ",");
				writer.write(article.getFindStation() + "\r\n");
				
			}
			
			writer.close();
			return;
			
		} catch (Exception e) {
			System.out.println("분실물 리스트 쓰기 실패");
			e.printStackTrace();
		}
		
	}//End of writeLostArticleList()
	
	/**
	 * 민원 정보를 파일에 쓰는 메서드
	 */
	private void writeUserVoiceList() {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.USERVOICEPATH, Charset.forName("UTF-8") ) );
			
			for(UserVoice voice : Data.userVoiceList) {
				writer.write(voice.getId() + ",");
				writer.write(voice.getTitle() + ",");
				writer.write(voice.getContent() + ",");
				writer.write(voice.getIsRead() + "\r\n");
				
			}
			
			writer.close();
			return;
			
		} catch (Exception e) {
			System.out.println("민원 리스트 쓰기 실패");
			e.printStackTrace();
		}
		
		
	}//End of writeUserVoiceList()
	
	
	/**
	 * 행동 로그 정보를 파일에 쓰는 메서드
	 */
	private void writeLogList() {
		try {
			
			Collections.sort(Data.logList, Comparator.comparing(Log::getTime));
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.LOGPATH, Charset.forName("UTF-8") ) );
			
			for(Log log : Data.logList) {
				writer.write(log.getTime() + ",");
				writer.write(log.getId() );
					
					for(String action : log.getAction()) {
						writer.write("," + action);
					}
				writer.write("\r\n");
				
			}
			
			writer.close();
			return;
			
		} catch (Exception e) {
			System.out.println("로그 리스트 쓰기 실패");
			e.printStackTrace();
		}
	}//End of writeCalendarList()
	
	
	/**
	 * 일정 정보를 파일에 쓰는 메서드
	 */
	private void writeScheduleList() {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.SCHEDULEPATH, Charset.forName("UTF-8") ) );
			
			for(Schedule schedule : Data.scheduleList) {
				writer.write(schedule.getTime() + ",");
				writer.write(schedule.getStation() + ",");
				writer.write(schedule.getSchedule() + "\r\n");
				
			}
			
			writer.close();
			return;
			
		} catch (Exception e) {
			System.out.println("일정 리스트 쓰기 실패");
			e.printStackTrace();
		}
	}//End of writeScheduleList()

	/**
	 * 정기권 정보를 파일에 쓰는 메서드
	 */
	private void writePassList() {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.PASSPATH, Charset.forName("UTF-8") ) );
		
			for(String pass : Data.passList) {
				writer.write(pass + "\r\n");
			
			}
		
			writer.close();
			return;
		
		} catch (Exception e) {
			System.out.println("정기권 코드 리스트 쓰기 실패");
			e.printStackTrace();
		}
	}//End of writePassList()

	/**
	 * 직원 정보를 파일에 쓰는 메서드
	 */
	private void writeEmployeeList() {
		try {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter
									(data.EMPLOYEEPATH, Charset.forName("UTF-8") ) );
		
		for(Employee employee : Data.employeeList) {
			writer.write(employee.getName() + ",");
			writer.write(employee.getId() + ",");
			writer.write(employee.getPw() + ",");
			writer.write(employee.getRegistration() + ",");
			writer.write(employee.getPhone() + ",");
			writer.write(employee.getPosition() + ",");
			writer.write(employee.getLine() + ",");
			writer.write(employee.getStation() + ",");
			writer.write(employee.getLevel() + "\r\n");
			
		}
		
		writer.close();
		return;
		
		} catch (Exception e) {
			System.out.println("직원 리스트 쓰기 실패");
			e.printStackTrace();
		}
	}//End of writeEmployeeList()

	/**
	 * 고객 정보를 파일에 쓰는 메서드
	 */
	private void writeUserList() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter
										(data.USERPATH, Charset.forName("UTF-8") ) );
			
			for(User user : Data.userList) {
				writer.write(user.getName() + ",");
				writer.write(user.getId() + ",");
				writer.write(user.getPw() + ",");
				writer.write(user.getRegistration() + ",");
				writer.write(user.getPhone() + ",");
				writer.write(user.getPassCheck() + ",");
				writer.write(user.getPassExpiry()+"\r\n");
				
			}
			
			writer.close();
			return;
			
		} catch (Exception e) {
			System.out.println("유저 리스트 쓰기 실패");
			e.printStackTrace();
		}
	}//End of writeUserList()
	
	
}//End of class
