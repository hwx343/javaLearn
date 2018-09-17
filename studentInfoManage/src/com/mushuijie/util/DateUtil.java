package com.mushuijie.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static String chDate2String(Date date){
		String format = "yyyy-MM-dd";
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format); 
			String result=sdf.format(date);
//			System.out.println(result);
			return result;
		}
		return null; 		
	}
}
