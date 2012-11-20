package com.pugwoo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 实现ApplicationContextAware接口以获得ApplicationContext对象
 * 2011年1月9日 下午03:32:08
 */
public class LoginAction implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public int login(String username, String password){
		//定义一个和username同名的ActionEvent对象
		ActionEvent event = new ActionEvent(username);
		//将event发布出去
		this.applicationContext.publishEvent(event);
		return 0;
	}

}
