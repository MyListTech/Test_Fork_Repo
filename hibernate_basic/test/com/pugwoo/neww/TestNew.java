package com.pugwoo.neww;

import hibernate.HibernateUtils;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.pugwoo.User;

/**
 * 2011年3月10日 下午01:04:34
 */
public class TestNew extends TestCase{

	public void testSave(){
		User user = new User("pugwoo", "123456", new Date(),10);
		User user2 = new User("nick", "123456", new Date(),10);
		HibernateUtils.save(user);
		HibernateUtils.save(user2);
		// 必须有commit数据才会被存入数据库，因为这里用了事务
		HibernateUtils.commit();
	}
	
	public void testRollback(){
		User user = new User("woo", "123456", new Date(),11);
		HibernateUtils.save(user);
		HibernateUtils.rollback();
	}

	public void testQueryOne(){
		User user = (User) HibernateUtils.queryByGet(User.class, 1L);
		if(user == null){
			System.out.println("user not found");
		}
		else{
			System.out.println(user.getName());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testQuery(){
		List<User> list = HibernateUtils.query("from User");
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getName());
		}
	}
}
