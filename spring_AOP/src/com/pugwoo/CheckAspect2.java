package com.pugwoo;

import org.aspectj.lang.JoinPoint;


/**
 * 切面类，不需要设定任何的注解来指定Pointcut或Advide，全部在配置文件中进行配置
 * 2010年12月16日 下午08:57:51
 */
@SuppressWarnings("unused")
public class CheckAspect2 {

	/*
	private void checkSecurity() {
		System.out.println("-------checkSecurity-------");
	}*/
	
	/**
	 * 上面的方法也可以，下面演示在Aspect中获得参数和返回值
	 * 首先方法的参数要加上JoinPoint对象
	 */
	private void checkSecurity(JoinPoint joinPoint){
		//获得参数列表
		Object[] objs = joinPoint.getArgs();
		
		//打印参数列表
		System.out.println("In function checkSecurity:");
		for(int i=0; i<objs.length; i++)
			System.out.println("arg["+i+"]="+objs[i]);
		
		//获得被切的对象调用的方法名
		System.out.println("you call method: "+ joinPoint.getSignature().getName());
		
		System.out.println("-------checkSecurity-------");
	}
}
