package com.pugwoo.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 2013年1月27日 17:13:55
 * 
 * 简单的Job，必须实现Job接口
 */
public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {

		System.out.println(jobCtx.getTrigger().getName()
				+ " triggered. time is:" + (new Date()));
		
		// 这里可以包含任何的执行代码
	}

}
