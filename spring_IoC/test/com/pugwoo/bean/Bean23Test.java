package com.pugwoo.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class Bean23Test extends TestCase{

	public void testBean1(){
		//取得bean工厂
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-bean23.xml");
	    //ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		/*在IoC容器中拿对象，当拿bean2时，bean2依赖的bean3将自动创建*/
	    Bean2 bean2 = (Bean2)factory.getBean("bean2");
	    System.out.println("------------bean23------------");
	    System.out.println("bean2.bean3.name="+bean2.getBean3().getName());
	}
}
