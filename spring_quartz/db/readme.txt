使用数据库，可以让quartz做到集群，注意做测试时每次先清空表

对于mysql数据库，执行quartz.tar.gz包的docs/dbTables/tables_mysql.sql
一个会创建12张表,其中

1) qrtz_cron_triggers 存放cron trigger的信息
   做了个测试，单独直接修改数据库的cron expr是【不会】生效的，但是重启就可以
   这说明reschedule肯定还修改了数据库里面的某个字段
2) qrtz_fired_triggers 记录已经被触发的机器和最近一次时间
3) qrtz_job_details 记录注册的job detail
4) qrtz_triggers 保存trigger和jobDetail的关系
    里面trigger_state表示任务状态：ACQUIRED运行中

这是真正的集群，每个节点（以进程为单位，同一台机器可多个）都跑quartz
通过数据库同步，如果一个节点挂了，其它节点会自动重启。

这里有个不足：无法指定哪个job在哪台机器上运行，但系统会保证当前只有1个在执行

参考：http://sundoctor.iteye.com/blog/441951
http://wang-heaven.iteye.com/blog/1554634 