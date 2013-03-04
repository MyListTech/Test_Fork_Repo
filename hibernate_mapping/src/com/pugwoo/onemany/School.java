package com.pugwoo.onemany;

import java.util.Set;

public class School {

	private int id;

	private String schoolName;
	
	/**
	 * school中的学生，可删除student中的school属性变成单向
	 */
	private Set<Student> students;

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
