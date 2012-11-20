package com.pugwoo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * ‎2011‎年‎1‎月‎29‎日，‏‎22:01:34
 */
public class HibernateTemplateTest {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-jdbc.xml");
		HibernateTemplate hibernateTemplate = (HibernateTemplate) factory
				.getBean("hibernateTemplate");

		User user = new User();
		user.setId(123);
		user.setUsername("karen");
		user.setPassword("xxxx");
		
		hibernateTemplate.save(user);
	}

}
