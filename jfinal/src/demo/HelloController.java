package demo;

import com.jfinal.core.Controller;
/**
 * 2012年12月29日
 */
public class HelloController extends Controller {

	/**
	 * http://localhost/hello
	 */
	public void index() {
		renderText("Hello JFinal world.");
	}

	/**
	 * http://localhost/hello/myPara/v0-v1-v2?name=pugwoo
	 * 使用getPara方法，如果参数是String，则获取传递的参数 如果参数是int，则获取restful url的参数
	 */
	public void myPara() {
		String name = getPara("name");
		String restful = getPara();
		renderText("hello " + name + "; restful para:" + restful);
	}
	
	/**
	 * http://localhost/hello/myRender
	 */
	public void myRender() {
		// 传递参数至页面
		setAttr("name", "pugwoo");
		render("index.jsp");
	}
	
	/**
	 * http://localhost/hello/myJson
	 */
	public void myJson() {
		// 将所有的setAttr render为json格式
		setAttr("name", "tom");
		setAttr("age", 16);
		renderJson();
	}
	
	/**
	 * http://localhost/hello/db
	 */
	public void db() {
		DAO dao = new DAO();
		dao.insertStudent("nick", 99);
		
		// <del>这一段是不被支持的！</del> 已经想办法让它支持了
		Student student = new Student();
		student.setName("karen");
		student.setScore(100);
		student.save();
		
		renderText("插入成功");
	}
}