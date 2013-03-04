package test;

import com.pugwoo.hibernate.HibernateUtils;

import model.User;

import junit.framework.TestCase;

public class Test extends TestCase {

	public void testSave() {
		User user = new User();
		user.setUsername("pugwoo");

		HibernateUtils.save(user);
		HibernateUtils.commit();

		HibernateUtils.execute("update User set username='in'");
		HibernateUtils.commit();

		HibernateUtils.executeSQL("update user set username='out'");
		HibernateUtils.commit();
	}
}
