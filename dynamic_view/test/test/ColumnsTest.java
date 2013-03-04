package test;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.hibernate.utils.StringUtils;
import com.pugwoo.hibernate.view.Container;
import com.pugwoo.hibernate.view.View;
import com.pugwoo.hibernate.view.cmp.Columns;

public class ColumnsTest extends TestCase {
	private static ApplicationContext factory;

	private static Container viewContext;

	static {
		factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		viewContext = (Container) factory.getBean("viewContainer");
		viewContext.setEsc('`');
	}

	public static void main(String args[]) {
		View student = viewContext.getDynamicView("Student",
				"id i,name,school.name sn,school.id si,school.address.name", null);
		Columns columns = student.getColumns();
		
		// 打印出基本信息
		StringUtils.println(3, "别名", "真实列名", "View列名", "Object列名", "Scope", "isID");
		for (int i = 0; i < columns.size(); i++) {
			String alias = columns.getAlias(i);
			int scope = columns.getScope(alias);
			String scopeStr = scope == 0 ? "ALL" : scope == 1 ? "SELF" : "ID";
			StringUtils.println(3, alias, columns.getColumnReal(alias), columns
					.getColumnView(alias), columns.getColumnObject(alias), scopeStr,
					columns.isAliasId(alias) ? "YES" : "NO");
		}
		System.out.println("自身ID个数：" + columns.getIdSize());

		// 打印出Select语句
		System.out.println("View Select: " + columns.getViewSelect());
		System.out.println("Object Select: " + columns.getObjectSelect());
	}
}
