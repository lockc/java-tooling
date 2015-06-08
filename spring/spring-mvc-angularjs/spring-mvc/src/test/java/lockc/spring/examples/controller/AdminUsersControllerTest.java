package lockc.spring.examples.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.Resource;

import lockc.spring.examples.config.AdminUsersControllerTestContext;
import lockc.spring.examples.config.AdminWebMvcConfig;
import lockc.spring.examples.controller.ExampleController;
import lockc.spring.examples.domain.ExampleEntity;
import lockc.spring.examples.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@ContextConfiguration(classes = { AdminUsersControllerTestContext.class, AdminWebMvcConfig.class })
@WebAppConfiguration
public class AdminUsersControllerTest
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

    /**
     * Mocked version of the {@link UserService}
     */
    @Autowired
    private UserService userService;

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(conext).build();
    }

    // Valid happy path
    @Test
    public void testSearchStandingDataGET() throws Exception
    {

        ExampleEntity data = new ExampleEntity();
        data.setRoles(new HashSet<String>(Arrays.asList("ROLE_A", "ROLE_B")));
        data.setServiceProviders(new HashSet<String>(Arrays.asList("SP_ONE", "SP_TWO")));

        when(userService.getSearchStandingData()).thenReturn(data);

        String expected = "{roles:[\"ROLE_A\",\"ROLE_B\"],serviceProviders:[\"SP_ONE\",\"SP_TWO\"]}";

        // @formatter:off
        mockMvc.perform(get("/data"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
        // @formatter:on

    }

    // results in 405 Method Not Allowed
    @Test
    public void testSearchStandingDataPOST() throws Exception
    {
        // @formatter:off
        mockMvc.perform(post("/data"))
                .andExpect(status().is(HttpStatus.METHOD_NOT_ALLOWED.value()));
        // @formatter:on
    }

    // results in 405 Method Not Allowed
    @Test
    public void testSearchStandingDataPUT() throws Exception
    {
        // @formatter:off
        mockMvc.perform(put("/data"))
                .andExpect(status().is(HttpStatus.METHOD_NOT_ALLOWED.value()));
        // @formatter:on
    }

    // results in 405 Method Not Allowed
    @Test
    public void testSearchStandingDataDELETE() throws Exception
    {
        // @formatter:off
        mockMvc.perform(delete("/data"))
                .andExpect(status().is(HttpStatus.METHOD_NOT_ALLOWED.value()));
        // @formatter:on
    }
}
