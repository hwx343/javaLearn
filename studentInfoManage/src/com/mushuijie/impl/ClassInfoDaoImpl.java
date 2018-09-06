package com.mushuijie.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mushuijie.dao.ClassInfoDao;
import com.mushuijie.model.ClassBean;
import com.mushuijie.model.PageBean;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.JsonUtil;

import net.sf.json.JSONArray;

public class ClassInfoDaoImpl implements ClassInfoDao{
	
	@Override
	public JSONArray listClassInfo(Connection conn,PageBean pg,String className) {
		StringBuffer sb=new StringBuffer("select * from classtbl");
		if(className!=null&&!(className.equals(""))){
			sb.append(" and className like '%"+className+"%'");
		}		
		if(pg!=null){
			sb=sb.append(" limit "+pg.getStart()+","+pg.getRows());
		}
		PreparedStatement ppst=null;
		ResultSet rs=null;
		try {
			ppst=conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
			rs=ppst.executeQuery();
			JSONArray jarr=JsonUtil.resultSet2Json(rs);
			return jarr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ppst!=null){
					ppst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
			
			
		
		return null;
	}

	@Override
	public int getClassCount(Connection conn,String className) {
		StringBuffer sb=new StringBuffer("select count(*) as total from classtbl");
		if(className!=null&&!(className.equals(""))){
			sb.append(" and className like '%"+className+"%'");
		}
		Statement stmt = null;
		ResultSet rs=null;
		try {
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sb.toString().replaceFirst("and", "where"));
			if(rs.next()){
				return rs.getInt("total");
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return 0;
	}

	@Override
	public int classDelete(Connection conn, String classID) {
		// TODO Auto-generated method stub
		String delsql="delete from classtbl where id in ("+classID+")";
		PreparedStatement ppst=null;
		try {
			ppst=conn.prepareStatement(delsql);
			return ppst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ppst!=null){
					ppst.close();
				}					
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return 0;
		
	}

	@Override
	public int classAdd(Connection conn,String className,String classDesc) {
		String sql="insert classtbl (className,classDesc) values(?,?)";
		PreparedStatement ppst=null;
		try {
			ppst=conn.prepareStatement(sql);
			ppst.setString(1, className);
			ppst.setString(2, classDesc);
			int addNums=ppst.executeUpdate();
			return addNums;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(ppst!=null){
					ppst.close();
				}					
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return 0;
	}

	@Override
	public int classModify(Connection conn, ClassBean clb) {
		int classID=clb.getId();
		String className=clb.getClassName();
		String classDesc=clb.getClassDesc();
		String sql="update classtbl set className=?,classDesc=? where id=?";
		PreparedStatement ppst=null;
		try {
			ppst=conn.prepareStatement(sql);
			ppst.setString(1, className);
			ppst.setString(2, classDesc);
			ppst.setLong(3, classID);
			int editNums=ppst.executeUpdate();
			return editNums;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(ppst!=null){
					ppst.close();
				}					
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return 0;
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	
	
	

}
