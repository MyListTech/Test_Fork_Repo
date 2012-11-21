package com.pugwoo.service;

import java.io.Serializable;

/**
 * 2012年11月21日 10:54:30 这是一个自定的结构，专门不继承序列化接口
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private String name;

	private int score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
