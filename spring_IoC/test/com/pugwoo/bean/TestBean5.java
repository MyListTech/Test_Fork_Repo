package com.pugwoo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2011年1月9日 下午06:35:53
 */
public class TestBean5 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		    "applicationContext-bean5.xml");
		
		Bean5 ds = (Bean5)ctx.getBean("bean5");
        System.out.println(ds.getUser());
        System.out.println(ds.getPassword());
	}

}
