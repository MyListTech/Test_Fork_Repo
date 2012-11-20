package com.pugwoo.dao;

import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.domain.User;


public class TestUserDao extends TestCase{

	private static String[] paths = new String[] { 
		"classpath*:applicationContext-beans.xml",
		"classpath*:applicationContext-hibernate.xml" };
	
	protected static ApplicationContext applicationContext = null;
	
	static{
		applicationContext = new ClassPathXmlApplicationContext(paths);
	}
	
	public void testAdd(){
		IUserDao dao = (IUserDao) applicationContext.getBean("userDao");
		User user = new User();
		user.setName("pugwoo");
		user.setPassword("123456");
		user.setBirth(new Date());
		user.setProvince("广东");
		
		dao.add(user);
		
		/*这个不会改变密码的值*/
		user.setPassword("123aaa");
	}
	
	public void testRollback(){
		IUserDao dao = (IUserDao) applicationContext.getBean("userDao");
		User user = new User();
		user.setName("test");
		user.setBirth(new Date());
		dao.add(user);
		throw new RuntimeException();
	}
	
}
