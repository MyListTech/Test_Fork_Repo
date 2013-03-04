该项目来自于王勇的Spring，该项目还演示了Logging。

使用current session就能保证在多个DAO中使用同一个session，当出错时能全部回滚。

这样的Session是线程安全的

spring和hibernate的集成（编程式事务）

1、openSession和getCurrentSession的区别？
	* openSession必须关闭，currentSession在事务结束后自动关闭
	* openSession没有和当前线程绑定，currentSession和当前线程绑定
	
2、如果使用currentSession需要在hibernate.cfg.xml文件中进行配置：
	* 如果是本地事务（jdbc事务）
		<property name="hibernate.current_session_context_class">thread</property>
	* 如果是全局事务（jta事务）
	<property name="hibernate.current_session_context_class">jta</property>
	
	
Spring提供类似的事务管理