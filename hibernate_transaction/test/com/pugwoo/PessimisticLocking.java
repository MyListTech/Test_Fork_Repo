package com.pugwoo;

import java.util.List;

import model.User;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.HibernateUtils;

/**
 * 悲观锁
 * 2011年4月12日 下午03:17:54
 */
public class PessimisticLocking {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();

		// 第一种Query
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User user");
		query.setLockMode("user", LockMode.UPGRADE); //悲观锁
		@SuppressWarnings("unused")
		List<User> list = query.list();
		tx.commit();
		
		// 第二种：get或load方法中设置
		tx = session.beginTransaction();
		@SuppressWarnings("unused")
		User user = (User) session.get(User.class, 1L, LockMode.UPGRADE);
		tx.commit();
	}

}
