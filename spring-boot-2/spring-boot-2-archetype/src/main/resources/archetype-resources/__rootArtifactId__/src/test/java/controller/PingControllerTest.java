#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link PingController}
 */
public class PingControllerTest {

	private PingController controller;

	@Before
	public void setUp() throws Exception {
		controller = new PingController();
	}

	@Test
	public void shouldReturnOK() throws Exception {

		// Given

		// When
		String actual = controller.ping();

		// Then
		assertThat( actual ).isEqualTo( "OK" );
	}
}
