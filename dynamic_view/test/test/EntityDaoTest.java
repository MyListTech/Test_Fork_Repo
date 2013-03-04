package test;

import java.util.List;

import junit.framework.TestCase;
import model.Address;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.hibernate.HibernateUtils;
import com.pugwoo.hibernate.dao.EntityDao;
import com.pugwoo.hibernate.utils.ViewUtils;

public class EntityDaoTest extends TestCase {

	@SuppressWarnings("unchecked")
	public void testBasic() {
		
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");
		EntityDao ed = (EntityDao) factory.getBean("entityDao");
		Address addr = new Address();
		addr.setName("spring");
		
		ed.save(addr);
		
		List list = ed.query("SELECT o.id,o.name,o.school.id,o.school.name FROM model.Student o left join o.school", -1, -1);
		
		ViewUtils.print(list);
		
		//【重大】
		List list2 = HibernateUtils.query("SELECT o.id,o.name,o1.name FROM model.Student o left join o.school as o1");
		ViewUtils.print(list2);
		
		// 【重大2】
		List list3 = HibernateUtils.query("SELECT o.id,o.name,o0.name FROM model.Student o LEFT JOIN o.school AS o0 WHERE o0.name is null");
		ViewUtils.print(list3);

	}
	
}
