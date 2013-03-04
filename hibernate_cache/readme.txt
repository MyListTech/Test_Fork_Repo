本项目采用EHCache(hibernate自带)：需要加入ehcache.jar包和common.logging
1) hibernate.cfg.xml添加cache配置属性
2) 编写ehcache.xml
3) 修改User.hbm.xml
4) 测试之

缓存级别：
事务级别：每个事务有自己的缓存数据，事务结束，缓存清空
应用(进程)级别：

Hibernate的缓存：
一级缓存：事务级别，hibernate自行控制
一级缓存常用方法：session.evict(obj)从缓存中移除缓存对象obj
                 session.clear()移除所有缓存对象
                 还有contgains() flush() setReadOnly()几个方法
                 
二级缓存：先查找一级，再查找二级
二级缓存由第三方提供：
Hashtable
EHCache 
OSCache 推荐
SwarmCache 推荐，支持集群
JBossCache 支持分布式

二级缓存策略：
只读
读写
不严格读写：读多写少
事务缓存，只能用在JPA中