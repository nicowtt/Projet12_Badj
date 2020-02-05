package com.esales.microservicebusiness.Integrationtests;

import com.esales.microservicebusiness.config.TestContextConf;
import com.esales.microservicebusiness.contract.AddressManager;
import com.esales.microservicebusiness.contract.UserManager;
import com.esales.microservicebusiness.impl.UserManagerImpl;
import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
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
    private Address addressDtoTest;
    private User userTest;

    @Before
    public void setup() {
        User oldUserTest;
        Optional<Address> oldAddressTest;

        userDtoTest = new UserDto();
        addressDtoTest = new Address();
        userDtoTest.setName("nico");
        userDtoTest.setLastName("bod");
        userDtoTest.setPassword("pass");
        userDtoTest.setEmail("test@test.com");
        userDtoTest.setPhone("0612121212");
        userDtoTest.setVoluntary(false);
        addressDtoTest.setStreet("rue du test");
        addressDtoTest.setPostalCode(31200);
        addressDtoTest.setCity("Toulouse");
        userDtoTest.setAddress(addressDtoTest);

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

    @Test
    public void testUpdateUser() {
        userManager.addUser(userDtoTest);
        userDtoTest.setVoluntary(true);
        // get user
        List<User> listOfAllUser = userManager.getAllUsers();
        User userConcerned = listOfAllUser.stream()
                .filter(user -> "test@test.com".equals(user.getEmail()))
                .findAny()
                .orElse(null);
        userDtoTest.setId(userConcerned.getId());
        userDtoTest.getAddress().setId(userConcerned.getAddress().getId());
        userManager.updateUser(userDtoTest);

        List<User> listOfAllUserFinal = userManager.getAllUsers();
        User userUpdated = listOfAllUserFinal.stream()
                .filter(user -> "test@test.com".equals(user.getEmail()))
                .findAny()
                .orElse(null);

        Assert.assertTrue(userUpdated.isVoluntary());
    }
}
