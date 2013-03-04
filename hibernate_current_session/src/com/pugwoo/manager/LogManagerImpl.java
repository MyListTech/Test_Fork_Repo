package com.pugwoo.manager;

import hibernate.HibernateUtils;

import com.pugwoo.domain.Log;


public class LogManagerImpl implements LogManager {

	public void addLog(Log log) {
		HibernateUtils.getSessionFactory().getCurrentSession().save(log);
	}

}
