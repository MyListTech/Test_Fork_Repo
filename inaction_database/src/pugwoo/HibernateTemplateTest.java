package pugwoo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateTemplateTest {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		HibernateTemplate hibernateTemplate = (HibernateTemplate) factory.getBean("hibernateTemplate");
		User user = new User();
		user.setId(123);
		user.setUsername("karen");
		user.setPassword("xxxx");
		hibernateTemplate.save(user);

	}

}
