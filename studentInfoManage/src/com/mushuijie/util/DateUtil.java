package com.mushuijie.util;

import java.sql.Date;
import java.text.ParseException;
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
	
	public static Date chString2Date(String dateStr){
		String format = "yyyy-MM-dd";
		if(!StringUtil.isEmpty(dateStr)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date result = null;
			try {
				result=new java.sql.Date(sdf.parse(dateStr).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		return null;
		
	}
}
