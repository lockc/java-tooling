#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.metrics;

import org.springframework.stereotype.Component;

import ${groupId}.metrics.model.Measurements;
import ${groupId}.metrics.RequestMeasurementService;

/**
 * Example factory for creating a populated {@link Measurements} for the current request thread
 */
@Component
public class RequestMeasurementsFactory {

	private RequestMeasurementService measurementService;
	
	public RequestMeasurementsFactory( RequestMeasurementService measurementService ) {
		this.measurementService = measurementService;
	}

	/**
	 * Create a {@link Measurements} using the given {@link Object}
	 **/
	public Measurements create( Object object ) {
		throw new IllegalStateException( "Method not implemented" );
	}
}
