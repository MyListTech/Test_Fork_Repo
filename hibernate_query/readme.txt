最详细的查询教程：http://docs.jboss.org/hibernate/core/3.3/reference/en/html/queryhql.html

四种连接查询：

交叉连接  from Student s,Teacher t
内连接      from Studnet s inner join s.teacher  (默认)
左外连接  select s from Student s left outer join s.teacher
右外连接  from Student s right outer join s.teacher


命名HQL：即在hbm.xml中定义一hql语句，然后通过session获取到该query语句对应的Query对象。

使用MyEclipse自带的HQL查询工具：首先要为Project添加Hibernate Capabilities，然后使用HQL Editor

select o.id,o.school.name as school,o.teacher.name from User o left join o.school left join o.teacher where o.id=3