package com.pugwoo.quartz.basic;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 2013年1月27日 17:16:11
 */
public class SimpleTriggerRunner {

	public static void main(String[] args) throws SchedulerException,
			InterruptedException {
		//1. 创建一个JobDetail实例，指定SimpleJob
		JobDetail jobDetail = new JobDetail("job1_1", "jGroup1",
				SimpleJob.class);

		//2. 通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次，共运行100次
		SimpleTrigger simpleTrigger = new SimpleTrigger();
		simpleTrigger.setName("trigger1_1");
		simpleTrigger.setGroup("tgroup1");
		simpleTrigger.setStartTime(new Date());
		simpleTrigger.setRepeatInterval(2000);
		simpleTrigger.setRepeatCount(100);

		//3. 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		//4. 注册并进行调度,返回值是初次运行的时间
		Date runat = scheduler.scheduleJob(jobDetail, simpleTrigger);
		System.out.println("will run at:" + runat);

		//5. 调度启动
		scheduler.start();

		/**
		 * 调度start()之后还是可以调用scheduler.scheduleJob加入任务的
		 * 
		 * rescheduleJob方法可以重新给指定的name/group的job设置新的trigger
		 */

		Thread.sleep(10 * 1000);
		scheduler.shutdown(true);
		// display some stats about the schedule that just ran
		SchedulerMetaData metaData = scheduler.getMetaData();
		System.out.println("Executed " + metaData.getNumberOfJobsExecuted()
				+ " jobs.");
	}

}
