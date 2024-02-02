package com.java.member.employee;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ActionLog {
	private static ArrayList<String> actionList = new ArrayList<>();
	
	public static void main(String[] args) {
		
//		ArrayList<String> userList = new ArrayList<String>();

        // 목록 추가
		actionList.add("역관리-의자 없는 열차 추가");
		actionList.add("역관리-일반 열차 추가");
		actionList.add("직원 관리-전체 직원 정보 보기-호선으로 직원 검색");
        actionList.add("직원 관리-전체 직원 정보 보기-역 이름으로 직원 검색");
        actionList.add("직원 관리-전체 직원 정보 보기-이름으로 직원 검색");
        actionList.add("직원 관리-직원 정보 수정-근무지 변경");
        actionList.add("직원 관리-직원 정보 수정-직급 변경");
        actionList.add("직원 관리-직원 정보 수정-직원 삭제");
        actionList.add("안전 요원 관리-전체 안전 요원 보기-호선으로 안전 요원 검색");
        actionList.add("안전 요원 관리-전체 안전 요원 보기-역 이름으로 안전 요원 검색");
        actionList.add("안전 요원 관리-안전 요원 배치");
        actionList.add("안전 요원 관리-안전 요원 부서 해제");
        actionList.add("고객의 소리-전체 분실물 보기-분실물 검색");
        actionList.add("고객의 소리-전체 분실물 보기-분실물 추가/제거");
        actionList.add("고객의 소리-전체 민원 보기");
        actionList.add("행사 캘린더-전체 일정 보기");
        actionList.add("행사 캘린더-일정 추가");
        actionList.add("행사 캘린더-일정 제거");
        actionList.add("통계 정보-혼잡도 통계");
        actionList.add("통계 정보-이용객 통계");
        actionList.add("통계 정보-매출 통계");
        actionList.add("개인 정보-개인 정보 확인");
        actionList.add("개인 정보-개인 정보 수정");
        actionList.add("행동 로그 보기");
        
        ArrayList<String> employeeList = readUserFile("dat\\employee.txt");
        
        
        로그인id.
        캘린더날짜 2024-00-00 이름 형식
        행동 로그 형식 만들? 클래스 만들기
        		
        로그인할때 유저리스트에 있는지 확인 > 맞으면 stream에 아이디를 넣고
        동일한 arraylist에 string으로 쭉쭉 드가면 로그 찍힐 때마다 ..흠 ..
        
       
       
  


		
	private static void actionLog(int index) {
		if(index>=0&&index<actionList.size()) {
			String action=actionList.get(index);
			System.out.println(action);
		}else {
			return;
		}
		
	}
		
        
	//index를 입력하면 그 위치의 문장이 출력되는... 형태여야하는데.. (그게 또 옆으로 쌓여야되는데..)
		
		
	}


