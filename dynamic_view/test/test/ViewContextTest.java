package test;

import java.util.List;

import junit.framework.TestCase;
import model.Student;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.hibernate.utils.ViewUtils;
import com.pugwoo.hibernate.view.Container;
import com.pugwoo.hibernate.view.View;
import com.pugwoo.hibernate.view.cmp.Filter;
import com.pugwoo.hibernate.view.cmp.FilterItem;

public class ViewContextTest extends TestCase {

	private static ApplicationContext factory;

	private static Container viewContext;

	static {
		factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		viewContext = (Container) factory.getBean("viewContainer");
		viewContext.setEsc('`');
	}

	public static void main(String[] args) {

	}

	@SuppressWarnings("unchecked")
	public void testRead() {
		View dc = viewContext.getDynamicView("Student", "id,name,school.name sn",
				null);
		//List data = dc.read(-1, -1);
		Filter filter = new Filter(new FilterItem("sn", "=", null));
		List data = dc.read(filter, -1, -1);
		ViewUtils.print(dc, data);
	}
	
	@SuppressWarnings("unchecked")
	public void testRead2(){
		View dc = viewContext.getDynamicView("Student", "id,name,school.name",
				null);
		List data = dc.readObject(-1, -1);
		for(int i=0; i<data.size(); i++)
			System.out.println(((Student)data.get(i)).getName());
	}

	// 測試保持
	@SuppressWarnings("unchecked")
	public void testCreate() {
		View student = viewContext.getDynamicView("Student",
				"id,name,school.id si,school.name sn", null);
		List data = student.create("{\"name\":\"fd\",\"sn\":34}");
		// List data =
		// student.create("[{\"name\":\"fd\",\"si\":1},{\"name\":\"fc\"}]");
		ViewUtils.print(student, data);
	}

	@SuppressWarnings("unchecked")
	public void testCreate2() {
		// 一次只能运行一次
		View compositeId = viewContext
				.getDynamicView("CompositeId", null, null);
		// List data = compositeId.createSelf("{\"id\":3,\"name\":\"nick\"}");
		List data = compositeId
				.create("[{\"id\":3,\"name\":\"nick\"},{\"id\":5,\"name\":\"pugwoo\"}]");
		ViewUtils.print(compositeId, data);
	}

}
