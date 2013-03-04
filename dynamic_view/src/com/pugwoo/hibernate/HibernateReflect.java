package com.pugwoo.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.User;

import org.hibernate.MappingNotFoundException;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;

/**
 * 通过Hibernate获得Hibernate映射到数据库中的表和列
 * 注：po类名须与对应映射文件名一致，即Student.java与Student.hbm.xml
 */
public class HibernateReflect {

	/**
	 * 要实现这些功能得获得Configuration
	 */
	private static Configuration cfg = HibernateUtils.getConfiguration();

	/**
	 * 获得对应类的表名
	 */
	@SuppressWarnings("unchecked")
	public static String getTableName(Class clazz) {
		return getPersistentClass(clazz).getTable().getName();
	}
	
	/**
	 * 获得对应类的主键名
	 */
	@SuppressWarnings("unchecked")
	public static String getPkColumnName(Class clazz) {
		return getPersistentClass(clazz).getTable().getPrimaryKey()
				.getColumn(0).getName();
	}
	
	/**
	 * 获得对应类的所有列名
	 */
	@SuppressWarnings("unchecked")
	public static List getColumnsName(Class clazz){
		PersistentClass persistentClass = getPersistentClass(clazz);
		Iterator it = persistentClass.getTable().getColumnIterator();
		List<String> list = new ArrayList<String>();
		while(it.hasNext()){
			Column column = (Column) it.next();
			list.add(column.getName());
		}
		return list;
	}
	
	/**
	 * 获得对应类对应属性的列名
	 */
	@SuppressWarnings("unchecked")
	public static String getColumnName(Class clazz, String propertyName) {
		PersistentClass persistentClass = getPersistentClass(clazz);
		Property property = persistentClass.getProperty(propertyName);
		Iterator it = property.getColumnIterator();
		if (it.hasNext()) {
			Column column = (Column) it.next();
			return column.getName();
		}
		return null;
	}

	/**
	 * 获得持久化对象
	 */
	@SuppressWarnings("unchecked")
	private static PersistentClass getPersistentClass(Class clazz) {
		PersistentClass pc = cfg.getClassMapping(clazz.getName());
		if (pc == null)
			throw new MappingNotFoundException("class", clazz.getName());
		return pc;
	}

	public static void main(String args[]) {
		System.out.println(getTableName(User.class));
	}
}
