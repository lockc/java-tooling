package lockc.springboot.examples.oauth2.password;

import javax.ws.rs.FormParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "security")
public class SecurityController {
    
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    
    // @formatter:off
    /**
     * A login resource to obtain an access token from oauth/token end
     * point, in this example that end point is in the same application.
     *
     * @param clientId
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "login",
            consumes = "application/x-www-form-urlencoded", produces = { "application/json" })// @formatter:on
    public String login(@FormParam("clientId") String clientId, @FormParam("username") String username,
        @FormParam("password") String password) {
    
        Client client = ClientBuilder.newClient();
        
        /*
         * Not sure if this is a bug with Spring OAuth2 but when you do not provide the client ID the access token is
         * still granted, if you supply an invalid client ID a 401 is generated
         *
         * Surely a null client ID is same as invalid client ID?!
         */
        if (clientId == null) {
            clientId = "unknown";
        }
        
        Form form = new Form();
        form.param("grant_type", "password");
        form.param("client_id", clientId);
        form.param("username", username);
        form.param("password", password);
        
        /*
         * The /oauth/token endpoint is by default secured by Spring using Basic Authentication comprising a Base64
         * encoded <client_id>:<secret>
         */
        Response response = client.target("http://localhost:8080").path("/oauth/token").request("application/json")
                .header("Authorization", "Basic dGhlQ2xpZW50OnNlY3JldA==")
                .post(Entity.entity(form, "application/x-www-form-urlencoded"));
        
        return response.readEntity(String.class);
    }
    
    // @formatter:off
    /**
     * Logout resource that invalidates the given access token, this resource is itself
     * protected by OAuth.
     *
     * This will invalidate the access token and the refresh token.
     *
     * @param token
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "logout",
            consumes = "application/x-www-form-urlencoded", produces = { "application/json" })// @formatter:on
    public String logout(@FormParam("token") String token) {
    
        boolean success = defaultTokenServices.revokeToken(token);
        return String.format("{\"success\":%s}", success);
    }
    
}
