package com.mushuijie.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mushuijie.dao.UserDao;
import com.mushuijie.model.User;
import com.mushuijie.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User loginIn(String username, String password) {
		// TODO Auto-generated method stub
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		String sql1="select id,password from usertbl where username=? limit 0,1";
		PreparedStatement ppst=null;
		ResultSet rs=null;
		try {
			ppst=conn.prepareStatement(sql1);
			ppst.setString(1, username);
			rs=ppst.executeQuery();
			if(rs.next()){
				String tmp=rs.getString("password");
				if(tmp.equals(password)){
					User u=new User();
					u.setID(rs.getInt(1));
					u.setUsername(username);
					u.setPassword(password);
					tmp=null;
					return u;
				}
			}
			
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
				if(conn!=null){
					db.closeConnection(conn);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public void register(String username,String password) {
		// TODO Auto-generated method stub
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		String sql="insert into usertbl (username,passowrd) values(?,?)";
		PreparedStatement ppst=null;
		
		try {
			ppst=conn.prepareStatement(sql);
			ppst.setString(1, username);
			ppst.setString(2, password);
			ppst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ppst!=null){
					ppst.close();
				}
				if(conn!=null){
					db.closeConnection(conn);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public boolean check(User u) {
		// TODO Auto-generated method stub
		DBUtil db=new DBUtil();
		Connection conn=db.openConnection();
		String sql="select  * from usertbl where username=? limit 0,1";
		PreparedStatement ppst=null;
		ResultSet rs=null;
		try {
			ppst=conn.prepareStatement(sql);
			ppst.setString(1, u.getUsername());
			rs=ppst.executeQuery();
			if(rs.next()){
				return true;
			}
			
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
				if(conn!=null){
					db.closeConnection(conn);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}

}
