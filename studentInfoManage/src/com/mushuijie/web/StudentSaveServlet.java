package com.mushuijie.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mushuijie.dao.StudentInfoDao;
import com.mushuijie.impl.StudentInfoDaoImpl;
import com.mushuijie.model.StudentBean;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.DateUtil;
import com.mushuijie.util.ResponseUtil;
import com.mushuijie.util.StringUtil;

import net.sf.json.JSONObject;

public class StudentSaveServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String gradeId=request.getParameter("gradeId");
		String email=request.getParameter("email");
		String stuDesc=request.getParameter("stuDesc");
		String stuId=request.getParameter("stuId");
		StudentBean stdBean=new StudentBean(stuNo, stuName, sex, DateUtil.chString2Date(birthday), 
				Integer.parseInt(gradeId), email, stuDesc);
		if(!StringUtil.isEmpty(stuId)){
			stdBean.setStuId(Integer.parseInt(stuId));			
		}
		StudentInfoDao stdd=new StudentInfoDaoImpl();
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		int nums=0;
		if(StringUtil.isEmpty(stuId)){
			nums=stdd.studentAdd(conn, stdBean);
		}else{
			nums=stdd.studentModify(conn, stdBean);
		}
		
		JSONObject result = new JSONObject();
		if(nums>0){
			result.put("success", "true");
			result.put("addNums", nums);			
		}else{
			result.put("success", "true");
			result.put("errormsg", "failed");
		}
		ResponseUtil.write(response, result);
		db.closeConnection(conn);
	}
}
