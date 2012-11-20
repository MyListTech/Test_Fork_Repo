2011年1月29日 下午09:41:24

该项目内容来自In Action一书第五章：使用数据库

得先创建数据库inaction_databases

*DataSource
推荐使用DBCP连接池或JNDI，不推荐直接用JDBC Driver

*Template
模板，常用有JdbcTemplate和HibernateTemplate

实现了HibernateTemplate和JdbcTemplate共用，推荐使用这里的配置