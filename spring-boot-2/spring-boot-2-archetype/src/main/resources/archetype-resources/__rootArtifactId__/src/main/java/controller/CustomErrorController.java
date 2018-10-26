#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Controller that handles any error requests. Overrides the default Spring boot error view
 */
@RestController
public class CustomErrorController implements ErrorController {

	private static final Logger LOG = LoggerFactory.getLogger( CustomErrorController.class );

	private static final String ERROR_PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping( path = ERROR_PATH, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ErrorResponse handleError( HttpServletRequest request ) {
		LOG.error( "Handling error thrown by Spring " );
		return getErrorResponse( request );
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	private ErrorResponse getErrorResponse( HttpServletRequest request ) {
		Map<String, Object> attributes = getErrorAttributes( request );
		String trace = (String) attributes.get( "trace" );
		if ( trace != null ) {
			String[] lines = trace.split( "\n\t" );
			attributes.put( "trace", lines );
		}

		Object statusAttribute = attributes.get( "status" );
		Integer status = (Integer) statusAttribute;
		String messageAttribute = (String) attributes.get( "message" );

		if ( HttpStatus.NOT_FOUND.value() == status ) {
			messageAttribute = "The requested resource was not found";
		}

		return new ErrorResponse( String.valueOf( status ),
				(String) attributes.get( "error" ),
				messageAttribute,
				(String) attributes.get( "path" ),
				new Date() );
	}

	private Map<String, Object> getErrorAttributes( HttpServletRequest request ) {
		ServletWebRequest webRequest = new ServletWebRequest( request );
		return errorAttributes.getErrorAttributes( webRequest, false );
	}
}