Fringe问题：id不能用native，得用increment，否则还没commit就已经写入数据库了
该问题发现于2010年11月26号晚

这个项目将涵盖：

一、hibernate基本配置

1、将workspace中的lib添加至Eclipse的User Library中，以便多个项目使用
       在项目中引入这些Library

2、创建hibernate.cfg.xml，可以复制简单模板
        该文件用于配置全局设置，包括数据库用户名密码连接地址、hbm.xml文件位置等

3、对于一个类，在同目录下配置其hbm.xml文件
       该文件完成对象到表的映射配置
       
       
思想：对象的三种状态Transient Persistent Detached
transient状态的特征？
	* 在数据库中没有与之匹配的数据
	* 没有纳入session的管理
persistent状态的特征？
	* persistent状态的对象在数据库中有与之匹配的数据
	* 纳入了session的管理
	* 在清理缓存（脏数据检查）的时候,会和数据库同步
detached状态的特征？
	* 在数据库中有与之匹配的数据
	* 没有纳入session的管理	 		