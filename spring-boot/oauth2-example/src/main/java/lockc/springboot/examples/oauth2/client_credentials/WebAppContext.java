package lockc.springboot.examples.oauth2.client_credentials;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
public class WebAppContext extends WebMvcConfigurerAdapter {

    @Bean(name = "oauthDataSource")
    public DataSource oauthDataSource() {
    
        DriverManagerDataSource ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:src/main/resources/oauth2.db");
        return ds;
    }

}
