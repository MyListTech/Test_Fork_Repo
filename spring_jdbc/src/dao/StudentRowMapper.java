package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pugwoo.model.Student;

/**
 * 2013年3月5日 18:17:28
 * 如果只是简单的对应关系的话，可以使用BeanPropertyRowMapper来实现ORM功能
 */
@SuppressWarnings("unchecked")
public class StudentRowMapper implements RowMapper {

	@Override
	public Student mapRow(ResultSet res, int index) throws SQLException {
		Student student = new Student();
		student.setId(res.getLong("id"));
		student.setName(res.getString("name"));
		student.setAge(res.getLong("age"));
		return student;
	}

}
