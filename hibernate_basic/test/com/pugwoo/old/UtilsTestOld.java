package com.pugwoo.old;

import hibernate.HibernateUtilsOld;

import java.util.Date;
import java.util.List;

import com.pugwoo.User;

import junit.framework.TestCase;

public class UtilsTestOld extends TestCase {
	
	public void testSave(){
		User user = new User("nick","1234",new Date(),12);
		/*ʵ����뵽��ݿ��У��˺�����ΪPersistent״̬*/
		HibernateUtilsOld.save(user);
		/*�޸��û����룬�ö�������ͬ��*/
		user.setPassword("abcd");
		/*һ��Ҫ����commitAndClose����ú������commit��������������ݽ���ʧ*/
		HibernateUtilsOld.close(user);
	}
	
	public void testUpdate(){
		User user = new User("awoo","abcdef",new Date(),11);
		//���µ�user��������ݿ����еģ�����һ��
		HibernateUtilsOld.save(user);
		HibernateUtilsOld.close(user);
		
		//��ʱuser������������uuid
		user.setPassword("xxx");
		//���Ը���
		HibernateUtilsOld.update(user);
		user.setPassword("yyy");
		HibernateUtilsOld.close(user);
	}
	
	@SuppressWarnings("unchecked")
	public void testQuery(){
		List<User> userList = HibernateUtilsOld.query("from User where name='nick'", 0, 10);
		for(int i=0; i < userList.size(); i++)
			System.out.println(userList.get(i).getName());
	}
}
