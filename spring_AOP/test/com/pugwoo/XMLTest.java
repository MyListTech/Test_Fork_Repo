package com.pugwoo;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLTest extends TestCase {

	public void testXML(){
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-2.xml");
		UserManager userManager = (UserManager)factory.getBean("userManager");
		
		userManager.addUser("张三", "123");
	}
}
