package com.pugwoo.dao;


import com.pugwoo.model.User;

public interface UserDao {

	public String[] getUsernameList();
	
	public void saveUser(User user);
	
	public User getUserByName(String name);
	
	public void deleteUserByName(String name);
	
}
