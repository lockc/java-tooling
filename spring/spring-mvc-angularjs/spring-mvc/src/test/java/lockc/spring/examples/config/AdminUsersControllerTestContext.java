package lockc.spring.examples.config;

import static org.mockito.Mockito.mock;
import lockc.spring.examples.service.UserService;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = { "lockc.spring.examples.controller" })
public class AdminUsersControllerTestContext
{

    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public UserService userService()
    {
        return mock(UserService.class);
    }
}
