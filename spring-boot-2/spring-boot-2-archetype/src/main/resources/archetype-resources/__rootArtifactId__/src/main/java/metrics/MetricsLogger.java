#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.metrics;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import ${groupId}.metrics.model.Measurements;

/**
 * Example class for logging the current metrics to an isolated metrics log, update this as required to implement your new applications per request metrics.
 */
@Component
public class MetricsLogger {

	private static final String METRICS_LOGGER_NAME = "metrics";

	private static final Logger LOG = LoggerFactory.getLogger( METRICS_LOGGER_NAME );

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	private RequestMeasurementsFactory requestMeasurementsFactory;
	
	public MetricsLogger( RequestMeasurementsFactory requestMeasurementsFactory ) {		
		this.requestMeasurementsFactory = requestMeasurementsFactory;
	}

	/**
	 * Log all of the available measurements for the current thread context
	 *
	 * @param object
	 */
	public void log( Object object ) {
		Measurements measurements = requestMeasurementsFactory.create( object );

		String json = toJson( measurements );

		if ( json != null ) {
			LOG.info( toJson( measurements ) );
		}
	}
	
	private String toJson( Measurements measurements ) {
		try {
			return OBJECT_MAPPER.writeValueAsString( measurements );	
		} catch ( IOException e ) {
			LOG.error( "Failed to log metrics", e );
			return null;
		}
	}

}