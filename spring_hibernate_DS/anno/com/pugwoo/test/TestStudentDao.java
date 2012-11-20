package com.pugwoo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.dao.StudentDao;
import com.pugwoo.model.Student;

public class TestStudentDao {

	public static void main(String[] args) {

		// 一定要用ApplicationContext，不然数据库事务方面会有问题
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-hibernate-anno.xml");
		
		StudentDao studentDao = (StudentDao) factory.getBean("studentDao");
		
		Student student = new Student();
		student.setName("pugwoo");
		student.setScore(99);
		
		studentDao.add(student);
		
	}

}
