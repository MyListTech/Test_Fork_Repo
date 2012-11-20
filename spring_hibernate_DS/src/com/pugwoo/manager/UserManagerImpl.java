package com.pugwoo.manager;

import java.util.Date;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pugwoo.domain.Log;
import com.pugwoo.domain.User;


public class UserManagerImpl extends HibernateDaoSupport implements UserManager {

	private LogManager logManager; 

	public void addUser(User user) throws Exception {
		//下面这两个操作时等价的，这两个方法均来自于HibernateDaoSupport
		//this.getSession().save(user);
		this.getHibernateTemplate().save(user);
	
		Log log = new Log();
		log.setType("操作日志");
		log.setTime(new Date());
		log.setDetail("XXX");
		
		logManager.addLog(log);
		
		//throw new RuntimeException(); //只有当抛出RuntimeException时回滚，其它Exception不回滚
		//这个不回滚！！！！
		//已解决回滚问题：得用ApplicationContext而不是BeanFactory
	}
	
	public void setLogManager(LogManager logManager) {
		this.logManager = logManager;
	}

}
