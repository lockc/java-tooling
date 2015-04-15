package lockc.springboot.examples.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "swagger")
@Api(value = "swagger-demo", description = "Sawgger Demo API documentation")
public class TheController {
    
    @ApiOperation(value = "Get the thingy", notes = "An operation to return the thingy that you request", nickname = "getTheThingy", response = String.class)
    @RequestMapping(method = RequestMethod.GET, value = "thing", produces = { "text/plain" })
    public String getThing() {
    
        return "the thing";
    }

    @ApiOperation(value = "Get the thingies", notes = "An operation to return the thingies that you request", nickname = "getTheThingies", response = String.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, value = "things", produces = { "application/json" })
    public List<String> getThings() {
    
        return Arrays.asList("first thing", "second thing", "third thing");
    }

    @ApiOperation(value = "Say Hello", notes = "An operation to say hello to the name you supplied", nickname = "Hi", response = String.class)
    @RequestMapping(method = RequestMethod.POST, value = "hello", produces = { "text/plain" })
    public String hello(@RequestParam(value = "data") String data) {
    
        return "hello " + data;
    }

    @ApiOperation(value = "Echo", notes = "An operation to echo whatever you sen it", nickname = "Echo", response = String.class)
    @RequestMapping(method = RequestMethod.POST, value = "echo", consumes = "text/plain", produces = { "text/plain" })
    public String echo(@RequestBody String data) {
    
        return data;
    }

}
