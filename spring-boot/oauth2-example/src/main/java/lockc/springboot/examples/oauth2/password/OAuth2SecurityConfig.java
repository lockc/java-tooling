package lockc.springboot.examples.oauth2.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class OAuth2SecurityConfig {
    
    @Bean
    public TokenStore tokenStore() {
    
        return new InMemoryTokenStore();
    }
    
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
        
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        
        @Autowired
        private TokenStore tokenStore;
        
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        
            // @formatter:off
            clients.inMemory().withClient("theClient")
            .secret("secret")
            .authorizedGrantTypes("password")
            .authorities("ROLE_CLIENT")
            .scopes("read", "write", "trust")
            .accessTokenValiditySeconds(300);
            // @formatter:on
        }
        
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        
            /*
             * Providing an AuthenticationManager to the AuthorizationServerConfigurer
             * tells it to enable the 'password' grant type, otherwise it is disabled.
             */
            // @formatter:off
            endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore);
            // @formatter:on
        }
    }
    
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        
        @Override
        public void configure(HttpSecurity http) throws Exception {
        
            /*
             * Only securing the 'thing' and 'logout' resources
             */
            // @formatter:off
            http
            .requestMatchers().antMatchers("/example/thing", "/security/logout")
            .and()
            .authorizeRequests().anyRequest().access("#oauth2.hasScope('trust')");
            // @formatter:off
        }
    }
}
