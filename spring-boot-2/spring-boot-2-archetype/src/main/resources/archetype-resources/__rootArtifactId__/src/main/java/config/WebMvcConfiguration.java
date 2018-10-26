#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC web configuration class - Implement any specific required behaviour e.g. specific message converters
 */
@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

}
