package com.pugwoo.hibernate.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.pugwoo.hibernate.dao.EntityDao;

public class EntityDaoImpl implements EntityDao {

	private HibernateTemplate hibernateTemplate;

	public EntityDaoImpl(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void delete(Object entity) {
		this.hibernateTemplate.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(final String hql, final Object... values) {
		hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				for(int i=0; i<values.length; i++)
					q.setParameter(i, values[i]);
				q.executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List query(final String hql, final int start, final int limit, final Object... values) {
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				for(int i=0; i<values.length; i++)
					q.setParameter(i, values[i]);
				if(start >= 0)
					q.setFirstResult(start);
				if(limit > 0)
					q.setMaxResults(limit);
				return q.list();
			}
		});
	}


	@Override
	public void save(Object entity) {
		hibernateTemplate.save(entity);
	}

	@Override
	public void update(Object entity) {
		hibernateTemplate.update(entity);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
