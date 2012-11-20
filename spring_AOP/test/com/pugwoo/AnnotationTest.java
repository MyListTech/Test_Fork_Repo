package com.pugwoo;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2010年12月16日 下午09:00:29
 */
public class AnnotationTest extends TestCase {

	public void testAnnotation(){

		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-1.xml");
		
		//获得userManager对象，这是接口的好处
		UserManager userManager = (UserManager)factory.getBean("userManager");
		userManager.addUser("张三", "123");
	}
}
