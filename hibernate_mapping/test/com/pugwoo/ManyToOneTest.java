package com.pugwoo;

import java.util.HashSet;

import junit.framework.TestCase;

import hibernate.HibernateUtils;
import com.pugwoo.onemany.School;
import com.pugwoo.onemany.Student;

/**
 * 2011年4月10日, 0:13:43
 */
public class ManyToOneTest extends TestCase{

	/**
	 * 演示单向
	 */
	public void testManyToOne1(){
		School school = new School();
		school.setSchoolName("SYSU");
		HibernateUtils.save(school); //如果不是级联，则需要先保存school
		
		Student student = new Student();
		student.setStuName("pugwoo");
		student.setSchool(school);
		HibernateUtils.save(student);
		
		HibernateUtils.commit();
	}
	/**
	 * 演示双向
	 */
	public void testManyToOne2(){
		School school = new School();
		school.setSchoolName("SYSU");
		HibernateUtils.save(school); //如果不是级联，则需要先保存school
		
		Student student = new Student();
		student.setStuName("pugwoo");
		student.setSchool(school);
		Student student2 = new Student();
		student2.setStuName("karen");
		student2.setSchool(school);
		
		HibernateUtils.save(student);
		HibernateUtils.save(student2);
		
		HashSet<Student> students = new HashSet<Student>();
		students.add(student);
		students.add(student2);
		school.setStudents(students);
		
		HibernateUtils.update(school);
		
		HibernateUtils.commit();
	}
}
