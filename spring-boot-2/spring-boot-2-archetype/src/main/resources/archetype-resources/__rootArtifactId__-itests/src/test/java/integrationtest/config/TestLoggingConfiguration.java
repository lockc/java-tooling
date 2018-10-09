#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtest.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import net.paypoint.wsutilities.logging.LoggingFilter;

/**
 *
 */
@TestConfiguration
@ComponentScan( "net.paypoint.wsutilities.logging" )
public class TestLoggingConfiguration {

	@Bean
	public LoggingFilter loggingFilter() {
		return new LoggingFilter();
	}

}
