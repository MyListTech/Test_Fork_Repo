package com.pugwoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * ‎2011‎年‎1‎月‎29‎日，‏‎21:57:11
 *  得先创建一个表： create table user_jdbc( id integer primary
 * key, username char(10), password char(20) );
 */
public class JdbcTemplateTest extends TestCase {

	ApplicationContext factory = new ClassPathXmlApplicationContext(
			"applicationContext-jdbc.xml");
	
	// 插入测试
	public void testInsert() {

		JdbcTemplate jdbcTemplate = (JdbcTemplate) factory
				.getBean("jdbcTemplate");// 获得JdbcTemplate
		String INSERT = "insert into user_jdbc values(?,?,?)";
		for (int i = 1; i < 100; i++)
			jdbcTemplate.update(INSERT, new Object[] { new Integer(i),
					"pugwoo", "063721" + Integer.toString(i) });
	}

	// 删除测试
	public void testDelete() {
		
		JdbcTemplate jdbcTemplate = (JdbcTemplate) factory
				.getBean("jdbcTemplate");// 获得JdbcTemplate
		String DELETE = "delete from user_jdbc";
		jdbcTemplate.execute(DELETE);
	}

	// 测试查询
	@SuppressWarnings("unchecked")
	public void testQuery() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) factory
				.getBean("jdbcTemplate");// 获得JdbcTemplate
		String QUERY = "select id,username,password from user_jdbc where id<?";
		List result = jdbcTemplate.query(QUERY,
				new Object[] { new Integer(10) }, new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getInt(1));
						user.setUsername(rs.getString(2));
						user.setPassword(rs.getString(3));
						return user;
					}
				});

		for (int i = 0; i < result.size(); i++) {
			User user = (User) result.get(i);
			System.out.println(user.getId());
		}
	}

	// 测试查询
	// 另一种用法，将值赋到query外面
	@SuppressWarnings("unchecked")
	public void testQuery2() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) factory
				.getBean("jdbcTemplate");// 获得JdbcTemplate
		String QUERY = "select id,username,password from user_jdbc where id=?";
		final User user = new User(); // User放在外面
		jdbcTemplate.query(QUERY, new Object[] { new Integer(10) },
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						user.setId(rs.getInt(1));
						user.setUsername(rs.getString(2));
						user.setPassword(rs.getString(3));
						return null;
					}
				});

		System.out.println(user.getId());

	}
}
