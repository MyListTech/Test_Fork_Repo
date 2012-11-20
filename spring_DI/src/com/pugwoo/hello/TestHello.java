package com.pugwoo.hello;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHello {

	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		Hello hello = (Hello) factory.getBean("hello");

		/**
		 * 这里调用的已经是新方法
		 */
		hello.sayHello();

	}

}
