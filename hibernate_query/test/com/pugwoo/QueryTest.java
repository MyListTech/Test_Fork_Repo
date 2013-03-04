package com.pugwoo;

import java.util.List;

import model.User;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.HibernateUtils;

/**
 * 2011年4月20日 下午03:12:49
 */
public class QueryTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from User");

		//分页
		query.setMaxResults(1);
		query.setFirstResult(0);

		// 获取结果为List
		List list = query.list();
		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);
			System.out.println(user.getName());
		}
		// 缓存适用  
		//Iterator iter = query.iterate();

		//只获取一个结果，前提要查询的结果要么0个要么1个，否则会出错
		User user = (User) query.uniqueResult();
		System.out.println(user.getName());
	}

}
