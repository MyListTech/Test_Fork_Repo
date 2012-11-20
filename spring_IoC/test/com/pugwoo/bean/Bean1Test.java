package com.pugwoo.bean;

import java.util.Properties;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean1Test extends TestCase{

	public void testBean1(){
		//取得bean工厂
		//BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-bean1.xml");
	    ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext-bean1.xml");
		
		/*在IoC容器中拿对象*/
	    Bean1 bean1 = (Bean1)factory.getBean("bean1");
	    System.out.println("---------bean1--------------");
	    System.out.println("strValue="+bean1.getStrValue());
	    System.out.println("intValue="+bean1.getIntValue());
	    System.out.println("listValue="+bean1.getListValue());
	    System.out.println("setValue="+bean1.getSetValue());
	    System.out.println("mapValue="+bean1.getMapValue());
	    System.out.print("arrValue=");
	    for(int i=0; i<bean1.getArrValue().length; i++){
	    	System.out.print(bean1.getArrValue()[i]+" ");
	    }
	    System.out.println("\nproperties=");
	    Properties properties = bean1.getProperties();
	    System.out.println(properties.toString());
	    System.out.println();
	}
}
