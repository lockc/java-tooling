#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.core.env.Environment;

/**
 * Environment settings.
 */
public class EnvironmentSettings {

	private final String healthCheckUrl;

	public EnvironmentSettings( Environment env ) {

		// Required properties
		this.healthCheckUrl = env.getRequiredProperty( "${application-acronym}.healthCheck.url" );
	}

	public String getHealthCheckUrl() {
		return healthCheckUrl;
	}
}
