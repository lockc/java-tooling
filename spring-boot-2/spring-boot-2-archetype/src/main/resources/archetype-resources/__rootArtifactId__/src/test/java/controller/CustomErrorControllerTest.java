#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * Test class for {@link CustomErrorController}
 */
@RunWith( MockitoJUnitRunner.class )
public class CustomErrorControllerTest {

	@Mock
	private ErrorAttributes errorAttributes;

	@InjectMocks
	private CustomErrorController controller;

	@Test
	public void shouldGetErrorPath() throws Exception {
		// Given

		// When
		String actual = controller.getErrorPath();

		// Then
		assertThat( actual ).isEqualTo( "/error" );
	}

	@Test
	public void shouldHandleError() throws Exception {
		// Given
		Date start = new Date();
		Map<String, Object> attributes = new HashMap<>();
		attributes.put( "status", 400 );
		attributes.put( "error", "Bad Request" );
		attributes.put( "message", "That was a bad request" );
		attributes.put( "path", "/my/request/uri" );
		MockHttpServletRequest mockRequest = new MockHttpServletRequest( "GET", "/my/request/uri" );
		when( errorAttributes.getErrorAttributes( any(), anyBoolean() ) ).thenReturn( attributes );

		// When
		ErrorResponse actual = controller.handleError( mockRequest );

		// Then
		Date end = new Date();
		assertThat( actual ).isNotNull();
		assertThat( actual.getPath() ).isEqualTo( "/my/request/uri" );
		assertThat( actual.getMessage() ).isEqualTo( "That was a bad request" );
		assertThat( actual.getError() ).isEqualTo( "Bad Request" );
		assertThat( actual.getStatus() ).isEqualTo( "400" );
		assertThat( actual.getTimestamp() ).isBetween( start, end, true, true );
	}
}
