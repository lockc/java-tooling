package lockc.spring.examples.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Short circuits the standard Spring functionality for handling an authentication failure. All we want to do is return
 * a 401 Unauthorised status code back to the client so AngularJS can manage the view itself.
 * 
 * @author clock
 *
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler
{

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException exception) throws IOException, ServletException
    {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
