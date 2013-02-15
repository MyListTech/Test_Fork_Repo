package com.pugwoo.quartz;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerRunner {

	public static void main(String[] args) throws Exception {
		
		// 1. jobDetail
		JobDetail jobDetail = new JobDetail("job1_2", "jGroup1",
				SimpleJob.class);

		// 2. 创建CronTrigger，指定组及名称
		CronTrigger cronTrigger = new CronTrigger("trigger1_2", "tgroup1");
		CronExpression cexp = new CronExpression("0/5 * * * * ?");
		cronTrigger.setCronExpression(cexp);

		// 3. 注册scheduler
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(jobDetail, cronTrigger);

		// 4. 启动
		scheduler.start();
	}
}
