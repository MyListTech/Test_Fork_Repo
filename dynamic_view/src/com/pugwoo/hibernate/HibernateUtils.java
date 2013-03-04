package com.pugwoo.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 2010-11-24 23:21重写 2011-04-09 晚 补充 思想：session与线程绑定，
 * 但这还是不太好，最好是session和一个逻辑业务单元绑定
 * 
 * 使用：commit和rollback需要手动调用，只要执行了对数据库的修改，就必须执行commit
 */
@SuppressWarnings("unchecked")
public class HibernateUtils {

	private static SessionFactory factory = null;
	private static Configuration cfg = null;

	/**
	 * maps变量用于维护线程所对应的session 也即维护对象的Persistent状态
	 */
	private static HashMap<Thread, Session> maps = new HashMap<Thread, Session>();

	/**
	 * 初始化
	 */
	static {
		try {
			/*
			 * 获得配置文件hibernate.cfg.xml信息 如果是xml文件，则需要加上.configure()
			 * 如果是properties文件，则不需
			 */
			cfg = new Configuration().configure("/hibernate.cfg.xml");
			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			cfg = null;
			factory = null;
		}
	}

	/**
	 * 获得Configuration对象
	 * 
	 * @return
	 */
	public static Configuration getConfiguration() {
		return cfg;
	}

	/**
	 * 获得SessionFactory对象
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}

	/**
	 * 获得一个Session对象
	 * 
	 * @return
	 */
	public static Session getSession() {
		return factory.openSession();
	}

	/**
	 * 获得当前Thread对应的Session对象
	 */
	protected static Session getThreadSession() {
		Thread thread = Thread.currentThread();
		Session session;
		if (maps.containsKey(thread)) {
			session = maps.get(thread);
		} else {/* 需要开事务 */
			session = getSession();
			maps.put(thread, session);
			session.beginTransaction();
		}
		return session;
	}

	/**
	 * 关闭一个Session对象
	 * 
	 * @param session
	 */
	public static void close(Session session) {
		if (session != null)
			if (session.isOpen()) {
				session.close();
			}
	}

	/**
	 * 提交当前线程对应的事务，会自动关闭session
	 */
	public static void commit() {
		Thread thread = Thread.currentThread();
		if (maps.containsKey(thread)) {
			Session session = maps.get(thread);
			maps.remove(thread);
			/* 提交事务 */
			session.getTransaction().commit();
			/* 关闭session */
			close(session);
		}
	}

	/**
	 * 回滚当前线程对应的事务，会自动关闭session
	 */
	public static void rollback() {
		Thread thread = Thread.currentThread();
		if (maps.containsKey(thread)) {
			Session session = maps.get(thread);
			maps.remove(thread);
			/* 回滚事务 */
			session.getTransaction().rollback();
			/* 关闭session */
			close(session);
		}
	}

	/**
	 * 保存一个实体到数据库，必须调用使用当前进程commit才能存入数据库
	 */
	public static void save(Object entity) {
		getThreadSession().save(entity);
	}

	/**
	 * 更新一个实体到数据库，必须调用使用当前进程commit才能存入数据库
	 */
	public static void update(Object entity) {
		getThreadSession().update(entity);
	}

	/**
	 * 删除数据库中一个实体，必须调用使用当前进程commit才能存入数据库
	 */
	public static void delete(Object entity) {
		getThreadSession().delete(entity);
	}

	/**
	 * 创建所有表，原先数据将被删除
	 */
	public static void clearAndCreateTables() {
		SchemaExport export = new SchemaExport(cfg);
		/* 将类生成数据库表，数据库要自己建立 */
		export.create(true, true);
	}

	/**
	 * 通过Get方法查询“主键”获得对象，对象成为Persistent状态 如果数据不存在，返回null
	 */
	public static Object queryByGet(Class clazz, Serializable id) {
		return getThreadSession().get(clazz, id);
	}

	/**
	 * 通过Load方法查询“主键”获得对象，对象成为Persistent状态
	 * 实际上是一种lazy加载，如果找不到，抛出ObjectNotFoundException
	 */
	public static Object queryByLoad(Class clazz, Serializable id) {
		return getThreadSession().load(clazz, id);
	}

	/**
	 * HQL query的总方法:通过hql语句查询，返回List
	 * 其中hql语句是以问号为参数的start和limit用-1表示无效
	 */
	public static List query(String hql, Object[] values, int start, int limit) {
		Session session = getThreadSession();
		//创建一个Query接口
		Query query = session.createQuery(hql);

		if (values != null)
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		if (start >= 0)
			query.setFirstResult(start);
		if (limit >= 0)
			query.setMaxResults(limit);

		return query.list();
	}

	public static List query(String hql) {
		return query(hql, null, -1, -1);
	}

	public static List query(String hql, Object[] values) {
		return query(hql, values, -1, -1);
	}

	public static List query(String hql, Object value) {
		return query(hql, new Object[] { value }, -1, -1);
	}

	public static List query(String hql, int start, int limit) {
		return query(hql, null, start, limit);
	}

	public static List query(String hql, Object value, int start, int limit) {
		return query(hql, new Object[] { value }, start, limit);
	}

	/**
	 * SQL query的总方法:通过sql语句查询，返回List
	 * 其中sql语句是以问号为参数的start和limit用-1表示无效
	 */
	public static List querySQL(String sql, Object[] values, int start,
			int limit) {
		Session session = getThreadSession();
		//创建一个Query接口
		SQLQuery query = session.createSQLQuery(sql);

		if (values != null)
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		if (start >= 0)
			query.setFirstResult(start);
		if (limit >= 0)
			query.setMaxResults(limit);

		return query.list();
	}

	public static List querySQL(String sql) {
		return querySQL(sql, null, -1, -1);
	}

	public static List querySQL(String sql, Object[] values) {
		return querySQL(sql, values, -1, -1);
	}

	public static List querySQL(String sql, Object value) {
		return querySQL(sql, new Object[] { value }, -1, -1);
	}

	public static List querySQL(String sql, int start, int limit) {
		return querySQL(sql, null, start, limit);
	}

	public static List querySQL(String sql, Object value, int start, int limit) {
		return querySQL(sql, new Object[] { value }, start, limit);
	}

	/**
	 * 执行HQL语句的总方法
	 */
	public static void execute(String hql, Object[] values) {
		Session session = getThreadSession();
		//创建一个Query接口
		Query query = session.createQuery(hql);
		if (values != null)
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		query.executeUpdate();
	}
	
	public static void execute(String hql, Object value) {
		execute(hql, new Object[]{value});
	}
	public static void execute(String hql) {
		execute(hql, null);
	}
	
	/**
	 * 执行SQL语句的总方法
	 */
	public static void executeSQL(String sql, Object[] values) {
		Session session = getThreadSession();
		//创建一个Query接口
		SQLQuery query = session.createSQLQuery(sql);
		if (values != null)
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		query.executeUpdate();
	}
	
	public static void executeSQL(String hql, Object value) {
		executeSQL(hql, new Object[]{value});
	}
	public static void executeSQL(String hql) {
		executeSQL(hql, null);
	}
}
