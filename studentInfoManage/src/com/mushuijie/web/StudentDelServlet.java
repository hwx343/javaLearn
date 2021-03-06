package com.mushuijie.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mushuijie.dao.StudentInfoDao;
import com.mushuijie.impl.StudentInfoDaoImpl;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentDelServlet extends HttpServlet{

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stuId=request.getParameter("stuId");
		StudentInfoDao stdao=new StudentInfoDaoImpl();
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		int delNums=stdao.studentDelete(conn, stuId);
		JSONObject result = new JSONObject();
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
