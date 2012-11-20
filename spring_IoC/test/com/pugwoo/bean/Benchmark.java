package com.pugwoo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2011年12月14日 上午09:50:19
 */
@SuppressWarnings("unused")
public class Benchmark {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException {

		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-bean5.xml");
		JavaTime.start();
		for (int i = 0; i < 1000000; i++) {			
			Bean3 bean3 = (Bean3) factory.getBean("bean3");
		}
		JavaTime.printInMs();

		JavaTime.start();
		for (int i = 0; i < 1000000; i++) {
			Bean3 bean3 = new Bean3();
		}
		JavaTime.printInMs();

		JavaTime.start();
		Class<?> clazz = Bean3.class;
		for (int i = 0; i < 1000000; i++) {
			Bean3 bean3 = (Bean3) clazz.newInstance();
		}
		JavaTime.printInMs();
	}
}
