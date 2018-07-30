package com.mushuijie.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static String chDate2String(Date date){
		String format = "yyyy-mm-dd";
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format); 
			return sdf.format(date);
		}
		return null; 		
	}
}
