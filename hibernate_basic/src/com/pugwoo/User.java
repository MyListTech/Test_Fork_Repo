package com.pugwoo;

import java.util.Date;

/**
 * 对于一个要保存至Database的对象，它必须是POJO： 1) 提供默认构造方法 2) 提供setter getter方法 3)
 * 提供一个标识属性（主键） 4) 使用非final的类，不要有final的数据成员
 * 
 * 具体查看 第 4 章 持久化类(Persistent Classes)
 * 
 * @author Pugwoo
 * 
 */
public class User {

	private Long id;

	private String name;

	private String password;

	private Date birth;

	private int age;

	/**
	 * 一般还要重写一下equals，如果要进行该类的两个对象的判断相等时
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		// 重要步骤
		Long id = ((User) obj).getId();
		if (id != null && this.getId() != null && this.getId().equals(id))
			return true;
		else
			return false;
	}

	public String toString() {
		return "id:" + id + ",name:" + name + ";the others.";
	}

	public User() {
	}

	public User(String name, String password, Date birth, int age) {
		this.name = name;
		this.password = password;
		this.birth = birth;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
