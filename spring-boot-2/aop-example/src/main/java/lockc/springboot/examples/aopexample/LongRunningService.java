package lockc.springboot.examples.aopexample;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class LongRunningService {

	@Autowired
	private  SomeService someService;

	@EventListener(ApplicationReadyEvent.class)
	public void start() throws InterruptedException {

		long elapsed = System.currentTimeMillis() + 60000;
		while(true) {
			Thread.sleep( 1000 );
			someService.doSomething();
			if (System.currentTimeMillis() > elapsed ) {
				break;
			}
		}
	}

}
