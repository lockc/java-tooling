package lockc.camel.examples;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
    
        // @formatter:off
        from("direct:starter-for-ten")
            .to("dozer:transform-my-type?targetModel=lockc.camel.examples.TypeB")
            .to("bean:stringer-bean?method=stringify")
            .to("file:dozer");
        // @formatter:on
    }
    
}
