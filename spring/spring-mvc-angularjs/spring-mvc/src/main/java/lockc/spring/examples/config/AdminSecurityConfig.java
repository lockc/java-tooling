package lockc.spring.examples.config;

import lockc.spring.examples.security.CustomAccessDeniedHandler;
import lockc.spring.examples.security.CustomAuthenticationFailureHandler;
import lockc.spring.examples.security.CustomAuthenticationSuccessHandler;
import lockc.spring.examples.security.CustomLoginUrlEntryPoint;
import lockc.spring.examples.security.CustomLogoutSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private CustomLoginUrlEntryPoint customLoginUrlEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        // @formatter:off
        auth
            .inMemoryAuthentication()
                .withUser("user01").password("password01").roles("SP_CM", "SP_Operations", "SP_Helpdesk", "SP_Admin")
            .and()
                .withUser("user02").password("password02").roles("SP_CM", "SP_Operations", "SP_Helpdesk");
        // @formatter:on
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.
     * springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        // @formatter:off
        
        /*
         * Exception handling
         */
        http.exceptionHandling()
            .accessDeniedHandler(customAccessDeniedHandler)
            .authenticationEntryPoint(customLoginUrlEntryPoint);
        
        /*
         * Login security
         */
        http.authorizeRequests().antMatchers("/login").permitAll();
        
        http.formLogin()
            .loginProcessingUrl("/login")
            .failureHandler(customAuthFailureHandler)
            .successHandler(customAuthSuccessHandler);

        /*
         * Logout security
         */
        http.authorizeRequests().antMatchers("/logout").permitAll().anyRequest().authenticated();
        
        http.logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutUrl("/logout")
            .logoutSuccessHandler(customLogoutSuccessHandler);
        
        /*
         * OWASP
         */
        // TODO: enable - https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-spring-security-part-ii
        http.csrf().disable();



        // Auth and Auth required on the resources.
        
        /*
         * TODO: currently anybody can do any action under the users resource.  This 
         * should change when we know what the roles are authorized to do 
         */
        http.authorizeRequests()
            .antMatchers("/users/*").permitAll().anyRequest().authenticated();

        /*
         * Session management
         */
        http.sessionManagement().maximumSessions(1);

        // @formatter:on
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.
     * springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/resources/**", "/static/**");
    }
}
