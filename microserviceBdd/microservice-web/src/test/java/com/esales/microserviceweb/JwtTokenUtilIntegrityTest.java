package com.esales.microserviceweb;

import com.esales.microservicebusiness.impl.AddressManagerImpl;
import com.esales.microservicebusiness.impl.UserManagerImpl;
import com.esales.microservicebusiness.securitytoken.JwtUserDetailsService;
import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import com.esales.microserviceweb.security.JwtTokenUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class JwtTokenUtilIntegrityTest extends AbstractTest {

    /** Jeu de données **/
    private UserDto userDtoTest;
    private Address addressDtoTest;
    private User oldUserTest;
    private Optional<Address> oldAddressTest;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

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
    public void testgenerateToken() throws Exception {
        userManagerImpl.addUser(userDtoTest);
        // token creation
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername("test@test.com");
        final String token = jwtTokenUtil.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void testGetUserNameFromToken() throws Exception {
        userManagerImpl.addUser(userDtoTest);
        String email = "test@test.com";
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);
        String token = jwtTokenUtil.generateToken(userDetails);
        String result = jwtTokenUtil.getUsernameFromToken(token);
        Assert.assertTrue(result.equals(email));
    }

    @Test
    public void testgetExpirationTimeFromToken() {
        userManagerImpl.addUser(userDtoTest);
        String email = "test@test.com";
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);
        String token = jwtTokenUtil.generateToken(userDetails);

        Date tokenExpirationDate = jwtTokenUtil.getExpirationDateFromToken(token);
        assertNotNull(tokenExpirationDate);
    }

    @Test
    public void testValidateToken() {
        userManagerImpl.addUser(userDtoTest);
        String email = "test@test.com";
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);
        String token = jwtTokenUtil.generateToken(userDetails);

        boolean valid = jwtTokenUtil.validateToken(token, userDetails);
        Assert.assertTrue(valid);
    }
}
