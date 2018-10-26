#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtest.config;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ${groupId}.metrics.config.HealthConfigurationProperties;


import net.paypoint.service.healthcheck.HealthCheckController;
import net.paypoint.service.healthcheck.api.HealthCheck;
import net.paypoint.servicetracing.context.ServiceTraceContext;

@TestConfiguration
@ComponentScan( basePackages = { "${package}.controller", "${groupId}.spring.boot.commons.controller" } )
public class ApplicationConfiguration {

	@Bean
	public MockMvc mockMvc( WebApplicationContext context ) {
		return MockMvcBuilders.webAppContextSetup( context ).build();
	}

	@Bean
	public HealthCheckController healthCheckController() {
		return mock( HealthCheckController.class );
	}

	@Bean
	public List<HealthCheck> healthChecks() {
		return new ArrayList<>();
	}

	@Bean
	public List<HealthIndicator> healthIndicators() {
		return new ArrayList<>();
	}

	@Bean
	public HealthConfigurationProperties healthConfigurationProperties() {
		return new HealthConfigurationProperties();
	}

	@Bean
	public ServiceTraceContext serviceTraceContext() {
		return mock( ServiceTraceContext.class );
	}
}
