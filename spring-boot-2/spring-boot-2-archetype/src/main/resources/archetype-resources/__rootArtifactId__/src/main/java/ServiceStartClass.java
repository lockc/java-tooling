#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Bootstraps the app. This is the starting class.
 */
@SpringBootApplication
public class ServiceStartClass {

	private static final Logger LOG = LoggerFactory.getLogger( ServiceStartClass.class );

	public static void main( String[] args ) {
		LOG.info( "Starting ${application-name}" );
		new SpringApplicationBuilder().sources( ServiceStartClass.class ).run( args );
	}

}