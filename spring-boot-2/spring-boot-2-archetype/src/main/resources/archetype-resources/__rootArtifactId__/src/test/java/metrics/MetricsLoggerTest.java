#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )

package ${package}.metrics;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pay360.metrics.model.Measurements;

/**
 * Test for {@link MetricsLogger}
 */
@RunWith( MockitoJUnitRunner.class )
public class MetricsLoggerTest {

	@Mock
	private RequestMeasurementsFactory requestMeasurementsFactory;

	@InjectMocks
	private MetricsLogger metricsLogger;

	//TODO Update this test once you have implemented the metrics for your application.
	@Test
	public void shouldLog() {
		// Given
		Object object = Mockito.mock( Object.class );
		Measurements measurements = new Measurements( new HashMap<>(), new ArrayList<>() );
		when( requestMeasurementsFactory.create( object ) ).thenReturn( measurements );

		// When
		metricsLogger.log( object );

		// Then
		verify( requestMeasurementsFactory ).create( object );
	}
}