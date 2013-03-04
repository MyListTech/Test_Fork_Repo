package com.pugwoo.hibernate.view.cmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.PersistentClass;

import com.pugwoo.hibernate.utils.StringUtils;

/**
 * 列的集合
 * 
 * Columns类并【不关心HibernateReflect】，它只保存数据并返回需要的数据
 * 用法：1)添加列名和该列的别名，以及该列是否为主键；2)添加需要left join的列名
 * 
 * 暂时只保存 alias --> 其它信息 的映射，【一切以Alias为线索】
 * 在外界看来，只能看到columns的Alias
 * 
 * @author PugwooChia
 *         2011-5-5
 */
public class Columns {

	/**
	 * 要求注入PersistentClass
	 */
	public Columns(PersistentClass object) {
		this.object = object;
	}

	/**
	 * 下面这三个是Columns的scope等级，分别是全部、自身属性、ID属性
	 * 请保持三者数值的大小顺序并确定三者大于0，【范围越小，数值越大】
	 */
	public static final int ALL = 0;

	public static final int SELF = 1;

	public static final int ID = 2;

	// object的别名，默认是o，这个一般不变
	public static final String OBJECT_ALIAS = "o";

	// 持久化对象
	private PersistentClass object;

	// 内部类，存放Map对应的数据
	private class AliasMeta {
		public AliasMeta(String column, boolean isId, String joinAlias,
				int scope) {
			this.column = column;
			this.isId = isId;
			this.joinAlias = joinAlias;
			this.scope = scope;
		}

		// 对应的列，真实的，不是别名，不包括OBJECT_ALIAS
		public String column;
		// 是否为ID，包括自身的ID和持久化类对象的ID
		public boolean isId;
		// join列的别名（持久化对象+rest名称），不包括OBJECT_ALIAS，如果该列不是join的，则为null；
		public String joinAlias;
		// 该列scope，标志其最小的范围，目前取值有ALL、SELF、ID
		public int scope;
	}

	// 列的别名
	private List<String> alias = new ArrayList<String>();

	// 通过别名获得对应的信息
	private Map<String, AliasMeta> aliasMeta = new HashMap<String, AliasMeta>();

	/**
	 * 通过别名获得对应的信息
	 */
	private AliasMeta getMeta(String alias) {
		AliasMeta meta = aliasMeta.get(alias);
		if (meta == null)
			new Exception("ALIAS '" + alias + "' IS NOT FOUND.")
					.printStackTrace();// 仅提示
		return meta;
	}

	// 需要left join的持久对象（不包括最后的名称rest）的【Alias名称】和它对应的别名，不包括最后的名称rest
	private Map<String, String> join = new HashMap<String, String>();

	// 存放主键的【Alias名称】，不包括出该PersistentObject之外的其它ID
	private List<String> keys = new ArrayList<String>();

	/**
	 * 添加列，需提供该列名称和别名、是否为主键；column alias isId均不能为空；
	 * join是left join的列，rest是left join剩下的名称
	 */
	public void addColumn(String column, String alias, boolean isId,
			String join, String rest) {
		String joinAlias = null; // joinAlias是join的别名
		if (join != null) {
			if (this.join.containsKey(join))
				joinAlias = this.join.get(join);
			else {
				joinAlias = "o" + this.join.size(); // 自动命名从o0 o1 o2开始
				this.join.put(join, joinAlias);
			}
		}

		int count = StringUtils.count(column, '.');
		int scope;
		if (isId && count == 0) // 【注意一定要等于0】也即主键只能是Self属性
			scope = Columns.ID;
		else if (count == 0 || (isId && count == 1))
			scope = Columns.SELF;
		else
			scope = Columns.ALL;

		if (scope == Columns.ID)
			keys.add(alias); // 注意这里是添加ID的alias名称

		this.alias.add(alias);
		this.aliasMeta.put(alias, new AliasMeta(column, isId,
				join == null ? null : joinAlias + "." + rest, scope));

		this.viewSelect = null; // 清除select的缓存
	}

	/**
	 * 通过位置获得别名
	 */
	public String getAlias(int index) {
		return alias.get(index);
	}

	/**
	 * 获得列的个数
	 */
	public int size() {
		return this.alias.size();
	}

	/**
	 * 通过别名获得可以【直接】用于View的列名，如果是join，则返回join的别名
	 */
	public String getColumnView(String alias) {
		AliasMeta meta = getMeta(alias);
		if (meta == null)
			return null;
		if (meta.joinAlias != null)
			return meta.joinAlias;
		return Columns.OBJECT_ALIAS + '.' + meta.column;
	}
	
	/**
	 * 通过别名获得列名，可以【直接】用于Object的列名，因为Object没有join这些
	 */
	public String getColumnObject(String alias){
		AliasMeta meta = getMeta(alias);
		if (meta == null)
			return null;
		return Columns.OBJECT_ALIAS + '.' + meta.column;
	}

	/**
	 * 通过别名获得列名，这个列名是真实的列名
	 */
	public String getColumnReal(String alias) {
		AliasMeta meta = getMeta(alias);
		return meta == null ? null : meta.column;
	}

	/**
	 * 通过列名获得scope，失败返回-1
	 */
	public int getScope(String alias) {
		AliasMeta meta = getMeta(alias);
		return meta == null ? -1 : meta.scope;
	}

