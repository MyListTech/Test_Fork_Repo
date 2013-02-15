package demo;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 2012年12月30日 10:37:25
 * JFinal的Db+ActiveRecord其本质就是一个map，不是ORM!
 * 其功能比dbutils还少
 * 
 * 假设建有表：
CREATE TABLE `Student` (
`id`  integer NOT NULL AUTO_INCREMENT,
`name`  varchar(32) NOT NULL ,
`score`  float NULL ,
PRIMARY KEY (`id`)
)
;
 */
public class DAO {

	/**
	 * 插入一条信息，提供列和值，并非ORM
	 */
	public void insertStudent(String name, float score) {
		Record student = new Record().set("name", name).set("score", score);
		// 表名,record
		Db.save("student", student);
	}

	// 单元测试
	public static void main(String[] args) {
	    DBTest.start();
	    
	}

}
