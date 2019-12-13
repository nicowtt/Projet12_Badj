package com.eSales.microserviceBusiness.IntegrationTests;

import com.eSales.microserviceBusiness.config.TestContextConf;
import com.eSales.microserviceBusiness.impl.UserManagerImpl;
import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestContextConf.class)
public class UserManagerIntegrationTest {

    @Autowired
    private UserManagerImpl userManagerImpl;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private UserDao userDao;

    /** Jeu de données **/
    private User userTest;
    private Address addressTest;

    @Before
    public void setup() {
//        userManagerImpl = new UserManagerImpl();

        userTest = new User();
        userTest.setName("nico");
        userTest.setLastName("bod");
        userTest.setPassword("pass");
        userTest.setEmail("test@test.com");
        userTest.setPhone("0612121212");
        userTest.setVoluntary(false);
        userTest.setResponsible(false);

        addressTest = new Address();
        addressTest.setStreet("rue du test");
        addressTest.setPostalCode(31200);
        addressTest.setCity("Toulouse");

        userTest.setAddress(addressTest);
        // check if old test is on bdd


    }

    @Test
    public void testAddUser() {
//
//        List<User> allUserOnBdd = userManagerImpl.getAllUsers();
//        System.out.println(allUserOnBdd);
        int countUserOnBdd = 0;
        List<User> listUserOnBdd = userManagerImpl.getAllUsers();
        for (int i = 0; i < listUserOnBdd.size(); i++) {
            countUserOnBdd++;
        }
        System.out.println(countUserOnBdd);
        // todo ajoute un user
        // todo test si on est a +1 de userOnBdd
        // todo efface ce user de test
    }

    @Test
    public void applicationContextLoads(){

    }

}
