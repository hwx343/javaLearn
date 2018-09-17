package com.mushuijie.dao;

import java.sql.Connection;
import com.mushuijie.model.PageBean;
import com.mushuijie.model.StudentBean;

import net.sf.json.JSONArray;

public interface StudentInfoDao {
	public JSONArray listStudentInfo(Connection conn,PageBean pg,StudentBean student,
			String begbirthday,String endbirthday);
	public int getStudentCount(Connection conn, PageBean pg,StudentBean student,
			String begbirthday,String endbirthday);
	public int studentDelete(Connection conn,String stuId);
	public int studentAdd(Connection conn,StudentBean stu);
	public int studentModify(Connection conn,StudentBean stu);
	public boolean getStudentBygradeId(Connection conn,String gradeId);
}
