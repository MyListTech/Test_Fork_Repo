package demo;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * 用于dao层的单独运行（也适合于非servlet项目）
 */
public class DBTest {

	public static void start() {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/jfinal",
				"root", "root");
		cp.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		
		arp.addMapping("student", Student.class);
		
		arp.start();
	}
}
