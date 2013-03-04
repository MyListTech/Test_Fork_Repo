package com.pugwoo.manager;

import junit.framework.TestCase;

import com.pugwoo.domain.User;

public class UserManagerImplTest extends TestCase {

	public void testAddUser() {
		UserManager userManager = new UserManagerImpl();
		
		User user = new User();
		user.setName("ÕÅÈı");
		
		userManager.addUser(user);
		
	}

}
