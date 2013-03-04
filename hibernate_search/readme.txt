Hibernate采用Lucene的全文检索系统

文档：http://docs.jboss.org/hibernate/search/3.3/reference/en-US/html/

*需要Hibernate Annotations支持

安装步骤：
1) http://sourceforge.net/projects/hibernate/files/hibernate-search/ 下载
导入hibernate-search.jar和lucene-core.jar两个文件

2) 配置hibernate.cfg.xml文件

3) 为类作注解，只支持注解

【注意】只有Hibernate对应的Field才会自动写入Index