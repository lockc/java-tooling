package lockc.spring.examples.controller;

import lockc.spring.examples.domain.ExampleEntity;
import lockc.spring.examples.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController
{
    private final static String MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/data", produces = { MEDIA_TYPE })
    public ResponseEntity<ExampleEntity> standingData()
    {
        ExampleEntity response = userService.getSearchStandingData();
        return new ResponseEntity<ExampleEntity>(response, HttpStatus.OK);
    }

}
