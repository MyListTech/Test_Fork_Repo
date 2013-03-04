package com.pugwoo.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.pugwoo.hibernate.utils.StringUtils;

/**
 * 两种创建方法：Hibernate和Spring
 * 
 * <bean id="hibernateReflect"
 * class="com.pugwoo.hibernate.utils.HibernateReflect">
 * <constructor-arg ref="&amp;sessionFactory" />
 * </bean>
 * 
 * @author PugwooChia
 *         2011-4-28
 */
public class HibernateReflect {

	// Hibernate配置对象
	private Configuration cfg;

	// 维护从类名到持久类的映射
	private Map<String, PersistentClass> map;

	public HibernateReflect(Configuration cfg) {
		this.cfg = cfg;
		this.refresh();
	}

	/**
	 * 用于Spring
	 */
	public HibernateReflect(LocalSessionFactoryBean sessionFactory) {
		this.cfg = sessionFactory.getConfiguration();
		this.refresh();
	}

	/**
	 * 当hibernate配置动态修改后，刷新映射表
	 */
	public void refresh() {
		if (this.map == null)
			this.map = new HashMap<String, PersistentClass>();
		else
			this.map.clear();
		Iterator<PersistentClass> iterator = cfg.getClassMappings(); // 获得所有的持久化类
		while (iterator.hasNext()) {
			PersistentClass pc = iterator.next();
			this.map.put(pc.getEntityName(), pc); // 全称
			this.map.put(pc.getNodeName(), pc); // 简称
		}
	}

	/**
	 * 核心函数：通过类名称(全称或简称)获得持久化类对象，如果没有则返回null
	 */
	public PersistentClass getPersistentClass(String className) {
		return this.map.get(className);
	}

	// 半核心函数

	/**
	 * 获得一个持久化类的主键的名称列表
	 */
	@SuppressWarnings("unchecked")
	public List<String> getKey(PersistentClass pc) {
		if (pc == null)
			return null;
		List<String> key = new ArrayList<String>();
		Iterator<Column> keyIterator = pc.getKey().getColumnIterator();
		while (keyIterator.hasNext())
			key.add(keyIterator.next().getName());
		return key;
	}

	/**
	 * 获得一个持久化类的除ID以外的其它Property
	 */
	@SuppressWarnings("unchecked")
	public List<Property> getClosurePropertyWithoutId(PersistentClass pc) {
		if (pc == null)
			return null;
		List<Property> list = new ArrayList<Property>();
		Iterator<Property> iterator = pc.getPropertyClosureIterator();
		while (iterator.hasNext())
			list.add(iterator.next());
		return list;
	}

	/**
	 * 从Property获得持久化类，如果是则返回，不是则返回null
	 */
	public PersistentClass getPersistentClass(Property property) {
		if (property == null)
			return null;
		return getPersistentClass(property.getType().getReturnedClass()
				.getName());
	}

	// 下面是一些非核心函数，【完全】根据View的需要而编写

	/**
	 * 获得持久化类的所有列，以string的形式返回，逗号隔开
	 */
	public String getAllColumns(String className) {
		PersistentClass pc = getPersistentClass(className);
		if (pc == null)
			return null;
		StringBuffer sb = new StringBuffer();

		// 添加ID属性
		List<String> key = getKey(pc);
		for (int i = 0; i < key.size(); i++)
			sb.append(key.get(i)).append(',');

		// 添加其它属性
		List<Property> ps = getClosurePropertyWithoutId(pc);
		for (int i = 0; i < ps.size(); i++) {
			Property property = ps.get(i);
			PersistentClass p = getPersistentClass(property);
			if (p == null) // 不是持久化类
				sb.append(property.getName()).append(',');
			else { // 持久化类
				List<String> k = getKey(p);
				for (int j = 0; j < k.size(); j++)
					sb.append(property.getName()).append('.').append(k.get(j))
							.append(',');
			}
		}
		return sb.substring(0, sb.length() - 1); // 刚好去掉最后一个逗号
	}

	/**
	 * 判断property是不是持久化类，其中property的格式是xxx.yyy形式
	 */
	public boolean isPersistentProperty(String className, String property) {
		PersistentClass pc = getPersistentClass(className);
		return isPersistentProperty(pc, property);
	}
	
	/**
	 * 判断property是不是持久化类，其中property的格式是xxx.yyy形式
	 */
	public boolean isPersistentProperty(PersistentClass object, String property){
		List<String> ps = StringUtils.split(property, '.');
		if (ps == null || ps.size() == 0 || object == null)
			return false;
		
		for (int i = 0; i < ps.size(); i++) {
			object = getPersistentClass(object.getProperty(ps.get(i)));
			if (object == null)
				break;
		}
		return object != null;
	}

	/**
	 * 判断property是不是某个持久化类的ID，其中property的格式是xxx.yyy形式
	 */
	public boolean isPersistentId(String className, String property) {
		PersistentClass pc = getPersistentClass(className);
		return isPersistentId(pc, property);
	}

	/**
	 * 判断property是不是某个持久化类的ID，其中property的格式是xxx.yyy形式
	 */
	@SuppressWarnings("unchecked")
	public boolean isPersistentId(PersistentClass object, String property) {
		List<String> ps = StringUtils.split(property, '.');
		if (ps == null || ps.size() == 0 || object == null)
			return false;

		int last = ps.size() - 1;
		for (int i = 0; i < last; i++) {
			object = getPersistentClass(object.getProperty(ps.get(i)));
			if (object == null)
				return false;
		}
		// 【效率比较慢】但是运行次数很少，基本一次，所以暂不优化
		String name = ps.get(last);
		Iterator<Column> keyIterator = object.getKey().getColumnIterator();
		while (keyIterator.hasNext())
			if (keyIterator.next().getName().equals(name))
				return true;
		return false;
	}

}
