package mainRepo.com.java;


import mainRepo.com.java.calendar.Calendar;
import mainRepo.com.java.common.Data;
import mainRepo.com.java.common.Exit;
import mainRepo.com.java.common.Load;
import mainRepo.com.java.member.user.User;



public class Main {
	
	public static void main(String[] args) {
		
		Load load = new Load();
		Exit exit = new Exit();
		
		// 데이터 로드
		load.loadAll();
		
		
//		Scanner sc = new Scanner(System.in);
//		boolean loop= true;
//		// 수정
//		
//		while(loop) {
//			
//			
////			View.page1(sel);
//			
//			String sel = sc.nextLine();
//			
//			
//			if(sel.equals("1")) {
//				
//				//로그인
//				
//			}else if(sel.equals("2")) {
//				
//				//회원가입
//				
//			}else if(sel.equals("3")) {
//				
//				//IDPW 찾기
//				
//			}else if(sel.equals("4")) {
//				
//				//종료
//				
//			}else {
//				
//				//다시입력
//				
//			}
//			
//			
//			
//			
//			
//		}
		Data.userList.stream()
							.forEach(article -> System.out.println(article) );
		Data.employeeList.stream()
							.forEach(article -> System.out.println(article) );
		Data.calendarList.stream()
							.forEach(article -> System.out.println(article) );
		Data.userVoiceList.stream()
							.forEach(article -> System.out.println(article) );
		Data.passList.stream()
							.forEach(article -> System.out.println(article) );
		Data.logList.stream()
							.forEach(article -> System.out.println(article) );
		Data.lostArticleList.stream()
							.forEach(article -> System.out.println(article) );
		
		Data.userVoiceList.stream()
							.forEach(n -> System.out.println(n));
		
		
		//종료시 파일 덮어쓰기할 클래스
		exit.writeAll();
		
		System.exit(0);

	}//main

}
