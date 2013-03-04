package unused;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 这个文件整合了之前hibernateUtils
 * 
 * @author PugwooChia
 *         2011-5-3
 */
public class EntityDaoHibernateImpl {

	/**
	 * Hibernate SessionFactory
	 */
	private SessionFactory sessionFactory;

	/**
	 * maps变量用于维护线程所对应的session 也即维护对象的Persistent状态
	 */
	private HashMap<Thread, Session> map = new HashMap<Thread, Session>();

	public EntityDaoHibernateImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 核心函数，获得当前Thread对应的Session对象
	 */
	private Session getSession() {
		Thread thread = Thread.currentThread();
		Session session;
		if (map.containsKey(thread)) {
			session = map.get(thread);
		} else {/* 需要开事务 */
			session = sessionFactory.openSession();
			map.put(thread, session);
			session.beginTransaction();
		}
		return session;
	}

	/**
	 * 提交当前线程对应的事务，会自动关闭session
	 */
	public void commit() {
		Thread thread = Thread.currentThread();
		if (map.containsKey(thread)) {
			Session session = map.get(thread);
			map.remove(thread);
			/* 提交事务 */
			session.getTransaction().commit();
			/* 关闭session */
			if (session.isOpen())
				session.close();
		}
	}

	/**
	 * 回滚当前线程对应的事务，会自动关闭session
	 */
	public void rollback() {
		Thread thread = Thread.currentThread();
		if (map.containsKey(thread)) {
			Session session = map.get(thread);
			map.remove(thread);
			/* 回滚事务 */
			session.getTransaction().rollback();
			/* 关闭session */
			if (session.isOpen())
				session.close();
		}
	}

	public void save(Object entity) {
		getSession().save(entity);
	}

	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(Object entity) {
		getSession().update(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List query(String hql, Object... values) {
		return query(hql, -1, -1, values);
	}

	@SuppressWarnings("unchecked")
	public List query(String hql, int start, int limit, Object... values) {
		Session session = getSession();
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

	public void execute(String hql, Object... values) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		if (values != null)
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		query.executeUpdate();
	}

	public void executeSQL(String sql, Object... values) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		if (values != null)
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List querySQL(String sql, Object... values) {
		return querySQL(sql, -1, -1, values);
	}

	@SuppressWarnings("unchecked")
	public List querySQL(String sql, int start, int limit, Object... values) {
		Session session = getSession();
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

	public void evict(Object obj) {
		getSession().evict(obj);
	}

	public void flush() {
		getSession().flush();
	}

}
