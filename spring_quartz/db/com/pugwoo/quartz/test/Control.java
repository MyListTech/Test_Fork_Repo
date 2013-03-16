package com.pugwoo.quartz.test;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2013年3月16日 15:47:29
 */
public class Control {

	public static void main(String[] args) {
		// schedule不包含任何trigger, 但还是会执行
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/quartz_db_control.xml");
		Scheduler sche = (Scheduler) context.getBean("mapScheduler");
		System.out.println(sche);
		
		// sche可以控制sche数据库，增删改查
	}
}
