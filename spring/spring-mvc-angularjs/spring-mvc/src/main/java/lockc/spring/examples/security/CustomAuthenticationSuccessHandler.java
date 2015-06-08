package lockc.spring.examples.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class changes how an authentication success is handled. We want to return the logged in user's roles as a JSON
 * response body for AngularJS to handle.
 * 
 * @author clock
 *
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    /**
     * The Jackson2 databind {@link ObjectMapper} for Java to JSON serialisation
     */
    private ObjectMapper objectMapper;

    public CustomAuthenticationSuccessHandler()
    {
        objectMapper = new ObjectMapper();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String body = objectMapper.writeValueAsString(authentication.getAuthorities());
        response.getWriter().write(body);
        response.flushBuffer();
    }

}
