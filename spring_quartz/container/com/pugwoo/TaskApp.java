package com.pugwoo;

import java.io.FileNotFoundException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2012年11月20日 下午10:25:32
 * 
 * 这种是最简单的静态的部署
 */
public class TaskApp {

	public static void main(String[] args) throws BeansException,
			FileNotFoundException, InterruptedException {
		
		@SuppressWarnings("unused")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/quartz_container.xml");
		
		// 这时候调度就已经开始执行了
		
		/**
		 * 注意到只要Spring容器一启动，job就开始跑了
		 * 因此如果部署在web容器，如tomcat里，那么tomcat已启动，这些就可以定时跑了
		 * 
		 * 但这样的缺点时，job调用是写死的
		 */

	}

}