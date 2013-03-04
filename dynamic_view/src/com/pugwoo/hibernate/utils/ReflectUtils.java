package com.pugwoo.hibernate.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import model.Student;

/**
 * 提供一些反射操作，都是静态方法
 */
public class ReflectUtils {

	/**
	 * 反射调用setter方法
	 */
	private static void set(Object obj, String attr, Object value,
			Class<?> valueType) {
		try {
			Method method = obj.getClass().getMethod("set" + upperFirst(attr),
					valueType);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 反射调用setter方法
	 */
	public static void set(Object obj, String attr, Object value) {
		set(obj, attr, value, value.getClass());
	}

	/**
	 * 反射调用setter方法，支持EL表达，用dot . 分开
	 * 如果中间某个属性值为null，则会自动创建一个，前提是该类提供默认构造函数
	 */
	public static void setCasecade(Object obj, String attr, Object value) {
		List<String> list = StringUtils.split(attr, '.');
		int last = list.size() - 1;
		for (int i = 0; i < last; i++) {
			Object prec = obj; // 临时
			obj = get(obj, list.get(i));
			if (obj == null) {
				try {
					Field field = prec.getClass().getDeclaredField(list.get(i));
					obj = field.getType().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				set(prec, list.get(i), obj);
			}
		}
		set(obj, list.get(last), value);
	}

	/**
	 * 反射调用getter方法，返回Object对象
	 */
	public static Object get(Object obj, String attr) {
		try {
			Method method = obj.getClass().getMethod("get" + upperFirst(attr));
			return method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反射调用getter方法，支持EL，用dot . 分开，返回Object对象
	 */
	public static Object getCasecade(Object obj, String attr) {
		List<String> list = StringUtils.split(attr, '.');
		try {
			for (int i = 0; i < list.size(); i++) {
				if(obj == null)
					return null;
				obj = get(obj, list.get(i));
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将属性的首字母变大写
	 */
	private static String upperFirst(String attr) {
		// 已优化
		if (attr == null || attr.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer(attr);
		char c = attr.charAt(0);
		if (c >= 'a' && c <= 'z')
			sb.setCharAt(0, (char) (c - 32));
		return sb.toString();
	}

	/**
	 * 单元测试
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static void main(String args[]) throws SecurityException,
			NoSuchFieldException {
		Student student = new Student();

		ReflectUtils.set(student, "name", "hi");
		ReflectUtils.setCasecade(student, "school.id", 3L);

        System.out.println(ReflectUtils.get(student, "name"));		
		System.out.println(ReflectUtils.getCasecade(student, "school.id"));

	}
}
