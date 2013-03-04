package com.pugwoo.hibernate.view.cmp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pugwoo.hibernate.utils.ReflectUtils;
import com.pugwoo.hibernate.utils.StringUtils;

/**
 * 若干個FilterItem的集合
 * 
 * 注意：如果操作符号是IN，则采用的值为Set
 * 
 * @author PugwooChia
 *         2011-5-5
 */
public class Filter {

	public Filter() {
	}

	public Filter(FilterItem filterItem) {
		add(filterItem);
	}

	public Filter(Filter filter) {
		add(filter);
	}

	// 默認各個FilterItem或Filter之間是AND的關係
	private boolean AND = true;

	// 存放若刚个FilterItem或Filter
	private List<Object> filters = new ArrayList<Object>();

	public void add(FilterItem filterItem) {
		this.filters.add(filterItem);
	}

	public void add(Filter filter) {
		this.filters.add(filter);
	}

	/**
	 * 返回值不含WHERE关键字
	 */
	@SuppressWarnings("unchecked")
	public String getWhere(Columns columns) {
		StringBuffer sb = new StringBuffer();
		boolean isFirstAND = true; // 记录是否第一次添加AND|OR

		for (int i = 0; i < filters.size(); i++) {
			Object obj = filters.get(i);
			if (obj instanceof FilterItem) {
				// 处理name
				String column = columns.getColumnView(((FilterItem) obj).getName());
				if (column == null) // 按理column不能为空，这里会有提示的
					continue; // 直接跳过

				if (!isFirstAND)
					sb.append(AND ? " AND " : " OR ");
				sb.append(column);
				// 处理op
				sb.append(' ').append(((FilterItem) obj).getOp()).append(' ');
				// 处理值：Number Set String(包含其它)
				Object value = ((FilterItem) obj).getValue();
				if (value == null)
					sb.append("null");
				else if (value instanceof Number)
					sb.append(value);
				else if (value instanceof Set) {
					sb.append('(');
					Iterator<Object> iterator = ((Set) value).iterator();
					boolean isFirst = true;
					while (iterator.hasNext()) {
						if (!isFirst)
							sb.append(',');
						Object o = iterator.next();
						if (o == null) // 空值是不处理的
							continue;
						if (o instanceof Number)
							sb.append(o);
						else
							sb.append(StringUtils.escape(o.toString()));
						isFirst = false;
					}
					sb.append(')');
				} else
					sb.append(StringUtils.escape(value.toString()));
			} else { // Filter
				String f = ((Filter) obj).getWhere(columns);
				if (f.isEmpty()) // 空的则不需要处理
					continue;
				if (!isFirstAND)
					sb.append(AND ? " AND " : " OR ");
				sb.append('(').append(f).append(')');
			}
			isFirstAND = false;
		}
		return sb.toString();
	}

	// 下面是获得对象Identifier的Filter

	/**
	 * 获得object的ID的Filter，也即通过该Filter就可以重新读取到这个对象
	 * 如果object为空，则返回null
	 */
	public static Filter getIdFilter(Columns columns, Object object) {
		if (object == null)
			return null;
		Filter filter = new Filter();
		List<String> keys = columns.getKeys();
		for (int i = 0; i < columns.getIdSize(); i++)
			filter.add(new FilterItem(keys.get(i), "=", ReflectUtils.get(
					object, keys.get(i))));
		return filter;
	}

	/**
	 * 获得list对象集的ID的Filter，也即通过该Filter就可以重新读取到这些对象;
	 * 如果objects为空，则返回null;
	 */
	public static Filter getIdFilter(Columns columns, List<Object> objects) {
		if (objects == null || objects.size() == 0)
			return null;

		Filter filter = new Filter();
		int idSize = columns.getIdSize(); // idSize>=1
		List<String> keys = columns.getKeys();

		if (idSize == 1) { // 如果只有一个Id，则采用IN
			Set<Object> idSet = new HashSet<Object>();
			for (int i = 0; i < objects.size(); i++)
				idSet.add(ReflectUtils.get(objects.get(i), keys.get(0)));
			filter.add(new FilterItem(keys.get(0), "IN", idSet));
		} else { // 否则采用基本的逻辑运算
			filter.setAND(false); // 采用OR
			for (int i = 0; i < objects.size(); i++) {
				Filter f = new Filter();
				for (int j = 0; j < idSize; j++)
					f.add(new FilterItem(keys.get(j), "=", ReflectUtils.get(
							objects.get(i), keys.get(j))));
				filter.add(f);
			}
		}
		return filter;
	}

	/**
	 * 获得map的ID的Filter，也即通过该Filter就可以重新读取到这个对象
	 * 如果map为空，则返回null
	 */
	public static Filter getIdFilter(Columns columns,
			Map<String, Object> aliasMap) {
		if (aliasMap == null)
			return null;
		Filter filter = new Filter();
		List<String> keys = columns.getKeys();
		for (int i = 0; i < keys.size(); i++)
			filter.add(new FilterItem(columns.getColumnView(keys.get(i)), "=",
					aliasMap.get(keys.get(i))));
		return filter;
	}

	/**
	 * 直接通过IDMap的list获得Filter，通过filter可以重新读取到这些对象
	 * 如果idMap为空，返回null
	 */
	public static Filter getIdFilter(List<Map<String, Object>> idMaps) {
		if (idMaps == null || idMaps.size() == 0)
			return null;
		Filter filter = new Filter();
		int idSize = idMaps.get(0).size();
		Iterator<String> keysIter = idMaps.get(0).keySet().iterator();
		List<String> keys = new ArrayList<String>();
		while (keysIter.hasNext())
			keys.add(keysIter.next());

		if (idSize == 1) { // 使用IN
			String key = keys.get(0); // 就一个
			Set<Object> idSet = new HashSet<Object>();
			for (int i = 0; i < idMaps.size(); i++)
				idSet.add(idMaps.get(i).get(key));
			filter.add(new FilterItem(key, "IN", idSet));
		} else {
			filter.setAND(false);// 采用OR
			for (int i = 0; i < idMaps.size(); i++) {
				Map<String, Object> map = idMaps.get(i);
				Filter f = new Filter();
				for (int j = 0; j < idSize; j++) {
					String key = keys.get(j);
					f.add(new FilterItem(key, "=", map.get(key)));
				}
				filter.add(f);
			}
		}
		return filter;
	}

	public boolean isAND() {
		return AND;
	}

	public void setAND(boolean aND) {
		AND = aND;
	}

	public List<Object> getFilters() {
		return filters;
	}

	public void setFilters(List<Object> filters) {
		this.filters = filters;
	}

}
