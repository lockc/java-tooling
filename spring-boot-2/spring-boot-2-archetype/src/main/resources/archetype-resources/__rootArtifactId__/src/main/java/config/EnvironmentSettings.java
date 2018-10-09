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
	private int healthCheckWindowSize;
	private long healthCheckCacheTime;


	public EnvironmentSettings( Environment env ) {

		// Required properties
		this.healthCheckUrl = env.getRequiredProperty( "${application-acronym}.healthCheck.url" );


		// Optional properties
		this.healthCheckWindowSize = env.getProperty( "${application-acronym}.healthCheck.window.size.secs", Integer.class, 10 );
		this.healthCheckCacheTime = env.getProperty( "${application-acronym}.healthCheck.cache.time.secs", Integer.class, 0 );
	}

	public String getHealthCheckUrl() {
		return healthCheckUrl;
	}

	public int getHealthCheckWindowSize() {
		return healthCheckWindowSize;
	}

	public long getHealthCheckCacheTime() {
		return healthCheckCacheTime;
	}
}
