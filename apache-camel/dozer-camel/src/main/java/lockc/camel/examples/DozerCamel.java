package lockc.camel.examples;

import java.util.Calendar;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * http://camel.apache.org/spring.html
 * http://camel.apache.org/bean-integration.html
 * http://camel.apache.org/dozer.html
 * 
 */
@Component
public class DozerCamel {
    
    private static final Logger LOG = Logger.getLogger(DozerCamel.class);
    
    public static void main(String[] args) throws Exception {
    
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        
                
        // wait a bit and then stop
        Thread.sleep(1000);
        
        CamelContext camelContext = springContext.getBean(CamelContext.class);
        ProducerTemplate producer = camelContext.createProducerTemplate();
        producer.setDefaultEndpointUri("direct:starter-for-ten");
        
        
        for (int i = 0; i < 10; i++) {
            String message = "Test Message: " + i;
            LOG.info("Sending message: " + message);
            
            TypeA typeA = new TypeA();
            typeA.setFieldA(message);
            typeA.setFieldB(999999999);
            typeA.setFieldX(Calendar.getInstance().getTime());
            
            producer.sendBody(typeA);
        }
        
        camelContext.stop();
        springContext.close();
    }
    
}
