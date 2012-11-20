package com.pugwoo.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.pugwoo.dao.IUserDao;
import com.pugwoo.domain.User;

public class UserDaoImpl implements IUserDao {

	private HibernateTemplate hibernateTemplate;

	/**
	 * 需要注入sessionFactory已获得hibernateTemplate
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//上面三个方法完成hibernateTemplate相关工作
	
	@Override
	public void add(User user) {
		getHibernateTemplate().save(user);
		
	}

}
