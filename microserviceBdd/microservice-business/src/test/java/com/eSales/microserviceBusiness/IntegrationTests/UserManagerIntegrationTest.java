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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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

}
