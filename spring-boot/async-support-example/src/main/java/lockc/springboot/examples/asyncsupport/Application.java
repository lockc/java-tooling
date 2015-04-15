package lockc.springboot.examples.asyncsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The annotation {@link SpringBootApplication} is equivalent to declaring these three:
 * 
 * @Configuration
 * @ComponentScan(basePackageClasses= {Application.class})
 * @EnableAutoConfiguration
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
    
        SpringApplication.run(Application.class, args);
    }
}
