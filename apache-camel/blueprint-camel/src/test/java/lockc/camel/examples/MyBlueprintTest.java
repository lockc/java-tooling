package lockc.camel.examples;

import org.apache.camel.Exchange;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class MyBlueprintTest extends CamelBlueprintTestSupport {
    
    
    @Override
    protected String getBlueprintDescriptor() {
    
        return "OSGI-INF/blueprint/bundle-blueprint.xml";
    }
    
    @Test
    public void testRoute() throws Exception {
 
        // set mock expectations
        getMockEndpoint("mock:a").expectedBodiesReceived("Hello World!");
 
        // send a message
        template.sendBody("direct:start", "World!");
 
        // assert mocks
        assertMockEndpointsSatisfied();
 
    }
    
    @Override
    public boolean isUseDebugger() {
        // must enable debugger
        return true;
    }
 
    @Override
    protected void debugBefore(Exchange exchange, org.apache.camel.Processor processor, ProcessorDefinition<?> definition, String id, String label) {
        log.info("Before " + definition + " with body " + exchange.getIn().getBody());
    }
 
    @Override
    protected void debugAfter(Exchange exchange, org.apache.camel.Processor processor, ProcessorDefinition<?> definition, String id, String label, long timeTaken) {
        log.info("After " + definition + " with body " + exchange.getIn().getBody());
    }
}
