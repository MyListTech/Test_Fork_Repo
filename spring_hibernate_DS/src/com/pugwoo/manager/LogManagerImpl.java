package com.pugwoo.manager;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pugwoo.domain.Log;

public class LogManagerImpl extends HibernateDaoSupport implements LogManager {

	public void addLog(Log log) {
		//下面这两个操作时等价的，这两个方法均来自于HibernateDaoSupport
		//getSession().save(log);
		getHibernateTemplate().save(log);
	}

}
