/* 
 * Copyright 2005 - 2009 Terracotta, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */

package com.pugwoo.quartz.basic;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * This Example will demonstrate all of the basics of scheduling capabilities
 * of Quartz using Simple Triggers.
 * 
 * @author Bill Kratzer
 * 
 * 2013年3月16日 10:06:05
 * 这个一开始时每2秒执行一次，计划执行30次
 * 但是在10来秒之后，修改为每1秒执行一次，一共10次
 * quartz会在修改后执行10次后结束
 */
public class SimpleTriggerExample {

    
    public void run() throws Exception {
        Logger log = LoggerFactory.getLogger(SimpleTriggerExample.class);

        log.info("------- Initializing -------------------");
        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete --------");
        log.info("------- Scheduling Jobs ----------------");

        // jobs can be scheduled before sched.start() has been called
        // get a "nice round" time a few seconds in the future...
        long ts = TriggerUtils.getNextGivenSecondDate(null, 5).getTime();

        sched.start();
        log.info("------- Started Scheduler -----------------");

        // jobs can also be scheduled after start() has been called...
        // job7 will repeat 20 times, repeat every 3 sec
        JobDetail job = new JobDetail("job7", "group1", SimpleJob.class);
        SimpleTrigger trigger = new SimpleTrigger("trigger7", "group1", "job7", "group1",
                new Date(ts), null, 30, 2000L);
        Date ft = sched.scheduleJob(job, trigger);
        log.info(job.getFullName() +
                " will run at: " + ft +  
                " and repeat: " + trigger.getRepeatCount() + 
                " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
        
        log.info("------- Waiting 15 seconds... --------------");
        try {
            Thread.sleep(15L * 1000L); 
        } catch (Exception e) {
        }

        // jobs can be re-scheduled...  
        // job 7 will run immediately and repeat 10 times for every second
        log.info("------- Rescheduling... --------------------");
        trigger = new SimpleTrigger("trigger7", "group1", "job7", "group1", 
                new Date(), null, 10, 1000L);
        ft = sched.rescheduleJob("trigger7", "group1", trigger);
        log.info("job7 rescheduled to run at: " + ft);
        
        log.info("------- Waiting five minutes... ------------");
        try {
            Thread.sleep(300L * 1000L); 
        } catch (Exception e) {
        }

        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");

        // display some stats about the schedule that just ran
        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws Exception {

        SimpleTriggerExample example = new SimpleTriggerExample();
        example.run();

    }

}
