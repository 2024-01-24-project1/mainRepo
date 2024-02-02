package com.java.member.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ActionLog2 {
    private static ArrayList<String> actionList = new ArrayList<>();

    public static void main(String[] args) {
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
       

        ArrayList<String> userList = readUserFile("dat\\employee.txt");

        actionLog(actionList, userList, 0);
        
        actionLog(1);
    }

    private static ArrayList<String> readUserFile(String fileName) {
        ArrayList<String> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                userList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static void actionLog(ArrayList<String> actionList, ArrayList<String> userList, int index) {
        if (index >= 0 && index < actionList.size() && index < userList.size()) {
            String action = actionList.get(index);
            String userInfo = userList.get(index);
//            System.out.println("Action: " + action);
//            System.out.println("User Info: " + userInfo);
            
            System.out.println(userInfo+action);
        } else {
            return;
        }
    }
}

