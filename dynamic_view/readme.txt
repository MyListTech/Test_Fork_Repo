依赖：Hibernate和Spring
      Jackson

【不】支持動態更新

dynamic view不支持*-to-many的情况，这些都可以转换为*-to-one

二级关联：
select o.id,o.school.name,o.school.address.name from Student o left join o.school.address


待解决：read的Order

CRUD: create read update delete