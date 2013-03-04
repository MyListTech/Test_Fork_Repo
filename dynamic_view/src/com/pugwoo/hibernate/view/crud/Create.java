package com.pugwoo.hibernate.view.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pugwoo.hibernate.dao.EntityDao;
import com.pugwoo.hibernate.utils.JsonUtils;
import com.pugwoo.hibernate.view.cmp.Columns;
import com.pugwoo.hibernate.view.cmp.Filter;

/**
 * 负责View的Create操作
 * 
 * @author PugwooChia
 *         2011-5-15
 */
public class Create {

	/**
	 * 通过json插入数据，json可以是map或list
	 * 返回的是带有ID集合的Filter；如果全部插入不成功，则返回null
	 * 这个只插入自身的数据
	 */
	@SuppressWarnings("unchecked")
	public static List create(EntityDao entityDao, Columns columns,
			String json) {
		Filter filter = create(entityDao, columns, json, true);
		if(filter == null)
			return new ArrayList();// 如果filter为空，则没有一个创建成功
		return Read.readView(entityDao, columns, filter, -1, -1); 
	}

	/**
	 * 通过json插入数据，json可以是map或list
	 * 返回的是带有ID集合的Filter；如果全部插入不成功，则返回null
	 * 这个是级联插入
	 */
	@SuppressWarnings("unchecked")
	public static List createCasecade(EntityDao entityDao, Columns columns,
			String json) {
		Filter filter = create(entityDao, columns, json, false);
		if(filter == null)
			return new ArrayList();// 如果filter为空，则没有一个创建成功
		return Read.readView(entityDao, columns, filter, -1, -1);
	}

	/**
	 * 根据数据data插入一个对象并返回该对象
	 * createSelf只关心自身的属性，返回的对象也是
	 * 如果是create，可能需要设置级联casecade
	 * 如果不成功，则返回null
	 */
	private static Object create(EntityDao entityDao, Columns columns,
			Map<String, Object> aliasMap, boolean isSelf) {

		Map<String, Object> map = columns.getColumnMap(aliasMap,
				isSelf ? Columns.SELF : Columns.ALL);

		String json = JsonUtils.write(Columns.expandMap(map, '.')); // 【expand】
		Object obj = JsonUtils.read(json, columns.getObject().getMappedClass());
		try {
			entityDao.save(obj); // 保存
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return obj;
	}

	/**
	 * 根据json插入数据
	 */
	@SuppressWarnings("unchecked")
	private static Filter create(EntityDao entityDao, Columns columns,
			String json, boolean isSelf) {
		Object obj = JsonUtils.read(json);
		if (obj instanceof Map) {
			Object o = Create.create(entityDao, columns, (Map) obj, isSelf);
			return Filter.getIdFilter(columns, o);
		} else if (obj instanceof List) {
			List<Object> result = new ArrayList<Object>();
			for (int i = 0; i < ((List) obj).size(); i++) {
				Object o = Create.create(entityDao, columns, (Map) ((List) obj)
						.get(i), isSelf);
				if (o != null)
					result.add(o);
			}
			return Filter.getIdFilter(columns, result);
		} else
			return null;
	}

}
