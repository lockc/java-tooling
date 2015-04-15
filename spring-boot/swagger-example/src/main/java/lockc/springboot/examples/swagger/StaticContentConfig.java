package lockc.springboot.examples.swagger;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.mangofactory.swagger.plugin.EnableSwagger;

/**
 * This is required to get sprint to initialise the {@link RequestMappingHandlerMapping} 
 * with the configuration in {@link WebMvcAutoConfigurationAdapter}. This 
* means it
 * 
 * 
 * will map the contents of resources under /static folder on the class path.
 *
 * @author lockc
 *
 */
@EnableWebMvc
@EnableSwagger
@Configuration
public class StaticContentConfig extends WebMvcAutoConfigurationAdapter {
    
}
