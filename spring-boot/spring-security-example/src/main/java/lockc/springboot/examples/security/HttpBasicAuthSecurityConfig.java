package lockc.springboot.examples.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * A simple HTTP Basic Authentication example using Spring Security.
 *
 * @author lockc
 *
 */
@EnableWebSecurity
@Configuration
public class HttpBasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    
        auth.inMemoryAuthentication().withUser("lockc").password("pa55word").roles("USER").and().withUser("admin")
                .password("admin321").roles("ADMIN");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    
        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic();
    }
}
