package com.eSales.microserviceModel.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDtoUnitTest {

    @Test
    public void testToString() {
        UserDto userDto = new UserDto();
        userDto.setName("nico");
        userDto.setLastName("bod");
        userDto.setPassword("test");
        userDto.setEmail("test@test.com");
        userDto.setPhone("0612121212");
        userDto.setStreet("rue du test");
        userDto.setPostalCode(31000);
        userDto.setCity("Toulouse");
        userDto.setToken(null);

        Assert.assertEquals("UserDto{name='nico', lastName='bod', password='test', email='test@test.com', " +
                "phone='0612121212', street='rue du test', postalCode=31000, city='Toulouse', token='null'}"
                , userDto.toString());
    }
}