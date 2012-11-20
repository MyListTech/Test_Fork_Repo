package com.pugwoo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class User {

	private String username;
	
	private String password;

	public User(){}
	
	public User(String u, String p){
		username = u;
		password = p;
	}
	
	public static User getUser(String u, String p){
		return new User(u,p);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 单元测试
	 */
	public static void main(String args[]){
		
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user1 = (User) factory.getBean("user1");
		
		System.out.println("user1 name:" + user1.getUsername());
		System.out.println("user1 password:" + user1.getPassword());
		
		
		User user2 = (User) factory.getBean("user2");
		
		System.out.println("user2 name:" + user2.getUsername());
		System.out.println("user2 password:" + user2.getPassword());
	
		User user3 = (User) factory.getBean("user3");
		
		System.out.println("user3 name:" + user3.getUsername());
		System.out.println("user3 password:" + user3.getPassword());
	}
}
