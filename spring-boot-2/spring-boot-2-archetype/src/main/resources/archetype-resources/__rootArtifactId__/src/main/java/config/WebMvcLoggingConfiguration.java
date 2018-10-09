#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.paypoint.wsutilities.logging.LoggingFilter;
import net.paypoint.wsutilities.logging.PrettySecureJsonFilter;

/**
 * Configuration for the logging infrastructure.
 */
@Configuration
@ComponentScan( "net.paypoint.wsutilities.logging" )
public class WebMvcLoggingConfiguration {

	@Bean
	public LoggingFilter loggingFilter() {
		return new LoggingFilter();
	}

	@Bean
	public PrettySecureJsonFilter prettySecureJsonFilter( ObjectMapper objectMapper ) {
		PrettySecureJsonFilter filter = new PrettySecureJsonFilter();
		filter.setObjectMapper( objectMapper );
		return filter;
	}

	@Bean
	public FilterRegistrationBean loggingFilterRegistration( LoggingFilter loggingFilter ) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter( loggingFilter );
		registration.setOrder( 2 );
		return registration;
	}
}

