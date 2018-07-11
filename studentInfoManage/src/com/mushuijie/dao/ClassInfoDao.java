package com.mushuijie.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.mushuijie.model.ClassBean;
import com.mushuijie.model.PageBean;

import net.sf.json.JSONArray;

public interface ClassInfoDao {
	public JSONArray listClassInfo(Connection conn,PageBean pg,String className);
	public int getClassCount(Connection conn,String className);
	public int classDelete(Connection conn,String classID);
	public int classAdd(Connection conn,String className,String classDesc);
	public int classModify(Connection conn,ClassBean clb);
	
}
