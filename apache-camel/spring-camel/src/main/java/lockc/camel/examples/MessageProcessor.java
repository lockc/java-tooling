package lockc.camel.examples;

import org.apache.log4j.Logger;

/**
 * A POJO bean to process some messages, can be used in a bean component
 * using the bean:beanId URI as a camel endpoint. 
 * 
 * e.g.
 * 
 * <snippet>
 *   ...
 * 
 *   <from uri="test-jms:queue:camel-test" />
     <pipeline>
       <bean ref="myMessageProcessingBean" method="process"/>
       <to uri="test-jms:topic:camel-end" />
     </pipeline>
     
 *   
 *   
 *   <bean id="myMessageProcessingBean" class="lockc.camel.examples.MessageProcessor"/>
 *   
 *   <bean ref="myMessageProcessingBean" method="process"/>
 *
 *   .. 
 * </snippet>
 */
public class MessageProcessor {
    
    private static final Logger LOG = Logger.getLogger(MessageProcessor.class);
    
    public String process(String message) {
        LOG.info(String.format("Processing message %s", message));
        return message + " - processed!";
    }
}
