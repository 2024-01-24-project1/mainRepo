package com.java.member.employee;

import java.util.ArrayList;
import java.util.Scanner;

import com.java.common.Data;
import com.java.common.Validation;
import com.java.view.View;

public class EmployeeSearch {
	
	public static void employeeAll(ArrayList<Employee> list) {
		
		String sel = "";
		
		Scanner scan = new Scanner(System.in);
		
		employeePage(list);
		
		System.out.println("특정 호선, 이름, 직원이름으로 검색하시려면 1");
		System.out.println("아니면 엔터");
		sel = scan.nextLine();
		
		if(sel.equals("1")) {
			
			// 특정 단어로 찾는 메서드 호출
			employeeSearch(list);
			
		}
		
		System.out.println("직원검색 종료");
		View.pause();
		
	}//End of employeeAll()
	
	public static void employeeSearch(ArrayList<Employee> employeeList) {
		
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
			String search = "";
			
			ArrayList<Employee> searchlist = new ArrayList<>();
			
			System.out.println("특정 호선(숫자만), 이름, 직원이름");
			System.out.print("찾기: ");
			search = scan.nextLine();
			
			final String SEARCH = search;
			
			boolean check = employeeList.stream().anyMatch(employee -> employee.getName().equals(SEARCH)
																	|| employee.getLine().contains(SEARCH)
																	|| employee.getStation().contains(SEARCH));
			if(check) {
				
				for(Employee employee : employeeList) {
					
					if(employee.getLine().contains(SEARCH) || employee.getName().equals(SEARCH) || employee.getStation().contains(SEARCH)) {
						searchlist.add(employee);
					}
					
				}
				
				employeePage(searchlist);
				
			}else {
				System.out.println("조건에 해당하는 직원이 존재하지 않습니다.");
			}
			
			System.out.println("다른 조건을 검색하시려면 엔터");
			System.out.println("직원검색을 종료하시면 back입력");
			System.out.printf("입력: ");
			search = scan.nextLine();
			
			if(search.equals("back")) {
				
				// 특정 조건 직원검색 종료
				break;
			}
			
		}//while루프 종료
		
		
		
	}//End of employeeSearch()
	
	
	public static void employeePage(ArrayList<Employee> list) {
		// 리스트의 페이지수 계산
				int page = (int)(Math.ceil((double)list.size() / 10));
				
				int index = 0;		// 문자로 입력받은 숫자를 int로 변환
				
				Scanner scan = new Scanner(System.in);
				
				while(true) {
					
					String sel = "";	// 입력받는 문자열
					
					// View클래스 출력
					System.out.println("======================================================");
					System.out.println("                  직원 리스트");
					System.out.println("======================================================");
					
					list.stream().skip(index * 10)
					 							 .limit(10)
					 							 .forEach(employee -> System.out.printf("%-8s|%-20s|%-2s|%-3s - %-10s|%-13s\r\n"
							 													, employee.getName()
							 													, employee.getId()
							 													, employee.getPosition()
							 													, employee.getLine() + "호선"
							 													, employee.getStation() + "역"
							 													, employee.getPhone()));
					// 이름, ID, 전화번호, 직급, 호선, 역이름
					System.out.printf("Page| %s / %s\r\n", index + 1, page);
					System.out.print("back입력시 뒤로갑니다.");
					System.out.print("원하는 페이지: ");
					sel = scan.nextLine();
					
					if(sel.equals("back")) {
						break;
					}else if (Validation.is_NumString(sel)) {
						index = Integer.parseInt(sel) - 1;
						
						if(index < 0 || index >= page) {
							System.out.println("페이지 범위를 벗어났습니다.");
							System.out.println("다시 입력해주세요.");
							index = 0;
							
						}
						
					}else {
						System.out.println("잘못된 입력입니다.");
						System.out.println("다시 입력해주세요.");
					}
					
				}//while루프 종료
		
	}//End of lostArticlePage()
	
	
}//End of class
