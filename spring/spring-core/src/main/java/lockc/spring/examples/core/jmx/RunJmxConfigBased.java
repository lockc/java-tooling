package lockc.spring.examples.core.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunJmxConfigBased {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("jmx/jmx-config-based.xml");
			Thread.sleep(10000 * 60);
		} finally {
			if(context != null) 
				context.destroy();
		}
	}

}
