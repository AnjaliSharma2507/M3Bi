package com.m3bi.hotelbooking.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
	
	public static String getCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now); 
	}
	
	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDate now = LocalDate.now();  
		return dtf.format(now); 
	}
	
	public static String getDateAfterNumberDays(int days) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDate now = LocalDate.now().plusDays(days); 
		return dtf.format(now); 
	}

}
