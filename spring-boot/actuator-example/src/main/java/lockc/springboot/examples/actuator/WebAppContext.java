package lockc.springboot.examples.actuator;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@EnableMBeanExport
@Configuration
public class WebAppContext extends WebMvcConfigurerAdapter {
    
}
