package lockc.spring.examples;

import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class RestResponse<T> extends ResourceSupport {

	private final T data;

	@JsonCreator
	public RestResponse(@JsonProperty("data") T data ) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
}
