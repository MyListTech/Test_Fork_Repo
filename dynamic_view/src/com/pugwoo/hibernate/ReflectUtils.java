package com.pugwoo.hibernate;
import java.lang.reflect.Method;

/**
 * 提供一些反射操作，都是静态方法
 */
public class ReflectUtils {
	
	/**
	 * 反射调用setter方法
	 */
	public static void setter(Object obj, String attr, Object value,
			Class<?> valueType) {
		try {
			Method method = obj.getClass().getMethod(
					"set" + firstToUpper(attr), valueType);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 反射调用setter方法
	 */
	public static void setter(Object obj, String attr, Object value) {
		setter(obj, attr, value, value.getClass());
	}

	/**
	 * 反射调用getter方法，返回Object对象
	 */
	public static Object getter(Object obj, String attr) {
		try {
			Method method = obj.getClass()
					.getMethod("get" + firstToUpper(attr));
			return method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将属性的首字母变大写
	 */
	private static String firstToUpper(String attr) {
		if (attr == null || attr.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer(attr.substring(0, 1).toUpperCase());
		sb.append(attr.substring(1));
		return sb.toString();
	}
}
