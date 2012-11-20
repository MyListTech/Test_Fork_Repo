package com.pugwoo.hello;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class NewHello implements MethodReplacer {

	/**
	 * 要替换的新方法
	 */
	@Override
	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		System.out.println("hello pugwoo");
		return null;
	}

}
