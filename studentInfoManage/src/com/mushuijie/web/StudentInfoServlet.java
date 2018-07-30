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
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.ResponseUtil;

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
		// TODO Auto-generated method stub
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		String stuName=request.getParameter("stuName");
		if(stuName==null){
			stuName="";
		}
		PageBean pg=new PageBean(page,rows);
		JSONObject result = new JSONObject();
		Connection conn=db.openConnection();
		JSONArray jarr=this.stuDao.listStudentInfo(conn,pg);
		int total=stuDao.getStudentCount(conn, stuName);
		result.put("rows", jarr);
		result.put("total", total);
		ResponseUtil.write(response, result);
		db.closeConnection(conn);		
	}
	
}
