package com.pugwoo.bean;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {

	// 匹配模式，我们自己加上的，增加程序灵活性
	private String pattern;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			//我们自己的转换函数，将String转换成要的对象
			Date date = new SimpleDateFormat(pattern).parse(text);
			//将对象加入到类里面
			this.setValue(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(text);
		}
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
