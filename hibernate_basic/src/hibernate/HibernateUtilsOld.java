package hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 这种是旧的封装，一个obj对应一个session
 */
@SuppressWarnings("unchecked")
public class HibernateUtilsOld {

	private static SessionFactory factory = null;
	private static Configuration cfg = null;
	
	/**
	 * maps变量用于维护对象所对应的session，当使用close关闭对象时，关闭对应的session
	 * 也即维护对象的Persistent状态
	 */
	private static HashMap<Object, Session> maps = new HashMap<Object, Session>();
	
	/**
	 * 初始化
	 */
	static {
		try{
			/* 获得配置文件hibernate.cfg.xml信息
			 * 如果是xml文件，则需要加上.configure()
			 * 如果是properties文件，则不需
			 * */
			cfg = new Configuration().configure("/hibernate.cfg.xml");
			factory = cfg.buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
			cfg = null;
			factory = null;
		}
	}
	
	/**
	 * 获得Configuration对象
	 * @return
	 */
	public static Configuration getConfiguration(){
		return cfg;
	}
	
	/**
	 * 获得SessionFactory对象
	 * @return 
	 */
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	/**
	 * 获得一个Session对象
	 * @return
	 */
	public static Session getSession(){
		return factory.openSession();
	}
	
	/**
	 * 关闭一个Session对象
	 * @param session
	 */
	public static void close(Session session){
		if(session != null)
			if(session.isOpen()){
				session.close();
			}
	}
	
	/**
	 * 关闭Persistent状态的对象
	 * 此时将调用commit
	 * @param obj
	 */
	public static void close(Object obj){
		if(maps.containsKey(obj)){
			/*获得对应的session变量*/
			Session session = (Session) maps.get(obj);
			/*删除obj对应的session*/
			maps.remove(obj);
			/*对于一个session，它可能是未提交的*/
			if(session !=null && session.getTransaction().isActive())
				session.getTransaction().commit();
			/*调用另一个close函数关闭session*/
			HibernateUtilsOld.close(session);
		}
	}
	
	/**
	 * 关闭Persistent状态的对象
	 * 但不调用commit
	 * @param obj
	 */
	public static void closeWithoutCommit(Object obj){
		if(maps.containsKey(obj)){
			/*获得对应的session变量*/
			Session session = (Session) maps.get(obj);
			/*删除obj对应的session*/
			maps.remove(obj);
			/*调用另一个close函数关闭session*/
			HibernateUtilsOld.close(session);
		}
	}
	
	/**
	 * 创建所有表，原先数据将被删除
	 */
	public static void createTables(){
		if(cfg != null){
			SchemaExport export = new SchemaExport(cfg);
			/*将类生成数据库表，数据库要自己建立*/
			export.create(true, true);
		}
	}
	
	/**
	 * 保存对象至数据库，执行save之后，对象变成Persistent状态
	 * 必须在后续程序中调用HibernateUtils.close(Object)关闭对象使状态不为Persistent
	 */
	public static void save(Object obj){
		Session session = null;
		try{
			/*获得一个Session，建议一个session对应一个事务*/
			session = HibernateUtilsOld.getSession();
			/*开启事务*/
			session.beginTransaction();
			/*保存对象，此时对象成为Persistent状态*/
			session.save(obj);
			/*将对象和Session加入到maps中*/
			maps.put(obj, session);
		}catch(Exception e){
			/*回滚事务*/
			session.getTransaction().rollback();
			if(maps.containsKey(obj))
				maps.remove(obj);
			session = null;
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新对象obj，其中obj的主键必须和数据库中的一致
	 * 更新完成之后，obj变成Persistent状态
	 */
	public static void update(Object obj){
		Session session = null;
		try{
			/*获得一个Session，建议一个session对应一个事务*/
			session = HibernateUtilsOld.getSession();
			/*开启事务*/
			session.beginTransaction();
			/*保存对象，此时对象成为Persistent状态*/
			session.update(obj);
			/*将对象和Session加入到maps中*/
			maps.put(obj, session);
		}catch(Exception e){
			/*回滚事务*/
			session.getTransaction().rollback();
			if(maps.containsKey(obj))
				maps.remove(obj);
			session = null;
			e.printStackTrace();
		}
	}

	/**
	 * 通过Get方法查询“主键”获得对象，对象成为Persistent状态
	 * @param Class 调用类名.class
	 * @param Serializable
	 */
	
	public static Object queryByGet(Class c, Serializable s){
		Session session = null;
		Object obj = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			/*调用Get方法获取对象*/
		    obj = session.get(c, s);
		    //如果不存在，则返回null
		    if(obj == null){
		    	HibernateUtilsOld.close(session);
		    	return null;
		    }
		    /*将对象和Session加入到maps中*/
		    maps.put(obj, session);
		}catch(Exception e){
			if(maps.containsKey(obj))
				maps.remove(obj);
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}

	/**
	 * 通过Load方法查询“主键”获得对象，对象成为Persistent状态
	 * @param Class 调用类名.class
	 * @param Serializable
	 */
	public static Object queryByLoad(Class c, Serializable s){
		Session session = null;
		Object obj = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			//使用Load返回代理对象（像空壳），真正使用时才加载（Lazy load）
		    obj = session.load(c, s);
		    //如果对象不存在，则抛出ObjectNotFoundException
		    /*将对象和Session加入到maps中*/
		    maps.put(obj, session);
		}catch(ObjectNotFoundException e){
		    return null;
		}catch(Exception e){
			if(maps.containsKey(obj))
				maps.remove(obj);
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}
	
	/**
	 * 删除Persistent状态对象，与save和update不同
	 * 删除后数据变成Transient状态，无需再关闭
	 */
	public static void delete(Object obj){
		Session session = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			
			//删除一个Persistent状态对象
			if(obj!=null)
				session.delete(obj);
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtilsOld.close(session);
		}
	}
	
	/**
	 * 通过HQL查询，返回对象数组List
	 * 注意数组中的对象是Persistent状态
	 */
	public static List query(String hql, int startPos, int maxResults){
		Session session = null;
		List list = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			//创建一个Query接口
			Query query = session.createQuery(hql);
			//分页
			query.setFirstResult(startPos); //设置开始位置
			query.setMaxResults(maxResults); //查询的结果个数
			//读取到的数据放到List
			list = query.list();
			maps.put(list, session);
		}catch(Exception e){
			if(maps.containsKey(list))
				maps.remove(list);
			session.getTransaction().rollback();
			list = null;
			e.printStackTrace();
		}
		return list;
	}
}
