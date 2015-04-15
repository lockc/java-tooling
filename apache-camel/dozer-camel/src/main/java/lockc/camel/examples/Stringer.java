package lockc.camel.examples;

import org.springframework.stereotype.Component;

@Component("stringer-bean")
public class Stringer {
    
    public String stringify(Object object) {
        return object.getClass().getSimpleName() + " - " + object.toString();
    }
    
}
