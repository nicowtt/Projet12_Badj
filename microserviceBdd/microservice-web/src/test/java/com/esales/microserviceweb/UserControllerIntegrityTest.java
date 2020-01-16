package com.esales.microserviceweb;


import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserControllerIntegrityTest extends AbstractTest {

    /** Jeu de données **/
    private UserDto userDtoTest;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        userDtoTest = new UserDto();
        userDtoTest.setName("nico");
        userDtoTest.setLastName("bod");
        userDtoTest.setEmail("bruce.lee@gmail.com");
        userDtoTest.setPhone("060606006");
        userDtoTest.setPassword("pass");
        userDtoTest.setStreet("rue du test");
        userDtoTest.setPostalCode(31200);
        userDtoTest.setCity("Toulouse");
        userDtoTest.setToken("tok");
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
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("email already exist", content);
    }

    @Test
    public void testCheckUserLoginPassWrong() throws Exception{
        String uri = "/checkUserLogIn";
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(406, status);
    }

    @Test
    public void testCheckUserLoginPassOK() throws Exception{
        userDtoTest.setPassword("mdp");
        String uri = "/checkUserLogIn";
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testUserStateChange() throws Exception {
        userDtoTest.setPassword("mdp");
        String uri = "/userStateChanged";
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Boolean result = super.mapFromJson(content,Boolean.class);
        assertEquals(true, result);
    }
}
