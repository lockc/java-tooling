package lockc.springboot.examples.aopexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SomeService {

	private static final Logger LOG = LoggerFactory.getLogger( SomeService.class );

	@Trace
	public void doSomething() {
		LOG.info( "doSomething" );
	}

	public void doSomethingElse() {
		LOG.info( "doSomethingElse" );
	}
}
