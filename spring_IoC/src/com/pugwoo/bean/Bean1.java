package com.pugwoo.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 该类演示各种数据基础类型的注入 包括：String, int, List, Set, Map, Array, Properties
 */
public class Bean1 {
	
	private String strValue;
	private int intValue;
	private List<Object> listValue;
	private Set<Object> setValue;
	private Map<Object, Object> mapValue;
	private String[] arrValue;
	private Properties properties;

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public List<Object> getListValue() {
		return listValue;
	}

	public void setListValue(List<Object> listValue) {
		this.listValue = listValue;
	}

	public Set<Object> getSetValue() {
		return setValue;
	}

	public void setSetValue(Set<Object> setValue) {
		this.setValue = setValue;
	}

	public Map<Object, Object> getMapValue() {
		return mapValue;
	}

	public void setMapValue(Map<Object, Object> mapValue) {
		this.mapValue = mapValue;
	}

	public String[] getArrValue() {
		return arrValue;
	}

	public void setArrValue(String[] arrValue) {
		this.arrValue = arrValue;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
