package com.udemy.helpdesk.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss"; 
	
	public static String dataAtualFormatado(String pattern) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.now().format(formatter);		
	}
}
