package com.eSales.microserviceBusiness.IntegrationTests;

import com.eSales.microserviceBusiness.config.TestContextConf;
import com.eSales.microserviceBusiness.contract.AddressManager;
import com.eSales.microserviceBusiness.contract.UserManager;
import com.eSales.microserviceBusiness.impl.UserManagerImpl;
import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestContextConf.class)
public class UserManagerIntegrationTest {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AddressManager addressManager;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);

    /** Jeu de données **/

    private UserDto userDtoTest;
    private User userTest;

    @Before
    public void setup() {
        User oldUserTest;
        Optional<Address> oldAddressTest;

        userDtoTest = new UserDto();
        userDtoTest.setName("nico");
        userDtoTest.setLastName("bod");
        userDtoTest.setPassword("pass");
        userDtoTest.setEmail("test@test.com");
        userDtoTest.setPhone("0612121212");
        userDtoTest.setStreet("rue du test");
        userDtoTest.setPostalCode(31200);
        userDtoTest.setCity("Toulouse");

        userTest = new User();
        userTest.setPassword("pass");
        userTest.setEmail("test@test.com");

        // check if old test is on bdd
        List<User> listUserOnBddBeforeTest = userManager.getAllUsers();
        if (listUserOnBddBeforeTest != null) {
            User result = listUserOnBddBeforeTest.stream()
                    .filter(x -> "test@test.com".equals(x.getEmail()))
                    .findAny()
                    .orElse(null);
            logger.info(" old user test is present: " + result);

            if (result != null) {
                // deleting address ( + user with CASCADE )
                oldUserTest = userManager.findUserByMail(userDtoTest.getEmail());
                oldAddressTest = addressManager.getAddressById(oldUserTest.getAddress().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old user test removed ");
            }
        }
    }

    @After
    public void after() {
        User oldUserTest;
        Optional<Address> oldAddressTest;
        // check if old test is on bdd
        List<User> listUserOnBddBeforeTest = userManager.getAllUsers();
        User result = listUserOnBddBeforeTest.stream()
                .filter(x -> "test@test.com".equals(x.getEmail()))
                .findAny()
                .orElse(null);
        logger.info(" old user test is present: " + result);

        if (result != null) {
            // deleting address ( + user with CASCADE )
            oldUserTest = userManager.findUserByMail(userDtoTest.getEmail());
            oldAddressTest = addressManager.getAddressById(oldUserTest.getAddress().getId());
            oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
            logger.info(" old user test removed ");
        }
    }

    @Test
    public void testAddUser() {
        User userTest;
        Optional<Address> addressTest;

        List<User> listUserOnBddBeforeTest = userManager.getAllUsers();
        long countNbrOfUserBeforeTest = listUserOnBddBeforeTest.stream().count();
        userManager.addUser(userDtoTest);
        List<User> listUserOnBddAfterTest = userManager.getAllUsers();
        long countNbrOfUserAfterTest = listUserOnBddAfterTest.stream().count();

        Assert.assertTrue("Add user must return number of user + 1",
                countNbrOfUserAfterTest == countNbrOfUserBeforeTest + 1);

        // remove test -> address ( + user with CASCADE )
        userTest = userManager.findUserByMail(userDtoTest.getEmail());
        addressTest = addressManager.getAddressById(userTest.getAddress().getId());
        addressTest.ifPresent(address -> addressManager.removeAddress(address));
    }

    @Test(expected = UnexpectedRollbackException.class)
    public void testAddUserIfEmailAlreadyPresent() {
        userManager.addUser(userDtoTest);
        userManager.addUser(userDtoTest);
    }

    @Test
    public void testCheckIfMailExist() {
        userManager.addUser(userDtoTest);
        boolean emailExist = userManager.checkIfMailExist(userDtoTest.getEmail());
        Assert.assertTrue(emailExist);
    }

    @Test
    public void testCheckIfMailExistWrongEmail() {
        userDtoTest.setEmail("autre@test.com");
        boolean emailDontExist = userManager.checkIfMailExist(userDtoTest.getEmail());
        Assert.assertFalse(emailDontExist);
    }

    @Test
    public void testCheckIfUserMailAndPasswordIsOk() {
        userManager.addUser(userDtoTest);
        boolean userExist = userManager.checkIfUserMailAndPasswordIsOk(userTest);
        Assert.assertTrue(userExist);

        // password not valid
        userTest.setPassword("autre");
        boolean userExitButPassNotValid = userManager.checkIfUserMailAndPasswordIsOk(userTest);
        Assert.assertFalse(userExitButPassNotValid);

        // mail not exist
        userTest.setEmail("autre@test.com");
        boolean userDontExist = userManager.checkIfUserMailAndPasswordIsOk(userTest);
        Assert.assertFalse(userDontExist);

    }

}
