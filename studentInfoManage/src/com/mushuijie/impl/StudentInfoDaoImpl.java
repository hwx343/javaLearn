package com.mushuijie.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mushuijie.dao.StudentInfoDao;
import com.mushuijie.model.PageBean;
import com.mushuijie.model.StudentBean;
import com.mushuijie.util.DBUtil;
import com.mushuijie.util.JsonUtil;
import com.mushuijie.util.StringUtil;

import net.sf.json.JSONArray;

public class StudentInfoDaoImpl implements StudentInfoDao{
	@Override
	public JSONArray listStudentInfo(Connection conn, PageBean pg,StudentBean student,
			String begbirthday,String endbirthday) {
//		StringBuffer sql=new StringBuffer("select s.id,s.stuNo,s.stuName,s.sex,s.birthday,"
//				+ "g.className,s.email,s.stuDesc from studentInfoTbl s,classtbl g "
//				+ "where s.gradeId=g.id");
		StringBuffer sql=new StringBuffer("select * from studentInfoTbl s,classtbl g "
				+ "where s.gradeId=g.id");
		if(student!=null){
			if(!StringUtil.isEmpty(student.getStuNo())){
				sql=sql.append(" and stuNo like '%"+student.getStuNo()+"%'");
			}
			if(!StringUtil.isEmpty(student.getStuName())){
				sql=sql.append(" and stuName like '%"+student.getStuName()+"%'");
			}
			if(!StringUtil.isEmpty(student.getSex())){
				sql=sql.append(" and sex like '%"+student.getSex()+"%'");
			}
			if(!StringUtil.isEmpty(begbirthday)){
				sql=sql.append(" and DATE(s.birthday) >=DATE('"+begbirthday+"')");
			}
			if(!StringUtil.isEmpty(endbirthday)){
				sql=sql.append(" and DATE(s.birthday) <=DATE('"+endbirthday+"')");
			}
			if(student.getGradeId()!=-1){
				sql=sql.append(" and gradeId like '%"+student.getGradeId()+"%'");
			}
		}
		
		if(pg!=null){
			String condition1=" limit "+pg.getStart()+","+pg.getRows();
			PreparedStatement ppst=null;
			ResultSet rs=null;
			try {
				ppst=conn.prepareStatement(sql.append(condition1).toString());
				rs=ppst.executeQuery();
				return JsonUtil.resultSet2Json(rs);
			} catch (SQLException e) {
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
		}		
		return null;
	}

	@Override
	public int getStudentCount(Connection conn, PageBean pg,StudentBean student,
			String begbirthday,String endbirthday) {
		StringBuffer sql=new StringBuffer("select count(*) as count from studentInfoTbl");
		if(student!=null){
			if(!StringUtil.isEmpty(student.getStuNo())){
				sql=sql.append(" and stuNo like '%"+student.getStuNo()+"%'");
			}
			if(!StringUtil.isEmpty(student.getStuName())){
				sql=sql.append(" and stuName like '%"+student.getStuName()+"%'");
			}
			if(!StringUtil.isEmpty(student.getSex())){
				sql=sql.append(" and sex like '%"+student.getSex()+"%'");
			}
			if(!StringUtil.isEmpty(begbirthday)){
				sql=sql.append(" and DATE(s.birthday) >=DATE('"+begbirthday+"')");
			}
			if(!StringUtil.isEmpty(endbirthday)){
				sql=sql.append(" and DATE(s.birthday) <=DATE('"+endbirthday+"')");
			}
			if(student.getGradeId()!=-1){
				sql=sql.append(" and gradeId like '%"+student.getGradeId()+"%'");
			}
		}
		
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql.toString().replaceFirst("and","where"));
			if(rs.next()){
				int result=rs.getInt(1);
				return result;
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
	public int studentDelete(Connection conn, String stuId) {
		String dslSql="delete from studentInfoTbl where stuId in ("+stuId+")";
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
			int result = stmt.executeUpdate(dslSql);
			return result;
		}catch(Exception e){ 
			e.printStackTrace();
		}finally {
			try {
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
	public int studentAdd(Connection conn, StudentBean stu) {
		String addSql="insert into studentInfoTbl (stuNo,stuName,sex,birthday,"
				+ "gradeId,email,stuDesc) values(?,?,?,?,?,?,?)";
		String stuNo=stu.getStuNo();
		String stuName=stu.getStuName();
		String sex=stu.getSex();
		Date birthday=stu.getBirthday();
		int gradeId=stu.getGradeId();
		String email=stu.getEmail();
		String stuDesc=stu.getStuDesc();
		PreparedStatement ppst=null;
		try {
			ppst=conn.prepareStatement(addSql);
			ppst.setString(1, stuNo);
			ppst.setString(2, stuName);
			ppst.setString(3, sex);
			ppst.setDate(4, birthday);
			ppst.setInt(5, gradeId);
			ppst.setString(6, email);
			ppst.setString(7, stuDesc);
			int result=ppst.executeUpdate();
			return result;
			
		} catch (Exception e) {
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
	public int studentModify(Connection conn, StudentBean stu) {
		String editSql="update studentInfoTbl set stuNo=?,stuName=?,sex=?,birthday=?,gradeId=?"
				+ ",email=?,stuDesc=? where stuId=?";
		String stuNo=stu.getStuNo();
		String stuName=stu.getStuName();
		String sex=stu.getSex();
		Date birthday=stu.getBirthday();
		int gradeId=stu.getGradeId();
		String email=stu.getEmail();
		String stuDesc=stu.getStuDesc();
		int id=stu.getStuId();
		PreparedStatement ppst=null;
		try {
			ppst=conn.prepareStatement(editSql);
			ppst.setString(1, stuNo);
			ppst.setString(2, stuName);
			ppst.setString(3, sex);
			ppst.setDate(4, birthday);
			ppst.setInt(5, gradeId);
			ppst.setString(6, email);
			ppst.setString(7, stuDesc);
			ppst.setInt(8, id);
			int result=ppst.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(ppst!=null){
					ppst.close();
				}					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return 0;
	}

}
