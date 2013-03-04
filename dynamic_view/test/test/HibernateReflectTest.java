package test;

import junit.framework.TestCase;

import org.hibernate.mapping.PersistentClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.hibernate.HibernateUtils;
import com.pugwoo.hibernate.dao.HibernateReflect;

public class HibernateReflectTest extends TestCase {

	private static HibernateReflect reflect = new HibernateReflect(
			HibernateUtils.getConfiguration());

	public static void main(String[] args) {
	     PersistentClass p = reflect.getPersistentClass("CompositeId");
	     // 正确获得主键的方式
	     System.out.println(p.getKey());
	     System.out.println(p.getPropertyClosureIterator());
	     
	     PersistentClass p2 = reflect.getPersistentClass("Student");
	     System.out.println(p2.getKey());
	     
	     System.out.println(reflect.getAllColumns("CompositeId"));
	     System.out.println(reflect.getAllColumns("Student"));
	     System.out.println(reflect.getAllColumns("School"));
	     
	     System.out.println(reflect.isPersistentProperty("Student", "school.address"));
	     System.out.println(reflect.isPersistentId("Student", "school.address.id"));
	}

	// 基本测试
	public void basicTest() {
		// hibernate impl测试【通过】
		HibernateReflect r1 = new HibernateReflect(HibernateUtils
				.getConfiguration());
		PersistentClass p1 = r1.getPersistentClass("Student");
		System.out.println(p1);

		// spring impl 测试【通过】
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");
		HibernateReflect r2 = (HibernateReflect) factory.getBean("hibernateReflect");
		
		PersistentClass p2 = r2.getPersistentClass("Student");
		System.out.println(p2);
	}

}
