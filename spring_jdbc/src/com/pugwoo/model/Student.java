package com.pugwoo.model;

/**
 * 2013年3月5日 18:08:59
 * 
CREATE TABLE `Student` (
`id`  int NOT NULL ,
`name`  varchar(32) NOT NULL ,
`age`  int NULL ,
PRIMARY KEY (`id`)
)
;
 */
public class Student {

	private Long id;
	private String name;
	private Long age;
	
	@Override
	public String toString() {
		return "id:" + id + ",name:" + name + ",age:" + age;
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}
