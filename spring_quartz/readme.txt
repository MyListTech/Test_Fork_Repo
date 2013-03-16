强烈建议使用【1.x版本】

Quartz表达式

包括7个字段：秒 分 小时 月内日期 月 周内日期 年(可选)

字段特殊符号：
1)星号（*）字符是通配字符，表示该字段可以接受任何可能的值。
2)逗号, 多个值列举，减号- 表示一段范围的数字
3)反斜线（/）字符表示增量值。例如，在秒字段中“5/15”代表从第 5 秒开始，每 15 秒一次。

字段 允许值 允许的特殊字符 
秒 0-59 , - * / 
分 0-59 , - * / 
小时 0-23 , - * / 
日期 1-31 , - * ? / L W C 
月份 1-12 或者 JAN-DEC , - * / 
星期 1-7 或者 SUN-SAT , - * ? / L C # 
年（可选） 留空, 1970-2099 , - * / 

【重要概念】
Job：是一个接口，只有一个方法void execute(JobExecutionContext context)，开发者实现该接口定义运行任务
    JobExecutionContext类提供了调度上下文的各种信息。Job运行时的信息保存在JobDataMap实例中；
JobDetail：Quartz在每次执行Job时，都重新创建一个Job实例。由name和group标识
Trigger：是一个类，描述触发Job执行的时间触发规则。由name和group标识
Scheduler：代表一个Quartz的独立运行容器，Trigger和JobDetail可以注册到Scheduler中，
        两者在Scheduler中拥有各自的组及名称，组及名称是Scheduler查找定位容器中某一对象的依据，
   Trigger的组及名称必须唯一，JobDetail的组和名称也必须唯一（但可以和Trigger的组和名称相同，因为它们是不同类型的）。
      【Trigger和jobdetail都通过组+名称唯一标识，有个默认组】
ThreadPool：Scheduler使用一个线程池作为任务运行的基础设施，任务通过共享线程池中的线程提高运行效率。

Job有一个StatefulJob子接口，代表有状态的任务，该接口是一个没有方法的标签接口，其目的是让Quartz知道任务的类型
        无状态任务在执行时拥有自己的JobDataMap拷贝,有状态任务共享共享同一个JobDataMap实例
        无状态的Job可以并发执行，而有状态的StatefulJob不能并发执行

Quartz拥有完善的事件和监听体系，大部分组件都拥有事件，如任务执行前事件、任务执行后事件、触发器触发前事件、
触发后事件、调度器开始事件、关闭事件等等，可以注册相应的监听器处理感兴趣的事件。

参考这篇：应用级部署：http://my.oschina.net/wangyongqing/blog/53938

可以将运行信息保存到数据库中，需要执行quartz自带的sql语句建立8个表
然后在quartz.properties里面写入数据库连接信息
最后通过查询数据库，就可以查询得到quartz当前的运行状态！

我们还需要一个提供用户配置执行的后台管理系统，用户可以配置执行的job名称和crontab表达式，【可以随时修改生效！】
思路1：将cron表达式存在数据库，每10秒钟查询数据库，比对容器中的表达式有无变化，
             有则调用scheduler.rescheduleJob重新设置，参考：http://my.oschina.net/yangphere/blog/56028
     这里有个不错的例子：http://sundoctor.iteye.com/blog/399980 
     http://sundoctor.iteye.com/blog/441951
     
这篇文章还没看：http://my.oschina.net/johnChina/blog/89403