package com.pugwoo;

import java.util.Date;

/**
 * 2012年11月20日 下午10:25:32
 */
public class MyService {

	// 在quartz.xml里面配置调用这个方法
	public void reportTime() {
		System.out.println(new Date());
	}
}
