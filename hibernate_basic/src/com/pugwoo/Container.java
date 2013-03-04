package com.pugwoo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 该类演示容器类的存储，包括Set、List、Map和Hibernate特有的Bag
 * 
 * Set集合石无序、不能有重复元素的
 * Hibernate提供了一个Bag集合，用来处理重复元素的情况。
 * 值得注意的是，Bag并不是Java API，而是Hibernate提供的。
 * Bag集合映射和List不同，List的集合元素是有序的，需要有一个集合序号来标识集合元素的位置，List元素可以重复；
 * 而Bag集合元素不需要元素序号标识，元素也是可以重复的。
 */
@SuppressWarnings("unchecked")
public class Container {
	
	private String id;
	private Set setValue;
	private List listValue;
	private Map mapValue;
	private String[] arrayValue;
	private List bagValue;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set getSetValue() {
		return setValue;
	}
	public void setSetValue(Set setValue) {
		this.setValue = setValue;
	}
	public List getListValue() {
		return listValue;
	}
	public void setListValue(List listValue) {
		this.listValue = listValue;
	}
	public Map getMapValue() {
		return mapValue;
	}
	public void setMapValue(Map mapValue) {
		this.mapValue = mapValue;
	}
	public String[] getArrayValue() {
		return arrayValue;
	}
	public void setArrayValue(String[] arrayValue) {
		this.arrayValue = arrayValue;
	}
	public List getBagValue() {
		return bagValue;
	}
	public void setBagValue(List bagValue) {
		this.bagValue = bagValue;
	}

}
