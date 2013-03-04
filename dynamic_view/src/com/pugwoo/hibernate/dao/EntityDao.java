package com.pugwoo.hibernate.dao;

import java.util.List;

/**
 * 通用实体的增删改查接口
 */
@SuppressWarnings("unchecked")
public interface EntityDao {

	/**
	 * 保存实体
	 */
	public void save(Object entity);

	/**
	 * 更新实体
	 */
	public void update(Object entity);

	/**
	 * 删除实体,只需要提供entity的Id
	 */
	public void delete(Object entity);

	/**
	 * 执行hql查询语句
	 * limit大于0时，limit生效
	 * start大于等于0时，start生效
	 */
	public List query(String hql, int start, int limit, Object... values);
	
	/**
	 * 执行hql语句
	 */
	public void execute(String hql, Object... values);

}
