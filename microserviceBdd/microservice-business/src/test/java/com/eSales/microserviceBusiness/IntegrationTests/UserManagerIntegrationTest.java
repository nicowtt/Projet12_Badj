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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestContextConf.class)
public class UserManagerIntegrationTest {

//    private UserManagerImpl userManagerImpl;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private SaleDao saleDao;
//
//    /** Jeu de données **/
//    private User userTest;
//    private Address addressTest;
//
//    @Before
//    public void setup() {
//        userManagerImpl = new UserManagerImpl();
//
//        userTest = new User();
//        userTest.setName("nico");
//        userTest.setLastName("bod");
//        userTest.setPassword("pass");
//        userTest.setEmail("test@test.com");
//        userTest.setPhone("0612121212");
//        userTest.setVoluntary(false);
//        userTest.setResponsible(false);
//
//        addressTest = new Address();
//        addressTest.setStreet("rue du test");
//        addressTest.setPostalCode(31200);
//        addressTest.setCity("Toulouse");
//
//        userTest.setAddress(addressTest);
//        // check if old test is on bdd
//
//
//    }
//
//    @Test
//    public void testAddUser() {
////        List<User> listUserOnBdd = userDao.findAll();
////        System.out.println(listUserOnBdd);
//
//        List<Sale> listAllSales = saleDao.findAll();
//        System.out.println(listAllSales);
//    }
//
//    @Test
//    public void applicationContextLoads(){
//
//    }

}
