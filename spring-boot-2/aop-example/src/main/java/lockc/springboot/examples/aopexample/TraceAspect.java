package lockc.springboot.examples.aopexample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Aspect
@Configuration
public class TraceAspect {

	private static final Logger LOG = LoggerFactory.getLogger( TraceAspect.class );

	@Around( "@annotation(lockc.springboot.examples.aopexample.Trace)" )
	public Object trace( ProceedingJoinPoint pjp ) throws Throwable {
		LOG.info( "Tracing event" );
		return pjp.proceed();
	}

}
