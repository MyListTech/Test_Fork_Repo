package demo;

import com.jfinal.plugin.activerecord.Model;

/**
 * 2012年12月30日
 */
@SuppressWarnings({ "unused", "serial" })
public class Student extends Model<Student> {

	public static final Student dao = new Student();
	
	public Student(){}
	public Student(String name){
		setName(name);
	}
	
	public static void main(String[] args) {
		DBTest.start();
		Student s = new Student("peter");
		//s.setName("peter");
		s.save();
		System.out.println(s);
	}

	private long id;

	public long getId() {
		return this.get("id");
	}

	public void setId(long id) {
		this.set("id", id);
	}

	/**
	 * jfinal没有所谓的getter/setter，所以根本就不是一个orm
	 * jfinal作者：“其实JFinal Model最大的缺点就是少了一个 IDE 对代码的静态检查，但JFinal AR非常有利于敏捷开发，对于修改数据库表响应非常迅速。”
	 *
	 * 有个想法：
	 * 其实getName()就等价于this.getAttr("name")
	 * 其实setName()就等价于this.set("name", name)
	 */
	
	public void setName(String name) {
		this.set("name", name);
	}
	
	public String getName() {
		return this.get("name");
	}
	
	public void setScore(float score) {
		this.set("score", score);
	}
	
	public float getScore() {
		return this.getFloat("score");
	}

}
