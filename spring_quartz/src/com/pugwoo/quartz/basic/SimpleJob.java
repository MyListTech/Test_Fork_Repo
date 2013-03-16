package com.pugwoo.quartz.basic;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 2013年1月27日 17:13:55
 * 简单的Job，必须实现Job接口
 */
public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {

		System.out.println("--------");
		System.out.println("trigger{name:" + jobCtx.getTrigger().getName()
				+ ";group:" + jobCtx.getTrigger().getGroup() + "}");
		System.out.println(jobCtx.getJobDetail().getJobClass());
		System.out.println("time:" + (new Date()));
		
		// 这里可以包含任何的执行代码
	}

}
