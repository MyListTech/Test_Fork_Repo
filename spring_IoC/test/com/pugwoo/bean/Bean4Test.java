package com.pugwoo.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class Bean4Test extends TestCase{

	public void testBean1(){
		//取得bean工厂
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-bean4.xml");
	    //ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		/*在IoC容器中拿对象*/
	    Bean4 bean4 = (Bean4)factory.getBean("bean4");
	    System.out.println("-------------bean4--------------");
	    System.out.println("bean4.date="+bean4.getDate().toString());
	}
}
