#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.pay360.metrics.AllMetricsEndpoint;
import com.pay360.metrics.RequestMeasurementService;
import com.pay360.metrics.config.HealthConfigurationProperties;
import com.pay360.metrics.config.HealthTimersConfiguration;
import com.pay360.metrics.config.MetricsConfiguration;
import com.pay360.metrics.config.MetricsConfigurationProperties;
import com.pay360.metrics.health.TimersMetricHealthIndicatorEndpoint;

import net.paypoint.servicetracing.springmvc.RestTemplateInterceptor;
import net.paypoint.wsutilities.logging.LoggingRequestInterceptor;

/**
 * Configuration for the Service's controllers.
 */
@Configuration
@Import( value = { MetricsConfiguration.class, HealthTimersConfiguration.class } )
@ComponentScan( basePackages = { "${groupId}.spring.boot.commons.controller" } )
public class ApplicationConfiguration {

	@Bean
	public ServletRegistrationBean dispatcherRegistration( DispatcherServlet dispatcherServlet ) {
		ServletRegistrationBean registration = new ServletRegistrationBean( dispatcherServlet );
		registration.addUrlMappings( "/*" );
		return registration;
	}

	@Bean
	public EnvironmentSettings environmentSettings( Environment env ) {
		return new EnvironmentSettings( env );
	}

	@Bean
	public LoggingRequestInterceptor loggingRequestInterceptor() {
		return new LoggingRequestInterceptor();
	}

	@Bean
	public RestTemplateInterceptor restTemplateInterceptor() {
		return new RestTemplateInterceptor();
	}

	@Bean
	public RestTemplate restTemplate( RestTemplateBuilder restTemplateBuilder, LoggingRequestInterceptor loggingRequestInterceptor, RestTemplateInterceptor restTemplateInterceptor ) {
		restTemplateBuilder.additionalInterceptors( restTemplateInterceptor, loggingRequestInterceptor );
		return restTemplateBuilder.build();
	}

	@Bean
	public HealthConfigurationProperties healthConfigurationProperties() {
		return new HealthConfigurationProperties();
	}

	@Bean
	public MetricsConfigurationProperties metricsConfigurationProperties() {
		return new MetricsConfigurationProperties();
	}

	@Bean
	public AllMetricsEndpoint allMetricsEndpoint( MetricsEndpoint metricsEndpoint ) {
		return new AllMetricsEndpoint( metricsEndpoint );
	}

	@Bean
	public RequestMeasurementService requestMeasurementService() {
		return new RequestMeasurementService();
	}

	@Bean
	public TimersMetricHealthIndicatorEndpoint timersMetricHealthIndicatorEndpoint() {
		return new TimersMetricHealthIndicatorEndpoint();
	}

	@Primary
	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper jsonMapper = new ObjectMapper();

		jsonMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false )
				.configure( SerializationFeature.INDENT_OUTPUT, false );

		return jsonMapper;
	}
}
