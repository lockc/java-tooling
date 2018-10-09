#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import static java.util.Arrays.asList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import ${package}.controller.PingController;

import net.paypoint.service.healthcheck.HealthCheckController;
import net.paypoint.service.healthcheck.version.CheckVersionFromClass;
import net.paypoint.service.healthcheck.webservice.RestServiceHealthCheck;

@Configuration
public class HealthCheckContext {

	@Bean
	public HealthCheckController healthCheck(
			RestServiceHealthCheck pingHealthCheck ) {

		HealthCheckController controller = new HealthCheckController();
		controller.setServiceName( "${application-name}" );
		controller.setHealthChecks( asList(
				serverVersion(),
				pingHealthCheck
		) );

		return controller;
	}

	@Bean
	public CheckVersionFromClass serverVersion() {
		CheckVersionFromClass checkVersionFromClass = new CheckVersionFromClass();
		checkVersionFromClass.setName( "Server Version" );
		checkVersionFromClass.setOrder( 1 );
		checkVersionFromClass.setTargetClass( PingController.class );

		return checkVersionFromClass;
	}

	@Bean
	public RestServiceHealthCheck pingHealthCheck( RestTemplate restTemplate, EnvironmentSettings settings ) {
		RestServiceHealthCheck restServiceHealthCheck = new RestServiceHealthCheck();
		restServiceHealthCheck.setName( "Ping Service is Available" );
		restServiceHealthCheck.setServiceUrl( settings.getHealthCheckUrl() );
		restServiceHealthCheck.setRestTemplate( restTemplate );
		restServiceHealthCheck.setOrder( 2 );

		return restServiceHealthCheck;
	}
}
