package com.mushuijie.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mushuijie.dao.ClassInfoDao;
import com.mushuijie.impl.ClassInfoDaoImpl;
import com.mushuijie.model.ClassBean;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.ResponseUtil;

import net.sf.json.JSONObject;

public class ClassSaveServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		String classID=request.getParameter("id");
		String className=request.getParameter("className");
		String classDesc=request.getParameter("classDesc");
		ClassBean clb=null;
		if(classID!=null&&(!(classID.trim().equals("")))){
			clb=new ClassBean();
			clb.setId(Integer.parseInt(classID));
			clb.setClassName(className);
			clb.setClassDesc(classDesc);
		}
		ClassInfoDao cld=new ClassInfoDaoImpl();
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		int nums=0;
		if(classID==null||classID.trim().equals("")){
			nums=cld.classAdd(conn,className,classDesc);
		}else{
			nums=cld.classModify(conn, clb);
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
