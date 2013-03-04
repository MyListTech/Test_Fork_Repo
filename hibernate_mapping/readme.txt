该项目演示Hibernate的基本映射

该项目实例：学校School，学生Student，课程Course；

*
学生和学校是多对一，该例演示的是双向（可随便删除一个变成单向）
凡是多对一或是一对多的，都在多的那一张表里的一列存放映射
<many-to-one>会在多的一端加入一个外键，指向一的一端，这个外键是由<many-to-one>
中的column属性定义的，如果忽略了这个属性那么默认的外键与实体的属性一致
<many-to-one>标签的定义示例：
	 * <many-to-one name="group" column="groupid"/>

	 
*
一对一：有【主键关联】和【外键关联】两种，均有【单向】和【双向】两种
Person和IdCard演示主键关联1对1
			//不会出现TransientObjectException异常
			//因为一对一主键关联映射中，默认了cascade属性
			
一对唯一外键关联映射是多对一关联映射的特例
可以采用<many-to-one>标签，指定多的一端的unique=true，这样就限制了多的一端的多重性为一
通过这种手段映射一对一唯一外键关联

双向：
一对一唯一外键关联双向，需要在另一端（idcard），添加<one-to-one>标签，指示hibernate如何加载
其关联对象，默认根据主键加载person，外键关联映射中，因为两个实体采用的是person的外键维护的关系，
所以不能指定主键加载person，而要根据person的外键加载，所以采用如下映射方式：
<one-to-one name="person" property-ref="idCard"/>


* 多对多
两个对象多对多需要3个表

双向的尚未弄

多对多可以拆分为两个多对一


* 继承
三种解决方案：
父子类共用一个表：(性能最佳，但不能对某些字段进行not null限制)
每个具体类一个表：简单，但效率低，需要查询两次
每个子类一个表：支持多态查询！效率较低