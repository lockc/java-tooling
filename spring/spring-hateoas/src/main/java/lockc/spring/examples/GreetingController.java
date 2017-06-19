package lockc.spring.examples;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping( "/greeting" )
	public HttpEntity<RestResponse> greeting(
			@RequestParam( value = "name", required = false, defaultValue = "World" ) String name ) {

		RestResponse<String> response = new RestResponse<>( String.format( TEMPLATE, name ) );

		response.add( linkTo( methodOn( GreetingController.class ).greeting( name ) ).withSelfRel() );

		return new ResponseEntity<>( response, HttpStatus.OK );
	}
}
