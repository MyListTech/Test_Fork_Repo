package com.pugwoo.manager;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.domain.User;

public class UserManagerImplTest extends TestCase {

	public void testAddUser() throws Exception {
		//BeanFactory factory = new ClassPathXmlApplicationContext(
		//		"applicationContext-*.xml");
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		UserManager userManager = (UserManager) factory.getBean("userManager");

		User user = new User();
		user.setName("pugwooxaa");

		userManager.addUser(user);

	}

}
