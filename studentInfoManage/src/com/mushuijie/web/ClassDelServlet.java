package com.mushuijie.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mushuijie.dao.ClassInfoDao;
import com.mushuijie.dao.StudentInfoDao;
import com.mushuijie.impl.ClassInfoDaoImpl;
import com.mushuijie.impl.StudentInfoDaoImpl;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassDelServlet extends HttpServlet{

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String classID=request.getParameter("classID");
		ClassInfoDao cld=new ClassInfoDaoImpl();
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		JSONObject result = new JSONObject();
		
		StudentInfoDao stdd=new StudentInfoDaoImpl();
		String[] delId=classID.split(",");
		for(int i=0;i<delId.length;i++){
			String str=delId[i];
			if(stdd.getStudentBygradeId(conn, str)){
				result.put("errorIndex", i);
				result.put("errormsg", "班级还有未删除的学生！");
				ResponseUtil.write(response, result);
				db.closeConnection(conn);
				return;
			}
		}
		int delNums=cld.classDelete(conn, classID);
		if(delNums>0){
			result.put("success", "true");
			result.put("delNums", delNums);			
		}else{
			result.put("errormsg", "delete failed");
		}
		ResponseUtil.write(response, result);
		db.closeConnection(conn);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doDelete(request, response);
	}

}
