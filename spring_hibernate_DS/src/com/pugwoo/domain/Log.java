package com.pugwoo.domain;

import java.util.Date;

public class Log {

	private int id;
	//日志类别，日志一般起到一个不可否认性
	//操作日志、安全日志、事件日志
	private String type;
	private String detail;
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
