事务管理

四种并发访问数据库问题：
更新丢失：更新被另一个事务回滚掉了
脏读：读取到的数据后，该数据被另一个事务回滚掉了，导致使用了错误的数据
不可重复读：读取一次后，此时另一个事务更新，再执行相同的语句后读到的数据不同或丢失
幻读：结果集(数量)不同

隔离级别：
Read Uncommitted
Read Commiteed(多数数据库默认)
Repeatable Read
Serializable

使用悲观锁和乐观锁可以解决后面两个并发问题

HibernateUtils已经使用了事务：
Transaction tx = session.beginTransaction();
...
tx.commit()或tx.rollback()

Hibernate使用【LockMode】表示锁定模式

悲观锁：其它事务不能读写

乐观锁：由Hibernate控制的version来实现