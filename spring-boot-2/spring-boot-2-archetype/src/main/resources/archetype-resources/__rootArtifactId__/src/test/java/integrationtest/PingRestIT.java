#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import ${groupId}.common.api.response.models.ErrorResponse;
import ${package}.config.WebMvcConfiguration;
import ${package}.controller.PingController;
import com.pay360.spring.boot.commons.controller.WebMvcErrorHandler;

/**
 * Mock Spring MVC RESTful tests for the {@link PingController} class
 */
@RunWith( SpringRunner.class )
@WebMvcTest( controllers = PingController.class, secure = false )
@ContextConfiguration( classes = { WebMvcConfiguration.class, PingController.class, WebMvcErrorHandler.class } )
public class PingRestIT extends BaseIT {

	private static final String PING_ENDPOINT = "/ping";
	private static final String PING_CONTENT_TYPE = MediaType.TEXT_PLAIN_VALUE + ";charset=ISO-8859-1";
	private static final String PING_BODY = "OK";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGetPing() throws Exception {
		// Given
		RequestBuilder request = get( PING_ENDPOINT ).accept( MediaType.TEXT_PLAIN );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.OK.value() );
		assertThat( response.getContentAsString() ).isEqualTo( PING_BODY );
		assertThat( response.getHeader( HttpHeaders.CONTENT_TYPE ) ).isEqualTo( PING_CONTENT_TYPE );
	}

	@Test
	public void shouldGetPingGivenNoAcceptHeader() throws Exception {
		// Given
		RequestBuilder request = get( PING_ENDPOINT );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.OK.value() );
		assertThat( response.getContentAsString() ).isEqualTo( PING_BODY );
		assertThat( response.getHeader( HttpHeaders.CONTENT_TYPE ) ).isEqualTo( PING_CONTENT_TYPE );
	}

	@Test
	public void shouldGetOptions() throws Exception {
		// Given
		RequestBuilder request = options( PING_ENDPOINT );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.OK.value() );
		assertThat( response.getContentAsString() ).isEqualTo( "" );
		assertThat( response.getHeader( HttpHeaders.ALLOW ) ).isEqualTo( "GET,HEAD" );
	}

	@Test
	public void shouldGetHead() throws Exception {
		// Given
		RequestBuilder request = head( PING_ENDPOINT );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.OK.value() );
	}

	@Test
	public void shouldFailGivenPostMethodUsed() throws Exception {
		// Given
		RequestBuilder request = post( PING_ENDPOINT )
				.contentType( MediaType.TEXT_PLAIN )
				.content( "something" );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.METHOD_NOT_ALLOWED.value() );
		assertThat( response.getHeader( HttpHeaders.ALLOW ) ).isEqualTo( "GET" );

		ErrorResponse errorResponse = OBJECT_MAPPER.readValue( response.getContentAsString(), ErrorResponse.class );
		assertThat( errorResponse.getStatus() ).isEqualTo( "405" );
		assertThat( errorResponse.getError() ).isEqualTo( "Method Not Allowed" );
		assertThat( errorResponse.getMessage() ).isEqualTo( "Request method 'POST' not supported" );
		assertThat( errorResponse.getPath() ).isEqualTo( PING_ENDPOINT );
	}

	@Test
	public void shouldFailGivenPutMethodUsed() throws Exception {
		// Given
		RequestBuilder request = put( PING_ENDPOINT )
				.contentType( MediaType.TEXT_PLAIN )
				.content( "something" );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.METHOD_NOT_ALLOWED.value() );
		assertThat( response.getHeader( HttpHeaders.ALLOW ) ).isEqualTo( "GET" );

		ErrorResponse errorResponse = OBJECT_MAPPER.readValue( response.getContentAsString(), ErrorResponse.class );
		assertThat( errorResponse.getStatus() ).isEqualTo( "405" );
		assertThat( errorResponse.getError() ).isEqualTo( "Method Not Allowed" );
		assertThat( errorResponse.getMessage() ).isEqualTo( "Request method 'PUT' not supported" );
		assertThat( errorResponse.getPath() ).isEqualTo( PING_ENDPOINT );
	}

	@Test
	public void shouldFailGivenDeleteMethodUsed() throws Exception {
		// Given
		RequestBuilder request = delete( PING_ENDPOINT );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.METHOD_NOT_ALLOWED.value() );
		assertThat( response.getHeader( HttpHeaders.ALLOW ) ).isEqualTo( "GET" );

		ErrorResponse errorResponse = OBJECT_MAPPER.readValue( response.getContentAsString(), ErrorResponse.class );
		assertThat( errorResponse.getStatus() ).isEqualTo( "405" );
		assertThat( errorResponse.getError() ).isEqualTo( "Method Not Allowed" );
		assertThat( errorResponse.getMessage() ).isEqualTo( "Request method 'DELETE' not supported" );
		assertThat( errorResponse.getPath() ).isEqualTo( PING_ENDPOINT );
	}

	@Test
	public void shouldFailGivenUnacceptableMediaType() throws Exception {
		// Given
		RequestBuilder request = get( PING_ENDPOINT ).accept( MediaType.APPLICATION_ATOM_XML );

		// When
		MockHttpServletResponse response = mockMvc.perform( request ).andReturn().getResponse();

		// Then
		assertThat( response.getStatus() ).isEqualTo( HttpStatus.NOT_ACCEPTABLE.value() );
		ErrorResponse errorResponse = OBJECT_MAPPER.readValue( response.getContentAsString(), ErrorResponse.class );
		assertThat( errorResponse.getStatus() ).isEqualTo( "406" );
		assertThat( errorResponse.getError() ).isEqualTo( "Not Acceptable" );
		assertThat( errorResponse.getMessage() ).isEqualTo( "Could not find acceptable representation" );
		assertThat( errorResponse.getPath() ).isEqualTo( PING_ENDPOINT );
	}
}
