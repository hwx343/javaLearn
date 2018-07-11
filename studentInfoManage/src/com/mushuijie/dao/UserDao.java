package com.mushuijie.dao;

import com.mushuijie.model.User;

public interface UserDao {
	public User loginIn (String username,String password);
	public void register(String username,String password);
	public boolean check(User u);
	
}
