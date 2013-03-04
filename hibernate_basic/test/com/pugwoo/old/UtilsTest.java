package com.pugwoo.old;

import hibernate.HibernateUtils;

import java.util.Date;

import com.pugwoo.User;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

	public void testSave() {

		try {
			Thread thread = new Thread(new Thread1());
			thread.start();
//������Ӳ��������User����Ϊ������һ���߳���
			HibernateUtils.commit();
		} catch (Exception e) {
			HibernateUtils.rollback();
			e.printStackTrace();
		}
	}
}

class Thread1 implements Runnable {

	@Override
	public void run() {
		User user = new User("pugwoo", "1234", new Date(), 23);
		System.out.println("in Thead1, thread name:"
				+ Thread.currentThread().getName());
		HibernateUtils.save(user);

	}

}