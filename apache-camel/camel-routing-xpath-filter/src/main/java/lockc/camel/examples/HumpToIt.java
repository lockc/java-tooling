package lockc.camel.examples;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * http://camel.apache.org/spring.html
 * http://camel.apache.org/xpath.html
 * 
 * This example should route all XML messages that are marked
 * as explicit to another JMS queue and also to a file.
 */
@Component
public class HumpToIt {
    
    private static final Logger LOG = Logger.getLogger(HumpToIt.class);
    
    @Produce(uri = "test-jms:queue:content")
    private MyProducer producer;
    
    @Produce(uri = "direct:starter-for-ten")
    private MyProducer producerForTen;
    
    /*
     * send 10 XML messages half of which are explicit content
     */
    public void doSomething() {
        
        for (int i = 0; i < 10; i++) {
            
            String message = "<?xml version=\"1.0\"?>"
                    + "<Something>"
                    + "    <Content-Warning explicit=\"" + String.valueOf(i % 2 == 0) + "\"/>"
                    + "    <Metadata>"
                    + "        <SomeTag>Hello " + i + "</SomeTag>"
                    + "    </Metadata>"
                    + "</Something>";
            
            LOG.info("Sending message: " + message);
            producer.sendMessage(message);
        }
    }
    
    public void doSomethingElse() {
        
        String message = "1915de4517af462e8fd2f63c97227496";
        LOG.info("Sending message: " + message);
        producerForTen.sendMessage(message);
        
    }
    
    public static void main(String[] args) throws Exception {
    
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        HumpToIt helloCamel = springContext.getBean(HumpToIt.class);
//        helloCamel.doSomething();
        
        helloCamel.doSomethingElse();
        
        
        
                
        // wait a bit and then stop
        Thread.sleep(1000);
        
        CamelContext camelContext = springContext.getBean(CamelContext.class);
        camelContext.stop();
             
        springContext.close();
    }
    
}
