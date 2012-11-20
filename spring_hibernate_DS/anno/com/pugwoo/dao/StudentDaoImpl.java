package com.pugwoo.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pugwoo.model.Student;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {

	@Override
	public void add(Student student) {
		this.getHibernateTemplate().save(student);
	}

}
