package com.pugwoo.old;

import hibernate.HibernateUtilsOld;

import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pugwoo.User;

public class BasicTest extends TestCase{
	public void testSession(){

		Session session = null;
		Transaction tx = null;
		User user = null;
		try{
			session = HibernateUtilsOld.getSession();
			tx = session.beginTransaction();
			
			//Transient Status
			user = new User();
			user.setName("pugwoo");
			user.setPassword("123");
			user.setBirth(new Date());
			user.setAge(10);
			
			//Persistent Status
			session.save(user); //insert  ... 此时user.id已有值
			//Persistent状态：对象和数据库同时改变，脏数据处理
			user.setName("nick"); //update ...
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtilsOld.close(session);
		}
		//Detached Status
		user.setName("karen");
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			
			//update a detached object
			//变成Persistent状态
			session.update(user);
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			//又变成Detached状态
			HibernateUtilsOld.close(session);
		}
	}
	/**
	 * 从数据库读不存在的数据
	 */
	public void testReadByGetMethod2(){
		Session session = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			
			//查询select，user是Persistent状态
			User user = (User)session.get(User.class, "xxxx");
			//如果不存在，则返回null
			if(user == null)
				System.out.println("数据不存在");
			
			//改名，此时会更新数据库
			user.setName("pig");
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtilsOld.close(session);
		}
	}
	/**
	 * 使用Load方法从数据库读数据
	 */
	public void testReadByLoadMethod(){
		Session session = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			
			//使用Load返回代理对象（像空壳），真正使用时才加载（Lazy load）
			User user = (User)session.load(User.class, "40288d592c3605ce012c3605d1e30000");
			//此时才加载，才发出select语句
			//如果对象不存在，则抛出ObjectNotFoundException
			System.out.println(user.getName());
			
			//改名，此时会更新数据库
			user.setName("petpet");
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtilsOld.close(session);
		}
	}
	/**
	 * 使用Get方法从数据库读数据
	 */
	public void testReadByGetMethod(){
		Session session = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			
			//查询select，user是Persistent状态
			User user = (User)session.get(User.class, "40288d592c3605ce012c3605d1e30000");
			System.out.println(user.getName());
			
			//改名，此时会更新数据库
			user.setName("pig");
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtilsOld.close(session);
		}
	}
	/**
	 * 删除数据库数据
	 */
	public void testDelete(){
		Session session = null;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			
			//查询select，user是Persistent状态
			User user = (User)session.get(User.class, "40288d592c3605ce012c3605d1e30000");
			
			//删除一个Persistent状态对象
			if(user!=null)
				session.delete(user);
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtilsOld.close(session);
		}
	}
}
