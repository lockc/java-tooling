#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.integrationtest;

import org.springframework.boot.test.context.TestConfiguration;


/**
 * Default configuration required to get the Spring Boot app started for ET tests
 * <p>
 * Annotate your ET test class with <b>@ContextConfiguration(classes = DefaultTestConfiguration.class)</b>
 */
@TestConfiguration
public class DefaultTestConfiguration {

}