package test;

import model.CompositeId;
import model.School;
import model.Student;

import com.pugwoo.hibernate.HibernateUtils;

public class Init {

	public static void main(String[] args) {
		
		School school = new School();
		school.setName("czh");
		
		Student user = new Student();
		user.setName("pugwoo");
		user.setNumber("12345");
		user.setSchool(school);
		
		Student user2 = new Student();
		user2.setName("karen");
		
		CompositeId compositeId = new CompositeId();
		compositeId.setId(80L);
		compositeId.setName("SYsu edu");
		
		HibernateUtils.save(compositeId);
		HibernateUtils.save(school);
		HibernateUtils.save(user2);
		HibernateUtils.save(user);
		HibernateUtils.commit();
	}

}
