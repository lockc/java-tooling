#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Configuration for the Service's controllers.
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration( DispatcherServlet dispatcherServlet ) {
		ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>( dispatcherServlet );
		registration.addUrlMappings( "/*" );
		return registration;
	}

	@Bean
	public EnvironmentSettings environmentSettings( Environment env ) {
		return new EnvironmentSettings( env );
	}

	@Bean
	public RestTemplate restTemplate( RestTemplateBuilder restTemplateBuilder ) {
		return restTemplateBuilder.build();
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
