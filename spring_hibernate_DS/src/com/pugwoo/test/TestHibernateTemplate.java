package com.pugwoo.test;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.pugwoo.domain.User;

/**
 * 该类专门测试HibernateTempalte
 * ‎2010‎年‎12‎月‎18‎日，‏‎16:39:04
 */
@SuppressWarnings("unchecked")
public class TestHibernateTemplate extends TestCase {

	private static String[] paths = new String[] {
		"classpath*:applicationContext-hibernate-xml.xml" };
	
	protected static ApplicationContext applicationContext = null;
	protected static HibernateTemplate hibernateTempalte = null;
	
	static{
		applicationContext = new ClassPathXmlApplicationContext(paths);
		hibernateTempalte = new HibernateTemplate((SessionFactory)applicationContext.getBean("sessionFactory"));
	}
	
	public void testFind(){
		List<User> list;
		//执行hql语句
		list = hibernateTempalte.find("from User");
		System.out.println(list);
		
		//执行hql，绑定一个?
		list = hibernateTempalte.find("from User u where u.name=?","pugwoo");
		System.out.println(list);
		
		//执行hql，绑定多个?
		list = hibernateTempalte.find("from User u where u.id=? and u.name=?",
				new Object[]{1, "pugwoo"});
		System.out.println(list);
		
		DetachedCriteria criteria;
		//通过criteria执行条件查询
		criteria = DetachedCriteria.forClass(User.class);
		list = hibernateTempalte.findByCriteria(criteria);
		System.out.println(list);
		
		//通过criteria实现分页功能,开始位置是从0开始数起的
		criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Property.forName("name").eq("pugwoo")); //名字相等
		list = hibernateTempalte.findByCriteria(criteria,1,1);
		System.out.println(list);
		
		//通过Example查询and条件
		User user = new User();
		user.setName("pugwoo");
		list = hibernateTempalte.findByExample(user); //可以用分页功能
		System.out.println(list);
		
		//通过NamedParam查询
		String queryString = "select count(*) from User u " +
				"where u.name=:NAME";
		list = hibernateTempalte.findByNamedParam(queryString, "NAME", "pugwoo"); //可多个
		System.out.println(list);
		
	}
	

}
