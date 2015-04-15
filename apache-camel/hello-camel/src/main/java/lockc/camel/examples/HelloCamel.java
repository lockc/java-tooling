package lockc.camel.examples;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloCamel {
    
    public static void main(String[] args) throws Exception {
    
        CamelContext context = new DefaultCamelContext();
        
        
        final String queueName = "camel-test";
        /*
         * An in memory option for ActiveMQ
         */
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        /*
         * Or, install ActiveMQ and start it and use a running local instance of ActiveMQ
         * 
         * http://localhost:8161/admin
         * 
         *  - create a queue called 'camel-test'
         *  - create a topic called 'camel-end'
         */
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        
        // Note we can explicit name the component
        context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // Add some configuration by hand ...
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("test-jms:queue:" + queueName)
                .to("file://test")
                .to("test-jms:topic:camel-end");
            }
        });
        
        // Camel template - a handy class for kicking off exchanges
        ProducerTemplate template = context.createProducerTemplate();
        
        // Now everything is set up - lets start the context
        context.start();
        
        // Now send some test text to a component         
        for (int i = 0; i < 10; i++) {
            template.sendBody("test-jms:queue:" + queueName, "Test Message: " + i);
        }
        
        // wait a bit and then stop
        Thread.sleep(1000);
        context.stop();
        
        
        
    }
    
}
