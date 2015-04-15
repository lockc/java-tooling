package lockc.springboot.examples.oauth2.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author lockc
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    
        // @formatter:off

        auth.inMemoryAuthentication()
        .withUser("lockc").password("pa55word").roles("USER").and()
        .withUser("admin").password("admin123").roles("ADMIN", "USER");

        // @formatter:on
        
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        // @formatter:off

        /*
         * Disable CSRF here because we are not using cookies to manage authentication,
         * this example is using the password grant type and state-less JAX-RS
         * web services.  Not using cookies in the browser to remember session
         * information means it is not vulnerable to CSRF.
         */
        http.csrf().disable();

        //        http.authorizeRequests().anyRequest().hasRole("USER");

        // @formatter:on
        
    }
}
