package lockc.spring.examples.service;

import java.util.HashSet;
import java.util.Set;

import lockc.spring.examples.domain.ExampleEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * A service class for performing the actual work required to service the users resource.
 * 
 * @author clock
 *
 */
@Service
public class UserService
{
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * 
     * @return
     */
    public ExampleEntity getSearchStandingData()
    {

        // go off and get all the Service Providers
        Set<String> serviceProviders = new HashSet<>();
        serviceProviders.add("SERVICEHUB");
        serviceProviders.add("ANOTHER_SP");
        serviceProviders.add("YET_ANOTHER_SP");

        // go off and get all the roles
        Set<String> roles = new HashSet<>();
        roles.add("Carrefour - Role A");
        roles.add("Carrefour - Role B");
        roles.add("Carrefour - Role C");
        roles.add("Coca Cola - Role A");
        roles.add("Coca Cola - Role B");
        roles.add("Coca Cola - Role C");

        ExampleEntity data = new ExampleEntity();
        data.setServiceProviders(serviceProviders);
        data.setRoles(roles);

        LOGGER.debug(data.toString());

        return data;
    }

    /**
     * 
     * @param userQuery
     * @return
     */
    public Object query(Object userQuery)
    {

        return null;
    }
}
