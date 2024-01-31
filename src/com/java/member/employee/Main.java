package com.java.member.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.java.common.Data;
import com.java.member.Member;

public class Main {
	
	public static void main(String[] args)throws IOException {
		
		
		String root = "dat\\직원목록.txt";
		boolean loop = true;
		Path path = Paths.get(root);
		
		List<String> line = Files.readAllLines(path);
		
//		파일 불러오기
		for(String s : line) {
			
			String[] temp = s.split(",");
			EmployeeManagement e = new EmployeeManagement(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8]);
			Data.employeeList.add(e);
			
		}
		
		System.out.println(Data.employeeList);
		//데이터 입력
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(loop) {



			System.out.println("1.직원검색 2.직원수정 3.안전요원 관리 4.종료");
			String sel = reader.readLine();



			//직원 검색
			if(sel.equals("1")) {

				System.out.println("1.호선으로 2.역이름으로 3.이름으로 4. 전체직원");
				sel = reader.readLine();

				if(sel.equals("1")) { //호선으로

					System.out.print("호선: ");
					String lines = reader.readLine();

					EmployeeInfo.searchEmployee(lines);


				}else if(sel.equals("2")) { //역이름으로

					System.out.print("역 이름: ");
					String station = reader.readLine();
					EmployeeInfo.searchEmployee(station);


				}else if(sel.equals("3")) { //이름으로

					System.out.print("이름: ");
					String name = reader.readLine();
					EmployeeInfo.searchEmployee(name);

				}else if(sel.equals("4")) { //전체 직원

					EmployeeInfo.printAllEmployee();

				}




			}else if(sel.equals("2")) { //직원 수정

				try {

					//관리자이면서 접근권한이 4이상인 관리자만 수정 가능

					if(Member.auth.equals("관리자") && (Member.level.equals("4") || Member.level.equals("5"))){



						System.out.print("수정 할 항목(근무지,직급,삭제):  ");
						String word = reader.readLine();



						// 수정할 항목은 나중에 숫자로 변경

						if(word.equals("근무지")) {

							System.out.print("수정할 직원 id : ");
							String id = reader.readLine();
							System.out.print("수정 할 호선 : ");
							String lines = reader.readLine();

							System.out.print("수정 할 근무지: ");
							String station = reader.readLine();

							EmployeeUpdate.changeStation(id, lines, station);


						}else if(word.equals("직급")) {

							System.out.print("수정할 직원 id : ");
							String id = reader.readLine();

							System.out.print("변경할 직급: ");
							String rank = reader.readLine();


							EmployeeUpdate.changeRank(id, rank);

						}else if(word.equals("삭제")) {

							System.out.print("삭제할 직원 id : ");
							String id = reader.readLine();

							EmployeeUpdate.removeEmployee(id);

						}else if(word.equals("검색")) {

						}


					}
				} catch (Exception e2) {
					System.out.println("Main.main");
					e2.printStackTrace();
				}

			}else if(sel.equals("3")) { //안전요원 관리

				System.out.print("1. 안전요원 목록 2. 안전요원 배치 3. 안전요원 부서 해제");
				sel = reader.readLine();

				if(sel.equals("1")) { //안전 요원 목록
					
					System.out.print("1. 전체 안전요원 2. 호선으로 검색 3. 역 이름으로 검색");
					sel = reader.readLine();
					
					if(sel.equals("1")) { //전체 안전요원
						
						EmployeeInfo.searchEmployee("안전요원");
						
					}else if(sel.equals("2")) { //호선으로 검색
						
						System.out.print("검색할 호선: ");
						sel = reader.readLine();
						
						EmployeeInfo.searchSecurityEmployee(sel);
						
					}else if(sel.equals("3")) { //역이름으로 검색
						
						System.out.print("검색할 역 이름: ");
						sel = reader.readLine();
						
						EmployeeInfo.searchSecurityEmployee(sel);
						
					}



				}else if(sel.equals("2")) { //안전요원 배치


					EmployeeInfo.searchEmployee("안전요원");

					Scanner scan = new Scanner(System.in);

					System.out.print("id 입력: ");
					String id = scan.nextLine();

					System.out.print("호선 입력: ");
					String lines = scan.nextLine();

					System.out.print("역 입력: ");
					String station = scan.nextLine();


					EmployeeUpdate.changeStation(id, lines, station);


				}else if(sel.equals("3")) { //안전요원 부서 해제

					EmployeeInfo.searchEmployee("안전요원");

					System.out.print("id 입력: ");
					String id = reader.readLine();



					EmployeeUpdate.changeStation(id, "미배치", "미배치");




				}




			}else if(sel.equals("4")) {
				System.out.println("프로그램을 종료합니다.");
				loop=false;
			}
		}


		BufferedWriter writer = new BufferedWriter(new FileWriter(root));


		//파일 저장 csv파일 형식
		for(EmployeeManagement e : Data.employeeList) {

			String str = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", e.getId(),e.getPw(),e.getName(),e.getIdNum(),e.getPhone(),e.getRank(),e.getLine(),e.getStation(),e.getLevel());
			writer.write(str);
			writer.newLine();


		}

		writer.close();
			
		
		
		

	}//main

}
