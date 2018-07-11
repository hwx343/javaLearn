package com.mushuijie.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static JSONArray resultSet2Json(ResultSet rs){
		if(rs!=null){
			try {
				ResultSetMetaData rsmd =rs.getMetaData();
				JSONArray jarr=new JSONArray();
				while(rs.next()){
					int num=rsmd.getColumnCount();
					JSONObject job=new JSONObject();
					for(int i=1;i<=num;i++){
						job.put(rsmd.getColumnName(i), rs.getObject(i));
					}
					jarr.add(job);
				}
				return jarr;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
		
	}
}
