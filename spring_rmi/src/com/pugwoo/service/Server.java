package com.pugwoo.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

	public static void main(String[] args) {
		BeanFactory f = new ClassPathXmlApplicationContext(
				"applicationContext-server.xml");
		// 这样即可启动服务
		f.getBean("rmiService");
	}

}