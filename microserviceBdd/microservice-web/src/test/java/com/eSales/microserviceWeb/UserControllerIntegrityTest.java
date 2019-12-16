package com.eSales.microserviceWeb;


import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserControllerIntegrityTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetUserList() throws Exception {
        String uri = "/AllUsers";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User[] userlist = super.mapFromJson(content, User[].class);
        assertTrue(userlist.length > 0);
    }

    @Test
    public void testNewUserWhenEmailExist() throws Exception {
        String uri = "/newUser";
        UserDto userDto = new UserDto();
        userDto.setName("nico");
        userDto.setLastName("bod");
        userDto.setEmail("bruce.lee@gmail.com");
        userDto.setPhone("060606006");
        userDto.setPassword("pass");
        userDto.setStreet("rue du test");
        userDto.setPostalCode(31200);
        userDto.setCity("Toulouse");
        userDto.setToken("tok");

        String inputJson = super.mapToJson(userDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("email already exist", content);
    }
}
