package lockc.camel.examples;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A java based route builder example 
 * 
 */
@Component
public class MyRouteBuilder extends RouteBuilder {
    
    private static final String queueName = "camel-test";
    private static final String topicName = "camel-end";
    
    @Override
    public void configure() throws Exception {
    
        // @formatter:off
        from("test-jms:queue:" + queueName)
            .to("file://test")
            .to("test-jms:topic:" + topicName);
        // @formatter:on
    }
    
}
