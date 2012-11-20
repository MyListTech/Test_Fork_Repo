package com.pugwoo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@SuppressWarnings("unchecked")
public class ActionListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ActionEvent){
			System.out.println(event.toString());
		}
		
	}

}
