package com.pugwoo;

/**
 * 提供服务的类，它和Aspect无任何关系
 */
public class UserManagerImpl implements UserManager {

	@Override
	public String addUser(String username, String password) {
		System.out.println("addUser " + username + ",password:" + password);
		return "addUser " + username + ",password:" + password;
	}

	@Override
	public void delUser(int userId) {
		System.out.println("delUser userid:" + userId);
	}

	@Override
	public String findUserById(int userId) {
		System.out.println("find user by id :" + userId);
		return "found user with id " + String.valueOf(userId);
	}

	@Override
	public void modifyUser(int userId, String username, String password) {
		System.out.println("modify user to be id:" + userId + ", name:"
				+ username + ", password:" + password);
	}

}
