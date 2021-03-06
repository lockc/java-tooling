#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtest.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestConfiguration
@ComponentScan( basePackages = { "${package}.controller" } )
public class ApplicationConfiguration {

	@Bean
	public MockMvc mockMvc( WebApplicationContext context ) {
		return MockMvcBuilders.webAppContextSetup( context ).build();
	}

}
