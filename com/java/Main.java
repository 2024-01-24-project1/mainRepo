package mainRepo.com.java;


import mainRepo.com.java.common.Exit;
import mainRepo.com.java.common.Load;



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
		
//		Data.lostArticleList.stream()
//					 .forEach(article -> System.out.println(article));
		
		
		//종료시 파일 덮어쓰기할 클래스
		exit.writeAll();
		
		System.exit(0);

	}//main

}
