package com.pugwoo.hibernate.view;

import java.util.List;

import org.hibernate.mapping.PersistentClass;

import com.pugwoo.hibernate.dao.EntityDao;
import com.pugwoo.hibernate.view.cmp.Columns;
import com.pugwoo.hibernate.view.cmp.Filter;
import com.pugwoo.hibernate.view.crud.Create;
import com.pugwoo.hibernate.view.crud.Read;

/**
 * 动态视图的抽象
 * 操作有：CRUD
 * 【！注意！】外面程序只知道View的Alias列，所以傳過來的數據都是Alias列名的
 * View得返回形式一直是一张表List
 * 
 * @author PugwooChia 2011-4-20
 */
public class View {

	// 和Hibernate相关的EntitiyDao操作对象
	private EntityDao entityDao;

	// view的列，包括基准对象
	private Columns columns;

	public View(EntityDao entityDao, Columns columns) {
		this.entityDao = entityDao;
		this.columns = columns;
	}

	/**
	 * 读取数据，数据是以view为准的二维表
	 */
	@SuppressWarnings("unchecked")
	public List read(Filter filter, int start, int limit) {
		return Read.readView(entityDao, columns, filter, start, limit);
	}

	/**
	 * 读取数据，数据是以view为准的二维表
	 */
	@SuppressWarnings("unchecked")
	public List read(int start, int limit) {
		return Read.readView(entityDao, columns, null, start, limit);
	}
	
	/**
	 * 读取数据，是Persistent态的Object
	 */
	@SuppressWarnings("unchecked")
	public List readObject(Filter filter, int start, int limit){
		return Read.readObject(entityDao, columns, filter, start, limit);
	}
	
	/**
	 * 读取数据，是Persistent态的Object
	 */
	@SuppressWarnings("unchecked")
	public List readObject(int start, int limit){
		return Read.readObject(entityDao, columns, null, start, limit);
	}

	/**
	 * 向数据库插入自身对象
	 * data可以是单个对象或者是数组，data的某个对象是view的列的集合，只有可能单层的列
	 * data是标准的json格式
	 * 返回值是创建的对象的view数据
	 */
	@SuppressWarnings("unchecked")
	public List create(String json) {
		return Create.create(entityDao, columns, json);
	}

	/**
	 * 向数据库级联插入对象，其它同create
	 */
	@SuppressWarnings("unchecked")
	public List createCasecade(String json) {
		return Create.createCasecade(entityDao, columns, json);
	}

	public PersistentClass getObject() {
		return columns.getObject();
	}

	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}

}
