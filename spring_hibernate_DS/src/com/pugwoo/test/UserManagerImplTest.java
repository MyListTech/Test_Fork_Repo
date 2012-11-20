package com.pugwoo.test;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.domain.User;
import com.pugwoo.manager.UserManager;

/**
 * ‎2011‎年‎1‎月‎9‎日，‏‎13:29:24
 */
public class UserManagerImplTest extends TestCase {

	public void testAddUser() throws Exception {
		
		// 一定要用ApplicationContext，不然数据库事务方面会有问题
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-hibernate-xml.xml");

		UserManager userManager = (UserManager) factory.getBean("userManager");

		User user = new User();
		user.setName("pugwoo");

		userManager.addUser(user);
	}
}
