package com.java.busy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.java.common.Data;

public class BusyReader {
	
	
	final static String BUSYPATH = ".\\dat\\20221231.csv";
	
	
	public static void loadBusy() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(BUSYPATH));
			
			String line = "";
			
			while((line=reader.readLine())!=null ) {
				
				String[] temp = line.split(",");
				
				ArrayList<Double> list = new ArrayList<>();
				
				double avg = 0;
				double sum = 0;
				
				if(temp[6].contains("분")) temp[6] = "0";
				if(temp[6].equals("")) temp[6] = "0";
				
				list.add(Double.parseDouble(temp[6]));
				
				
				for(int i=7; i<=43; i+=2) {
					
					if(temp[i].equals("")) {
						temp[i]="0";
					}
					if(temp[i+1].equals("")) {
						temp[i+1]="0";
					}
					
					if(temp[i].contains("분") || temp[i+1].contains("분")) {
						temp[i]=temp[i+1]="0";
					}
					
					double n1 = Double.parseDouble(temp[i]);
					double n2 = Double.parseDouble(temp[i+1]);
					
					sum = n1 + n2;
					avg = sum/2;
					list.add(avg);
					
				}
				
				
				Busy busy = new Busy(temp[1],temp[2],temp[4],temp[5],list);
				Data.busyList.add(busy); 
				
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("BusyReader");
			e.printStackTrace();
		}
		
		
	}

}
