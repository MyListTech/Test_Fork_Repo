package com.pugwoo;

import model.School;
import model.Teacher;
import model.User;

import hibernate.HibernateUtils;

/**
 * 2011年4月20日 上午10:49:32
 */
public class Init {

	public static void main(String[] args) {

		School school1 = new School();
		school1.setName("SZH");

		School school2 = new School();
		school2.setName("CZH");

		Teacher teacher = new Teacher();
		teacher.setName("mr");

		User user1 = new User();
		user1.setName("pugwoo");
		user1.setSchool(school1);
		user1.setTeacher(teacher);

		User user2 = new User();
		user2.setName("nick");
		user2.setSchool(school1);

		User user3 = new User();
		user3.setName("karen");
		user3.setSchool(school2);
		user3.setTeacher(teacher);

		User user4 = new User();
		user4.setName("unsub");

		HibernateUtils.save(teacher);
		HibernateUtils.save(school1);
		HibernateUtils.save(school2);
		HibernateUtils.save(user1);
		HibernateUtils.save(user2);
		HibernateUtils.save(user3);
		HibernateUtils.save(user4);

		HibernateUtils.commit();
	}

}
