package com.pugwoo.quartz.service;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import com.pugwoo.quartz.SerializableJob;

/**
 * 2013年3月16日 12:48:16
 */
@SuppressWarnings("serial")
@Service
public class SimpleJob implements SerializableJob {

	@Override
	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {

		System.out.println("--------");
		System.out.println("trigger{name:" + jobCtx.getTrigger().getName()
				+ ";group:" + jobCtx.getTrigger().getGroup() + "}");
		System.out.println(jobCtx.getJobDetail().getJobClass());
		System.out.println("time:" + (new Date()));

	}

}
