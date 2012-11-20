package com.pugwoo.random;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRandom {

	public static void main(String[] args) {

		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AbstractRandomFactory randomFactory = (AbstractRandomFactory) factory.getBean("randomFactory");
		
		/**
		 * 这里已经是返回Spring配置的bean了
		 */
		Random rand1 = randomFactory.createRandom();
		rand1.printRandom();
		
		Random rand2 = randomFactory.createRandom();
		rand2.printRandom();
	}

}
