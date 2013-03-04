package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.hibernate.view.Container;

import junit.framework.TestCase;

public class FilterTest extends TestCase {
	private static ApplicationContext factory;

	private static Container viewContext;

	static {
		factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		viewContext = (Container) factory.getBean("viewContainer");
		viewContext.setEsc('`');
	}
}
