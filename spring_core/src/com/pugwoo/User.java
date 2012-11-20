/**
 * 2011年1月8日 下午11:32:50
 */
package com.pugwoo;

import java.lang.reflect.Method;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 
 * @author Pugwoo
 * 
 */
public class User {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 使用Java的Reflection机制
	@SuppressWarnings("unchecked")
	public static Object getByJavaReflection() throws Exception {
		Class cls = Class.forName("com.pugwoo.User");
		Method mtd = cls.getMethod("setName", new Class[] { String.class });
		Object obj = (Object) cls.newInstance();
		mtd.invoke(obj, new Object[] { "nick" });
		return obj;
	}

	// 使用Spring的BeanWrapper
	public static Object getByBeanWrapper() throws Exception {
		Object obj = Class.forName("com.pugwoo.User").newInstance();
		BeanWrapper bw = new BeanWrapperImpl(obj);
		bw.setPropertyValue("name", "awoo"); // 这里会根据Bean规范，调用setName来赋值
		// name这个属性还可以是嵌套的，如address.phone，则会调用getAddress().getPhone()
		System.out
				.println("in BeanWrapper,name:" + bw.getPropertyValue("name"));
		return obj;
	}

	public static void main(String args[]) throws Exception {
		User user1 = (User) getByJavaReflection();
		System.out.println(user1.getName());

		User user2 = (User) getByBeanWrapper();
		System.out.println(user2.getName());
	}
}
