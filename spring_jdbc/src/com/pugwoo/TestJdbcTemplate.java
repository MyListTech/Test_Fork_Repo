package com.pugwoo;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pugwoo.model.Student;

import dao.StudentRowMapper;

/**
 * 2013年3月5日 18:06:23
 */
@SuppressWarnings("unchecked")
public class TestJdbcTemplate extends TestCase {

	private static JdbcTemplate jdbcTemplate;

	static {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) factory.getBean("jdbcTemplate");
	}

	public void testCount() {
		long count = jdbcTemplate.queryForLong("select count(*) from student");
		System.out.println("count:" + count);
	}

	public void testInsert() {
		jdbcTemplate.update("insert into student(id,name,age) values(?,?,?)",
				new Object[] { 1, "nick", 26 });
		// 目前jdbcTemplate还不支持jdbcTemplate.save(Object obj)这样的类似hibernate的方法
	}
	
	// 这种是需要自己写rowMapper类的
	public void testQuery() {
		List<Student> list = jdbcTemplate.query(
				"select * from student where id<?", new Object[] { 10 },
				new StudentRowMapper());
		for(Student student : list) {
			System.out.println(student);
		}
	}
	
	// 这种是不需要自己写rowMapper
	public void testQuery2() {
		List<Student> list = jdbcTemplate.query(
				"select * from student where id<?", new Object[] { 10 },
				new BeanPropertyRowMapper(Student.class));
		for(Student student : list) {
			System.out.println(student);
		}
	}

	public static void main(String[] args) {

	}

}
