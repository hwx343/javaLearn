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
import com.mushuijie.model.PageBean;
import com.mushuijie.model.StudentBean;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.ResponseUtil;
import com.mushuijie.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentInfoServlet extends HttpServlet{
	StudentInfoDao stuDao=new StudentInfoDaoImpl();
	DBUtil db=new DBUtil();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String sex=request.getParameter("sex");
		String begbirthday=request.getParameter("begbirthday");
		String endbirthday=request.getParameter("endbirthday");
		String gradeId=request.getParameter("gradeId");
		StudentBean student=null;
//		if(!StringUtil.isEmpty(stuNo)){
			student=new StudentBean();
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			student.setSex(sex);
			if(!StringUtil.isEmpty(gradeId)){
				student.setGradeId(Integer.parseInt(gradeId));	
			}					
//		}
		
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		PageBean pg=new PageBean(page,rows);
		JSONObject result = new JSONObject();
		Connection conn=db.openConnection();
		JSONArray jarr=this.stuDao.listStudentInfo(conn,pg,student,begbirthday,endbirthday);
		int total=stuDao.getStudentCount(conn,pg,student,begbirthday,endbirthday);
		result.put("rows", jarr);
		result.put("total", total);
		ResponseUtil.write(response, result);
		db.closeConnection(conn);		
	}
	
}