	/**
	 * 判断alias是不是ID
	 */
	public boolean isAliasId(String alias) {
		AliasMeta meta = aliasMeta.get(alias);
		return meta == null ? false : meta.isId;
	}

	/**
	 * 获得ID的个数，不包括出该PersistentObject之外的其它ID
	 */
	public int getIdSize() {
		return this.keys.size();
	}

	// 下面这些方法为select语句服务

	private String viewSelect; // 缓存

	/**
	 * 生成的select from语句，不带where后面那些
	 */
	public String getViewSelect() {
		if (viewSelect == null) {
			StringBuffer sb = new StringBuffer("SELECT ");
			int size = this.size();
			if (size > 0) // size应该大于0
				sb.append(getColumnView(getAlias(0)));
			for (int i = 1; i < size; i++)
				sb.append(',').append(getColumnView(getAlias(i)));

			sb.append(" FROM ").append(this.object.getEntityName()).append(' ')
					.append(Columns.OBJECT_ALIAS);

			Map<String, String> join = this.getJoin();
			Iterator<String> joinKeys = join.keySet().iterator();
			while (joinKeys.hasNext()) {
				String key = joinKeys.next();
				sb.append(" LEFT JOIN ").append(Columns.OBJECT_ALIAS).append(
						'.').append(key).append(" AS ").append(join.get(key));
			}
			viewSelect = sb.toString();
		}
		return viewSelect;
	}

	private String objectSelect; // 缓存，一次生成，一般不用改变

	/**
	 * 生成from语句，获得的是对象，不带where后面那些
	 */
	public String getObjectSelect() {
		if (objectSelect == null) {
			StringBuffer sb = new StringBuffer("FROM ");
			sb.append(this.object.getEntityName()).append(' ').append(
					Columns.OBJECT_ALIAS);
			objectSelect = sb.toString();
		}
		return objectSelect;
	}

	// 下面这些函数为CRUD服务

	/**
	 * 通过alias名称的map获得column名称的map，这些map都是单层的
	 */
	public Map<String, Object> getColumnMap(Map<String, Object> aliasMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keys = aliasMap.keySet().iterator();
		while (keys.hasNext()) {
			String alias = keys.next();
			String column = this.getColumnView(alias);
			if (column == null) // column不应为空，若为空则跳过
				continue;
			map.put(column, aliasMap.get(alias));
		}
		return map;
	}

	/**
	 * 通过alias名称的map获得column名称的map，这些map都是单层的
	 */
	public List<Map<String, Object>> getColumnMap(
			List<Map<String, Object>> aliasMapList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < aliasMapList.size(); i++)
			list.add(getColumnMap(aliasMapList.get(i)));
		return list;
	}

	/**
	 * 通过alias名称的map获得column名称的map，这些map都是单层的
	 * scope是获取的columns的范围：ALL全部、SELF自身属性、ID属性
	 */
	public Map<String, Object> getColumnMap(Map<String, Object> aliasMap,
			int scope) {
		return getColumnMap(aliasMap, Integer.MAX_VALUE, scope);
	}

	/**
	 * 通过alias名称的map获得column名称的map，这些map都是单层的
	 * scope是获取的columns的范围：ALL全部、SELF自身属性、ID属性
	 * largeScope是最大的范围，smallScope是最小的范围
	 */
	public Map<String, Object> getColumnMap(Map<String, Object> aliasMap,
			int smallScope, int largeScope) {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keys = aliasMap.keySet().iterator();
		while (keys.hasNext()) {
			String alias = keys.next();
			String column = this.getColumnView(alias);
			if (column == null) // column不应为空，若为空则跳过
				continue;
			int scope = getScope(alias);
			if (scope >= largeScope && scope <= smallScope) // 注意这个是颠倒的
				map.put(column, aliasMap.get(alias));
		}
		return map;
	}

	/**
	 * 将形如{"school.id":32}的【单层】Map展开成多层的Map
	 * 约定分隔的字符为dot
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> expandMap(Map<String, Object> map,
			char esc) {
		Map<String, Object> result = new HashMap<String, Object>(); // 用来存放新的map
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			List<String> cut = StringUtils.split(key, esc);
			if (cut.size() > 1) { // 说明它包含了多层,至少2层；只有一层的不需要处理
				Map lastLayer = result;
				for (int i = 0; i < cut.size() - 1; i++) { // 先不处理最后一层
					Object tmp = lastLayer.get(cut.get(i));
					Object curLayer = tmp == null ? new HashMap() : tmp;
					if (!(curLayer instanceof Map)) {// 如果拿出来的不是Map
						new Exception("MAP IS NEEDED HERE").printStackTrace();
						return null;
					}
					lastLayer.put(cut.get(i), curLayer);
					lastLayer = (Map) curLayer;
				}
				Object tmp = lastLayer.get(cut.get(cut.size() - 1));// 处理最后一层
				if (tmp != null && (tmp instanceof Map)) {
					new Exception("MAP IS NOT ALLOWED HERE").printStackTrace();
					return null;
				}
				lastLayer.put(cut.get(cut.size() - 1), map.get(key));
			} else {
				result.put(key, map.get(key)); // 直接添加
			}
		}
		return result;
	}

	// 下面是getter和setter

	public List<String> getKeys() {
		return keys;
	}

	public List<String> getAlias() {
		return alias;
	}

	public Map<String, String> getJoin() {
		return join;
	}

	public PersistentClass getObject() {
		return object;
	}

}
