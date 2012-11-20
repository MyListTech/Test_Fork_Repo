package com.pugwoo;

/**
 * 提供服务的接口，它和Aspect无任何关系
 */
public interface UserManager {

	public String addUser(String username, String password);
	
	public void delUser(int userId);
	
	public String findUserById(int userId);
	
	public void modifyUser(int userId, String username, String password);
}
