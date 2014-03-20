package com.alibaba.dubbo.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.dubbo.demo.DemoService;

public class Consumer {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "consumer.xml" });
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // get service invocation proxy
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++) {
			String hello = demoService.sayHello("world"); // do invoke!

			System.out.println(hello); // cool, how are you~
		}
		
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}

}