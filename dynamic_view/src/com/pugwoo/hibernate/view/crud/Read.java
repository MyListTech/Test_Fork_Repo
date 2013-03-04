package com.pugwoo.hibernate.view.crud;

import java.util.List;

import com.pugwoo.hibernate.dao.EntityDao;
import com.pugwoo.hibernate.view.cmp.Columns;
import com.pugwoo.hibernate.view.cmp.Filter;

public class Read {

	/**
	 * 读取数据，以二维表view的形式返回
	 * 【以后这个read将不是采用left join的方式】
	 */
	@SuppressWarnings("unchecked")
	public static List readView(EntityDao entityDao, Columns columns, Filter filter,
			int start, int limit) {
		StringBuffer sb = new StringBuffer(columns.getViewSelect());
		if (filter != null)
			sb.append(" WHERE ").append(filter.getWhere(columns));
		return entityDao.query(sb.toString(), start, limit);
	}
	
	/**
	 * 读取数据，只读取Object，但会的持久化态的对象
	 */
	@SuppressWarnings("unchecked")
	public static List readObject(EntityDao entityDao, Columns columns, Filter filter,
			int start, int limit){
		StringBuffer sb = new StringBuffer(columns.getObjectSelect());
		if(filter != null)
			sb.append(" WHERE ").append(filter.getWhere(columns));
		return entityDao.query(sb.toString(), start, limit);
	}
}
