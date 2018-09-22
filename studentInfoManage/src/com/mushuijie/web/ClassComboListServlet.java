package com.mushuijie.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mushuijie.dao.ClassInfoDao;
import com.mushuijie.impl.ClassInfoDaoImpl;
import com.mushuijie.model.PageBean;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassComboListServlet extends HttpServlet{
	ClassInfoDao cld=new ClassInfoDaoImpl();
	DBUtil db=new DBUtil();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=db.openConnection();
		JSONArray jarr=this.cld.listClassInfo(conn,null,null);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("className", "«Î—°‘Ò...");
		jarr.add(0,jsonObject);	
		int total=cld.getClassCount(conn,null);
		ResponseUtil.write(response, jarr);
		db.closeConnection(conn);
		
		
	}
	
}
