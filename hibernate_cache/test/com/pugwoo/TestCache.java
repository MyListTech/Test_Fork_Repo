package com.pugwoo;

import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.HibernateUtils;

/**
 * 2011年4月12日 下午05:00:02
 */
public class TestCache {

	public static void main(String[] args) {

		User user = new User();
		user.setId(1L);
		user.setUsername("pugwoo");

		HibernateUtils.save(user);
		HibernateUtils.commit();

		// 查询
		Session session1 = HibernateUtils.getSession();
		Transaction tx1 = session1.beginTransaction();
		User user1 = (User) session1.get(User.class, new Long(1L));
		tx1.commit();

		Session session2 = HibernateUtils.getSession();
		Transaction tx2 = session2.beginTransaction();
		User user2 = (User) session2.get(User.class, new Long(1L));
		tx2.commit();

		//正常的话，应该只看到一条insert语句

		System.out.println(user1 == user2);

		/**
		 * 管理二级缓存
		 */
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		sessionFactory.evict(User.class); //清空该类的所有二级缓存
		sessionFactory.evict(User.class, 1L); //情况制定的对象

		/**
		 * HQL
		 * 查询缓存
		 */
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User");
		//【重要】
		query.setCacheable(true);
		query.list();
		tx.commit();
	}

}
