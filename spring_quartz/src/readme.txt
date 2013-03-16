演示simpleTrigger和cronTrigger

不依赖spring容器，写死配置

【实用】
jobDetail有个JobDataMap可以用于附带数据，在job execute时通过JobExecutionContext获取到

当job运行是抛出JobExecutionException，
可以控制setRefireImmediately或setUnscheduleAllTriggers

通过实现InterruptableJob接口实现可以【暂停】的job任务！

有个jobListener可以在job执行的前后做些事情，可以利用这个在1个job里trigger另一个job

