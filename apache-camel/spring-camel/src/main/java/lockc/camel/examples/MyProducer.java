package lockc.camel.examples;

import org.apache.camel.InOnly;


/**
 * Our producer that will send messages to the JMS queue
 *
 */
public interface MyProducer {
    
    @InOnly
    void sendMessage(String message);
}
