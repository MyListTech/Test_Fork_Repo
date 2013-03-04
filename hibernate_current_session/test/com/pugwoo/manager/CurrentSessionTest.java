package com.pugwoo.manager;

import hibernate.HibernateUtils;

import junit.framework.TestCase;

import org.hibernate.Session;

import com.pugwoo.domain.User;



public class CurrentSessionTest extends TestCase {

	public void testCurrentSession() {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			User user = new User();
			user.setName("pugwoo");
			session.save(user);

			//这里会报错org.hibernate.HibernateException: save is not valid without active transaction
			new Thread(new ThreadAnother()).start(); //调用另一个线程保存

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}

class ThreadAnother implements Runnable {

	@Override
	public void run() {
		User user = new User();
		user.setName("nick");
		// System.out.println("in Thead1, thread name:"
		// + Thread.currentThread().getName());
		HibernateUtils.getSessionFactory().getCurrentSession().save(user);

	}
}