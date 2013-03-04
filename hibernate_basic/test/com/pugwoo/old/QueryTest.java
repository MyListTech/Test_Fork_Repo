package com.pugwoo.old;

import hibernate.HibernateUtilsOld;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pugwoo.User;

public class QueryTest extends TestCase {
	
	@SuppressWarnings("unchecked")
	public void testQuery(){
		Session session = null;
		Query query;
		try{
			session = HibernateUtilsOld.getSession();
			session.beginTransaction();
			//创建一个Query接口
			query = session.createQuery("from User");
			//分页
			query.setFirstResult(0); //设置开始位置
			query.setMaxResults(2); //查询的结果个数
			
			//读取到的数据放到List
			List userList = query.list();
			for(Iterator iter = userList.iterator(); iter.hasNext(); ){
				User user = (User)iter.next();
				System.out.print(user.getId() + ":");
				System.out.println(user.getName());
				//会修改密码
				user.setPassword("1111");
			}
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtilsOld.close(session);
		}
	}
}
