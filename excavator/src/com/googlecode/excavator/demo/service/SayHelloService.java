package com.googlecode.excavator.demo.service;

/**
 * SayHello服务
 * @author vlinux
 *
 */
public interface SayHelloService {

	/**
	 * 向某人问好
	 * @param name
	 * @return "hello:"+name
	 */
	String sayTo(String name);
	
}
