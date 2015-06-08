package lockc.spring.examples.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;
import javax.servlet.Filter;

import lockc.spring.examples.config.AdminSecurityConfig;
import lockc.spring.examples.config.AdminSecurityTestContext;
import lockc.spring.examples.config.AdminWebMvcConfig;
import lockc.spring.examples.controller.ExampleController;
import lockc.spring.examples.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Unit test class for the {@link ExampleController} controller.
 * 
 * @author clock
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AdminSecurityTestContext.class, AdminWebMvcConfig.class, AdminSecurityConfig.class })
@WebAppConfiguration
public class AdminSecurityTest
{
    /**
     * Used to perform HTTP methods operations and verify responses.
     */
    private MockMvc mockMvc;

    /**
     * Injected by Spring an used to create the MockMvc object
     */
    @Resource
    private WebApplicationContext conext;

    @Autowired
    private Filter springSecurityFilterChain;

    /**
     * Mocked version of the {@link UserService}
     */
    @Autowired
    private UserService userService;

    @Before
    public void setup()
    {
        // @formatter:off
        mockMvc = MockMvcBuilders.webAppContextSetup(conext)
                        .addFilters(springSecurityFilterChain)
                        .build();
        // @formatter:on
    }

    @Test
    public void testLoginFailed() throws Exception
    {
        // @formatter:off
        mockMvc.perform(formLogin().user("x").password("p"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
        // @formatter:on
    }

    @Test
    public void testLoginSucceded() throws Exception
    {
        // @formatter:off
        mockMvc.perform(formLogin().user("user01").password("password01"))
            .andExpect(status().is(HttpStatus.OK.value()));
        // @formatter:on
    }

    // Valid happy path, user authenticated
    @Test
    public void testSearchStandingData() throws Exception
    {
        // @formatter:off
        mockMvc.perform(get("/data")
                        .with(user("user01").roles("SP_ADMIN").password("password01")))
                
                .andExpect(status().is(HttpStatus.OK.value()));
        // @formatter:on
    }

    // Produces 401 Unauthorised
    @Test
    public void testSearchStandingDataAnnonymous() throws Exception
    {
        // @formatter:off
        mockMvc.perform(get("/data"))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
        // @formatter:on
    }



}
