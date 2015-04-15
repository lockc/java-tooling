package lockc.spring.examples.core.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunJmxConfigMethodNameBased {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("jmx/jmx-config-method-name-based.xml");
			Thread.sleep(10000 * 60);
		} finally {
			if(context != null) 
				context.destroy();
		}
	}

}
