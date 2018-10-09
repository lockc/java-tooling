#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.metrics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pay360.metrics.RequestMeasurementService;
import com.pay360.metrics.model.Measurement;
import com.pay360.metrics.model.Measurements;

/**
 * Test for {@link RequestMeasurementsFactory}
 */
@RunWith( MockitoJUnitRunner.class )
public class RequestMeasurementsFactoryTest {

	@Mock
	private RequestMeasurementService measurementService;

	@InjectMocks
	private RequestMeasurementsFactory factory;

	//TODO Update this test once you have implemented the metrics for your application.
	@Test
	public void shouldCreate() {
		//Given
		Object object = Mockito.mock( Object.class );

		//When
		try {
			factory.create( object );
			shouldHaveThrown( IllegalStateException.class );
		} catch ( IllegalStateException actual ) {
			//Then
			assertThat( actual ).hasMessage( "Method not implemented" );
		}
	}
}