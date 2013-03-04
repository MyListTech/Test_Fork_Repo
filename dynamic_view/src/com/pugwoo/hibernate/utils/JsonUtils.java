package com.pugwoo.hibernate.utils;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {

	public static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 从json到object
	 */
	public static Object read(String json) {
		try {
			return objectMapper.readValue(json, Object.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从json到object
	 */
	@SuppressWarnings("unchecked")
	public static Object read(String json, Class clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从object到json
	 */
	public static String write(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
