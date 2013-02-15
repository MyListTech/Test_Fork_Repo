package com.googlecode.excavator.demo.service.impl;

import com.googlecode.excavator.demo.service.SayHelloService;

/**
 * SayHello服务的实现
 * @author vlinux
 *
 */
public class SayHelloServiceImpl implements SayHelloService {

	public String sayTo(String name) {
		return "hello:"+name;
	}

}
