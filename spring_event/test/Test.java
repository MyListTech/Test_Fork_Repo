
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pugwoo.LoginAction;

/**
 * 2011年1月9日 下午04:34:47
 */
public class Test {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");

		LoginAction loginAction = (LoginAction)factory.getBean("loginAction");
		
		loginAction.login("pugwoo", "123456");
	}

}
