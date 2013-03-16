package com.pugwoo.quartz.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2013年3月16日 15:47:35
 */
public class Run {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/quartz_db.xml");
		// 容器启动时quartz容器就启动了
		
	}
}
