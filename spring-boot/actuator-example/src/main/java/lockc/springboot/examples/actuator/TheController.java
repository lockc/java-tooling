package lockc.springboot.examples.actuator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "example")
public class TheController {
    
    @Autowired
    private MyPublicMetric myPublicMetric;
    
    @Autowired
    private CounterService counterService;
    
    @RequestMapping(method = RequestMethod.GET, value = "thing", produces = { "text/plain" })
    public String getThing() {
    
        counterService.increment(MyPublicMetric.NAME);
        
        myPublicMetric.record(3.2);
        
        return "the thing";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "things", produces = { "application/json" })
    public List<String> getThings() {
    
        counterService.decrement(MyPublicMetric.NAME);
        
        myPublicMetric.record(3.3);
        
        return Arrays.asList("first thing", "second thing", "third thing");
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "hello", produces = { "text/plain" })
    public String hello(@RequestParam(value = "data") String data) {
    
        return "hello " + data;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "echo", consumes = "text/plain", produces = { "text/plain" })
    public String echo(@RequestBody String data) {
    
        return data;
    }
    
}
