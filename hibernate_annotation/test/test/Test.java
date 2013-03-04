package test;

import model.User;

import hibernate.HibernateUtils;

/**
 * 2011年4月14日 下午11:22:02
 */
public class Test {

	public static void main(String[] args) {

		User user = new User();
		user.setUsername("pugwoo");

		HibernateUtils.save(user);
		HibernateUtils.commit();
	}

}
