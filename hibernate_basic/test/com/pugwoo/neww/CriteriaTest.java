package com.pugwoo.neww;

import hibernate.HibernateUtils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.pugwoo.User;

/**
 * 2011年3月10日 下午01:04:34
 */
public class CriteriaTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		
		// 创建一个Criteria
		Criteria crit = session.createCriteria(User.class);
		//创建各种条件
		Criterion c1 = Restrictions.like("name", "%oo%");
		Criterion c2 = Restrictions.between("id", new Long(1), new Long(3));
		
		crit.add(c1);
		crit.add(c2);

		List list = crit.list();
		
		System.out.println(list);
		tx.commit();
	}

}
