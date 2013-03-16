package com.pugwoo.quartz.basic;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 2013年1月27日 下午07:40:39
 */
public class CronTriggerRunner {

	public static void main(String[] args) throws Exception {

		// 1. jobDetail
		JobDetail jobDetail = new JobDetail("job1_2", "jGroup1",
				SimpleJob.class);

		// 2. 创建CronTrigger，指定组及名称
		CronTrigger cronTrigger = new CronTrigger();
		cronTrigger.setName("trigger1_2");
		cronTrigger.setGroup("tgroup1");
		// 【有用】这个CronExpression可以获取到下一次执行的时间
		CronExpression cexp = new CronExpression("0/3 * * * * ?");
		cronTrigger.setCronExpression(cexp);

		// 3. 注册scheduler
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(jobDetail, cronTrigger);

		// 4. 启动
		scheduler.start();

		Thread.sleep(1000 * 10); // 10s

		// 修改crontab为每秒一次
		scheduler.rescheduleJob("trigger1_2", "tgroup1", new CronTrigger(
				"trigger1_2", "tgroup1", "job1_2", "jGroup1", "0/1 * * * * ?"));
	}
}
