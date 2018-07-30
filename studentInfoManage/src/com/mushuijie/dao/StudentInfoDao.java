package com.mushuijie.dao;

import java.sql.Connection;

import com.mushuijie.model.PageBean;
import com.mushuijie.model.StudentBean;

import net.sf.json.JSONArray;

public interface StudentInfoDao {
	public JSONArray listStudentInfo(Connection conn,PageBean pg);
	public int getStudentCount(Connection conn,String stuName);
	public int studentDelete(Connection conn,String stuId);
	public int studentAdd(Connection conn,StudentBean stu);
	public int studentModify(Connection conn,StudentBean stu);
}
