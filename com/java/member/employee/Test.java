package com.java.member.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static String getIdFromCSV(String filePath) {
        String idValue = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // CSV 파일의 구분자에 따라 변경

                // 가정: id가 0번 인덱스에 위치
                if (values.length > 0) {
                    idValue = values[0].trim();
                    break; // 첫 번째 행만 읽어오면서 id 값을 가져옴
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return idValue;
    }

    public static void main(String[] args) {
        // Replace "path/to/your/file.csv" with the actual path to your CSV file
        String filePath = "dat\\employee.txt";

        String idValue = getIdFromCSV(filePath);

        if (idValue != null) {
            System.out.println(idValue);
        } else {
            System.out.println("Error reading the CSV file or no ID found.");
        }
    }
}

