#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * An error handler to override the default Spring MVC error response for RESTful based errors
 * such as 405 Method Not Allowed and 406 Not Acceptable etc.
 * <p>
 * This implementation ensures API returns a the RESTful JSON response for MVC type errors that occur
 * within Spring's {@link DispatcherServlet} handler mapping process that do not reach our own controllers.
 */
@RestControllerAdvice
public class WebMvcErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger( WebMvcErrorHandler.class );
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.setSerializationInclusion( Include.NON_NULL );
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Add our own custom response body and call the overriden implementation to benefit from
	 * all the existing logic within the Spring framework
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal( Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request ) {
		ErrorResponse errorResponse = getErrorResponse( request, status, ex );
		String jsonBody = getResponseBody( errorResponse );
		LOG.error( "Handling exception.  Responding with: {}", jsonBody, ex );
		return super.handleExceptionInternal( ex, jsonBody, setHeaders( headers ), status, request );
	}

	@ExceptionHandler( Exception.class )
	@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
	public ErrorResponse handleInternalServerError( Exception ex, WebRequest request ) {
		LOG.error( "Handling internal server error" );
		return getErrorResponse( request, HttpStatus.INTERNAL_SERVER_ERROR, ex );
	}

	/**
	 * Serialise the ErrorResponse to a String JSON representation
	 *
	 * @param errorResponse the error response to serialise
	 * @return the error response as a JSON string
	 */
	private String getResponseBody( ErrorResponse errorResponse ) {
		String body = "{}";
		try {
			body = MAPPER.writeValueAsString( errorResponse );
		} catch ( JsonProcessingException e ) {
			LOG.error( "Failed to serialise the error response", e );
		}
		return body;
	}

	private ErrorResponse getErrorResponse( WebRequest request, HttpStatus status, Exception ex ) {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		HttpServletRequest httpServletRequest = servletWebRequest.getNativeRequest( HttpServletRequest.class );
		String uri = httpServletRequest != null ? httpServletRequest.getRequestURI() : null;
		return new ErrorResponse( String.valueOf( status.value() ), status.getReasonPhrase(), ex.getMessage(), uri, new Date() );
	}

	/*
	 * Add our own headers to the existing response headers, this is a bit long winded as we have to craft a new HttpHeaders
	 * object because the ones on the response are immutable
	 */
	private HttpHeaders setHeaders( HttpHeaders headers ) {
		HttpHeaders newHeaders = new HttpHeaders();
		// Add the header Content-Type=application/json
		newHeaders.setContentType( MediaType.APPLICATION_JSON );

		// Process the existing headers
		for ( Entry<String, List<String>> entry : headers.entrySet() ) {
			// Headers can contain lists of values so handle those cases
			Collection c = entry.getValue();
			String newHeaderValue = StringUtils.collectionToCommaDelimitedString( c );
			newHeaders.add( entry.getKey(), newHeaderValue );
		}
		return newHeaders;
	}

}
