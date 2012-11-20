package com.pugwoo;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class ActionEvent extends ApplicationEvent {

	public ActionEvent(Object source) {
		super(source);
	}

}
