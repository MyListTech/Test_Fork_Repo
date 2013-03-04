package unused;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EntityDaoSpringImpl {

	private HibernateTemplate hibernateTemplate;
	
	private JdbcTemplate jdbcTemplate;
	
	public EntityDaoSpringImpl(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 如果不是用纯SQL功能，则jdbcTemplate可以为空
	 */
	public EntityDaoSpringImpl(HibernateTemplate hibernateTemplate,
			JdbcTemplate jdbcTemplate){
		this.hibernateTemplate = hibernateTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public void delete(Object entity) {
		hibernateTemplate.delete(entity);
	}

	public void evict(Object obj) {
		hibernateTemplate.evict(obj);
	}

	@SuppressWarnings("unchecked")
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

	public void executeSQL(String sql, Object... values) {
		jdbcTemplate.update(sql, values);
	}

	public void flush() {
		hibernateTemplate.flush();
	}

	@SuppressWarnings("unchecked")
	public List query(String hql, Object... values) {
		return query(hql, -1, -1, values);
	}

	@SuppressWarnings("unchecked")
	public List query(final String hql, final int start, final int limit, final Object... values) {
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				for(int i=0; i<values.length; i++)
					q.setParameter(i, values[i]);
				if(limit >= 0){
					q.setFirstResult(start);
					q.setMaxResults(limit);
				}
				return q.list();
			}
		});
	}

	public Object querySQL(String sql, Object... values) {
		return jdbcTemplate.queryForRowSet(sql, values);
	}

	public Object querySQL(String sql, int start, int limit, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	public void rollback() {
		throw new RuntimeException();
	}

	public void save(Object entity) {
		hibernateTemplate.save(entity);
	}

	public void saveOrUpdate(Object entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	public void update(Object entity) {
		hibernateTemplate.update(entity);
	}

}
