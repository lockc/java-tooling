package lockc.springboot.examples.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 *
 * @author lockc
 *
 */

/* Uncomment these to use this config */
// @EnableWebSecurity
// @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    
        auth.inMemoryAuthentication().withUser("lockc").password("pa55word").roles("USER").and().withUser("admin")
                .password("admin321").roles("ADMIN");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    
        http.authorizeRequests().antMatchers("/security/things").hasRole("ADMIN");
    }
}
