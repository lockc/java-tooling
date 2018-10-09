#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtest;

import static org.mockito.Mockito.reset;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import ${package}.config.WebMvcConfiguration;
import ${package}.integrationtest.config.TestLoggingConfiguration;
import com.pay360.spring.boot.commons.controller.WebMvcErrorHandler;

/**
 * Base test class for wiring in the necessary context configuration for the integration tests.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration( classes = { TestLoggingConfiguration.class, WebMvcConfiguration.class, WebMvcErrorHandler.class  } )
@DirtiesContext( classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD )
public abstract class BaseIT {

	@Autowired
	protected MockMvc mockMvc;

	@Mock
	private RestTemplate mockRestTemplate;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup( context ).build();

		reset( mockRestTemplate );
	}

}
