package com.eSales.microserviceModel.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserUnitTest {

    @Test
    public void testToString() {
        User user = new User();
        user.setName("nico");
        user.setLastName("bod");
        user.setPassword("test");
        user.setEmail("nico.bod@gmail.com");
        user.setPhone("0612452154");
        user.setVoluntary(false);
        user.setResponsible(false);

        Assert.assertEquals("User{id=0, name='nico', lastName='bod', password='test', email='nico.bod@gmail.com', " +
                "phone='0612452154', isVoluntary=false, isResponsible=false, address=null}"
                , user.toString());
    }
}
