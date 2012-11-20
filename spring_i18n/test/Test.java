//2011年1月9日 下午03:13:00

import java.util.Calendar;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		Object[] arg = new Object[] { "Erica", Calendar.getInstance().getTime() };

		// 下面的arg是userinfo的参数
		String chineseMsg = factory.getMessage("userinfo", arg, Locale.CHINESE);
		System.out.println("Chinese Message is ===> " + chineseMsg);

		String englishMsg = factory.getMessage("userinfo", arg, Locale.US);
		System.out.println("English Message is ===> " + englishMsg);
		
		// 一般这么用：根据系统的不同而不同
		String sysDefaultMsg = factory.getMessage("userinfo", arg, Locale.getDefault());
		System.out.println("Default Message is ===> " + sysDefaultMsg);

	}

}
