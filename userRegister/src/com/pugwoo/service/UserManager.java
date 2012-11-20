package com.pugwoo.service;

import com.pugwoo.dao.UserDao;
import com.pugwoo.dao.impl.UserDaoImpl;
import com.pugwoo.model.User;

public class UserManager {

	private static UserDao userDao;
	
	static{
		userDao = new UserDaoImpl();
	}
	
	public static boolean isExistUsername(String username){
		return userDao.getUserByName(username)!=null;
	}
	
	public static void saveUser(User user){
		userDao.saveUser(user);
	}
	
	public static User getUserByName(String name){
		return userDao.getUserByName(name);
	}
}
