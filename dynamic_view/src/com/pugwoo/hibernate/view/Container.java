package com.pugwoo.hibernate.view;

import java.util.List;

import org.hibernate.mapping.PersistentClass;

import com.pugwoo.hibernate.dao.EntityDao;
import com.pugwoo.hibernate.dao.HibernateReflect;
import com.pugwoo.hibernate.utils.StringUtils;
import com.pugwoo.hibernate.view.cmp.Columns;

/**
 * 负责从配置文件读取信息并创建DynamicView
 * 
 * @author PugwooChia
 *         2011-4-30
 */
public class Container {

	private EntityDao entityDao;

	private HibernateReflect reflect;

	public Container(EntityDao entityDao, HibernateReflect hibernateReflect) {
		this.reflect = hibernateReflect;
		this.entityDao = entityDao;
	}

	// 转义字符，默认为空
	private char esc = '\0';

	/**
	 * 生成getDynamicView對象
	 */
	public View getDynamicView(String object, String columns,
			String additionalColumns) {
		PersistentClass pc = reflect.getPersistentClass(object);
		if (pc == null)
			return null;

		if (columns == null || columns.isEmpty())
			columns = reflect.getAllColumns(object);
		StringBuffer sb = new StringBuffer(columns);
		if (additionalColumns != null && !columns.isEmpty())
			sb.append(',').append(additionalColumns);

		Columns cols = getColumns(pc, sb.toString());
		return new View(entityDao, cols);
	}

	/**
	 * columns集合了columns和additionanlColumns
	 * columns暂时不考虑空格、回车符号、Tab符号、空column等
	 * columns的格式为xxx.yyy alias,fff.ddd,fd,fe
	 */
	private Columns getColumns(PersistentClass object, String columns) {
		Columns cols = new Columns(object);
		List<String> colsList = StringUtils.split(columns, ',');
		for (int i = 0; i < colsList.size(); i++) {
			List<String> col = StringUtils.split(colsList.get(i), ' ');
			
			boolean isId = reflect.isPersistentId(object, col.get(0));

			String join = null;
			String rest = null;
			List<String> joinList = StringUtils.split(col.get(0), '.', false, 2);
			if (joinList.size() == 2
					&& reflect.isPersistentProperty(object, joinList.get(1))){
				join = joinList.get(1);
				rest = joinList.get(0);
			}

			if (col.size() > 1) {
				cols.addColumn(col.get(0), col.get(1), isId, join, rest);
			} else {
				if (this.esc == '\0')
					cols.addColumn(col.get(0), col.get(0), isId, join, rest);
				else
					cols.addColumn(col.get(0), col.get(0).replace('.', esc),
							isId, join, rest);
			}
		}
		return cols;
	}

	// getter setter

	public char getEsc() {
		return esc;
	}

	public void setEsc(char esc) {
		this.esc = esc;
	}

	public HibernateReflect getReflect() {
		return reflect;
	}

	public void setReflect(HibernateReflect reflect) {
		this.reflect = reflect;
	}

	public EntityDao getEntityDao() {
		return entityDao;
	}

	public void setEntityDao(EntityDao entityDao) {
		this.entityDao = entityDao;
	}

}
