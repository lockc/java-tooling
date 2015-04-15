package lockc.springboot.examples.oauth2.client_credentials;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class OAuth2SecurityConfig {
    
    @Autowired
    @Qualifier("oauthDataSource")
    public DataSource oauthDataSource;
    
    // @Bean
    // public TokenStore inMemoryTokenStore()
    // {
    // return new InMemoryTokenStore();
    // }
    
    @Bean
    public TokenStore jdbcTokenTokenStore() {
    
        return new JdbcTokenStore(oauthDataSource);
    }
    
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
        
        @Autowired
        private TokenStore tokenStore;
        
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        
            //@formatter:off
            clients.inMemory().withClient("theClient")
            .secret("secret")
            .authorizedGrantTypes("client_credentials")
            .authorities("ROLE_CLIENT")
            .scopes("read", "write", "trust")
            .accessTokenValiditySeconds(300);
            //@formatter:on
        }
        
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        
            endpoints.tokenStore(tokenStore);
        }
    }
    
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        
        @Override
        public void configure(HttpSecurity http) throws Exception {
        
            /*
             * Only securing the 'thing' resource with OAuth2
             */
            //@formatter:off
            http
            .requestMatchers().antMatchers("/example/thing")
            .and()
            .authorizeRequests().anyRequest().access("#oauth2.hasScope('trust')");
            //@formatter:off
        }
    }
}
