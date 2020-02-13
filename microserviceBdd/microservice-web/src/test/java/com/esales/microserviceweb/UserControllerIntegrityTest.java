package com.esales.microserviceweb;

import com.esales.microservicebusiness.impl.AddressManagerImpl;
import com.esales.microservicebusiness.impl.UserManagerImpl;
import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserControllerIntegrityTest extends AbstractTest {

    /** Jeu de données **/
    private UserDto userDtoTest;
    private Address addressDtoTest;
    private User oldUserTest;
    private Optional<Address> oldAddressTest;

    @Autowired
    private UserManagerImpl userManagerImpl;

    @Autowired
    private AddressManagerImpl addressManagerImpl;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);


    @Override
    @Before
    public void setUp() {
        super.setUp();

        userDtoTest = new UserDto();
        addressDtoTest = new Address();
        userDtoTest.setName("nico");
        userDtoTest.setLastName("bodforTest");
        userDtoTest.setEmail("test@test.com");
        userDtoTest.setPhone("060606006");
        userDtoTest.setPassword("pass");
        userDtoTest.setVoluntary(false);
        userDtoTest.setResponsible(false);
        addressDtoTest.setStreet("rue du test");
        addressDtoTest.setPostalCode(31200);
        addressDtoTest.setCity("Toulouse");
        userDtoTest.setAddress(addressDtoTest);
        userDtoTest.setToken("tok");

        // check if old test is on bdd
        List<User> listUserOnBddBeforeTest = userManagerImpl.getAllUsers();
        if (listUserOnBddBeforeTest != null) {
            User userResult = listUserOnBddBeforeTest.stream()
                    .filter(x -> "bodforTest".equals(x.getEmail()))
                    .findAny()
                    .orElse(null);
            logger.info(" old user test is present: " + userResult);

            if (userResult != null) {
                // deleting adress -> cascade for delete sale
                oldUserTest = new User();

                oldUserTest = userManagerImpl.findUserByMail("test@test.com");
                oldAddressTest = addressManagerImpl.getAddressById(oldUserTest.getAddress().getId());
                oldAddressTest.ifPresent(address -> addressManagerImpl.removeAddress(address));
                logger.info(" old user test removed ");
            }
        }
    }

    @After
    public void cleanAfter() {
        // check if old test is on bdd
        List<User> listUserOnBddBeforeTest = userManagerImpl.getAllUsers();
        if (listUserOnBddBeforeTest != null) {
            User userResult = listUserOnBddBeforeTest.stream()
                    .filter(x -> "test@test.com".equals(x.getEmail()))
                    .findAny()
                    .orElse(null);
            logger.info(" old user test is present: " + userResult);

            if (userResult != null) {
                // deleting adress -> cascade for delete sale
                oldUserTest = new User();

                oldUserTest = userManagerImpl.findUserByMail("test@test.com");
                oldAddressTest = addressManagerImpl.getAddressById(oldUserTest.getAddress().getId());
                oldAddressTest.ifPresent(address -> addressManagerImpl.removeAddress(address));
                logger.info(" old user test removed ");
            }
        }
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
        userManagerImpl.addUser(userDtoTest);
        String uri = "/newUser";
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);
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
        userManagerImpl.addUser(userDtoTest);
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

    @Test
    public void testAllUsersEmails() throws Exception {
        String uri = "/allUserEmails";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        String[] emailList = super.mapFromJson(content, String[].class);
        assertTrue(emailList.length > 0);
    }

    @Test
    public void testUpdateUserAndAddressSamePassword() throws  Exception {
        userManagerImpl.addUser(userDtoTest);
        // get user id and address id
        User userForTest = userManagerImpl.findUserByMail("test@test.com");
        userDtoTest.setId(userForTest.getId());
        addressDtoTest.setId(userForTest.getAddress().getId());
        // test for update
        String uri = "/updateUserAndAddressSamePassword";
        String inputJson = super.mapToJson(userDtoTest);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testUpdateUserAndAddressAndPassword() throws Exception {
        userManagerImpl.addUser(userDtoTest);
        // get user id and address id
        User userForTest = userManagerImpl.findUserByMail("test@test.com");
        userDtoTest.setId(userForTest.getId());
        addressDtoTest.setId(userForTest.getAddress().getId());
        // test for update
        String uri = "/updateUserAndAddressAndPassword";
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testGetOneUser() throws Exception {
        userManagerImpl.addUser(userDtoTest);
        // get user id and address id
        User userForTest = userManagerImpl.findUserByMail("test@test.com");
        userDtoTest.setId(userForTest.getId());
        addressDtoTest.setId(userForTest.getAddress().getId());
        // test
        String uri = "/getOneUser";
        String inputJson = super.mapToJson(userDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
