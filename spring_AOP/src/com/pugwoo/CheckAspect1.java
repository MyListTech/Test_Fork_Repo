package com.pugwoo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 切面服务类，通过@Aspect注解该类 
 * 2010年12月16日 下午08:57:18
 * 
 * 文档：http://www.redsaga.com/spring_ref/2.5/html/aop.html
 */
@Aspect
public class CheckAspect1 {

	//@Pointcut("execution(* add*(..))")
	//private void addAddMethod(){};

	/**
	 * execution(* add*(..))就是一个Joinpoint，它匹配所有以add开头，任意参数任意返回值的方法
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 * 
	 * JoinPoint joinPoint参数可以忽略
	 */
	@Before("execution(* add*(..))")
	// @After("execution(* add*(..))")
	public void checkSecurity(JoinPoint joinPoint) {
		// 获得参数列表
		Object[] objs = joinPoint.getArgs();

		// 打印参数列表
		System.out.println("In function checkSecurity:");
		for (int i = 0; i < objs.length; i++)
			System.out.println("arg[" + i + "]=" + objs[i]);

		// 获得被切的对象调用的方法名
		System.out.println("you call method: "
				+ joinPoint.getSignature().getName());

		System.out.println("-------checkSecurity-------");
	}
	
	/**
	 *  此外还有抛出异常、返回值等几种通知方式，下面演示环绕通知（功能最强大）：
	 *  2012年11月18日 10:01:06
	 */
	@Around("execution(* add*(..))")
	public Object checkSecurity2(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("--------checkSecurity2---------");
		
		// 这里可以做一些检查，如果不符合条件就不调用proceed
		// 必须显式调用proceed才能执行
		Object retVal = pjp.proceed();
		return retVal;
	}
}
