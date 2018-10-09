#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.paypoint.servicetracing.context.DefaultTraceEventFactory;
import net.paypoint.servicetracing.context.ServiceTraceContext;
import net.paypoint.servicetracing.servlet.ServletTracingFilter;
import net.paypoint.servicetracing.context.ServiceTraceContextImpl;
import net.paypoint.servicetracing.json.JSONSerializingLogEventListener;

/**
 * Trace filter context.
 */
@Configuration
public class TracingContext {

	@Bean
	public FilterRegistrationBean<ServletTracingFilter> servletTracingFilterRegistration( ServletTracingFilter servletTracingFilter ) {
		FilterRegistrationBean<ServletTracingFilter> registration = new FilterRegistrationBean();
		registration.setFilter( servletTracingFilter );
		registration.setOrder( 1 );
		return registration;
	}

	@Bean
	public ServiceTraceContext serviceTraceContext() {
		DefaultTraceEventFactory traceEventFactory = new DefaultTraceEventFactory();
		traceEventFactory.setSystemId( "${groupId}:${artifactId}" );

		ServiceTraceContextImpl traceContext = new ServiceTraceContextImpl( traceEventFactory );
		traceContext.setDefaultListener( new JSONSerializingLogEventListener() );

		return traceContext;
	}

	@Bean
	public ServletTracingFilter servletTracingFilter( ServiceTraceContext serviceTraceContext ) {
		ServletTracingFilter tracingFilter = new ServletTracingFilter();
		tracingFilter.setTraceContext( serviceTraceContext );

		return tracingFilter;
	}
}
