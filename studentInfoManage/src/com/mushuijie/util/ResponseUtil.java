package com.mushuijie.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {
	public static void write(HttpServletResponse response,Object jsonObject){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(jsonObject.toString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
}
