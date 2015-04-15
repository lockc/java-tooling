package lockc.springboot.examples.asyncsupport;

import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {
    
    @RequestMapping(method = RequestMethod.GET, value = "thing", produces = { "text/plain" })
    public String getThing() {
    
        return "the thing";
    }

    @RequestMapping(method = RequestMethod.GET, value = "asyncthing", produces = { "text/plain" })
    public Callable<ResponseEntity<String>> getAsyncThing() {
    
        return () -> new ResponseEntity<String>("the async thing", HttpStatus.OK);
    }

}
