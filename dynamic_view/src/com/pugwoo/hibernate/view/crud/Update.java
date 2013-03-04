package com.pugwoo.hibernate.view.crud;

import java.util.Map;

import org.hibernate.mapping.PersistentClass;

import com.pugwoo.hibernate.dao.EntityDao;
import com.pugwoo.hibernate.view.cmp.Columns;

public class Update {

	/**
	 * 使用hibernate的update方法进行更新，isSelf制定是否只更新自身的属性
	 * 如果使用了HibernateSearch，则必须使用这个，否则推荐使用updateHQL
	 */
	public Object update(EntityDao entityDao, PersistentClass object,
			Columns columns, Map<String, Object> aliasMap, boolean isSelf){
		
		return null;
	}
	
	/**
	 * 使用HQL语句的方式进行更新，isSelf制定是否只更新自身的属性
	 */
	public Object updateHQL(EntityDao entityDao, PersistentClass object,
			Columns columns, Map<String, Object> aliasMap, boolean isSelf){
		return null;
	}
}
