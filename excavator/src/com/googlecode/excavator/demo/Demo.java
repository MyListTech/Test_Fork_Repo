package com.googlecode.excavator.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.googlecode.excavator.demo.service.SayHelloService;

/**
 * 推土机例子
 * @author vlinux
 *
 */
public class Demo {
	
	public static void main(String... args) throws InterruptedException {
		
		try {
			
			// 初始化Spring
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
			ctx.registerShutdownHook();
			
			// 这里需要将服务的export导出，目的是为了能触发Spring主动加载provider服务
			ctx.getBean("sayHelloServiceExport");
			
			// 加载客户端
			SayHelloService sayHelloService = (SayHelloService) ctx.getBean("sayHelloService");
			
			//这里暂停5秒等待zk完成服务推送过程
			Thread.sleep(5000);
			
			// 远程调用服务
			System.out.println( sayHelloService.sayTo("dukun") );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// 作为一个良好市民，记得容器关闭的时候释放资源哦
			System.exit(0);
		}
		
	}
	
}
