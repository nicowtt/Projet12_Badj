package com.esales.microservicemodel.dto;

import com.esales.microservicemodel.entity.Address;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDtoUnitTest {

    @Test
    public void testUserDtoToString() {
        UserDto userDto = new UserDto();
        Address address = new Address();
        userDto.setName("nico");
        userDto.setLastName("bod");
        userDto.setPassword("test");
        userDto.setEmail("test@test.com");
        userDto.setPhone("0612121212");
        userDto.setResponsible(false);
        userDto.setVoluntary(false);
        address.setStreet("rue du test");
        address.setPostalCode(31000);
        address.setCity("Toulouse");
        userDto.setAddress(address);
        userDto.setToken(null);

        Assert.assertEquals("UserDto(id=null, name=nico, lastName=bod, password=test, email=test@test.com, " +
                        "phone=0612121212, isVoluntary=false, isResponsible=false, token=null, address=Address(id=0," +
                        " street=rue du test, postalCode=31000, city=Toulouse))"
                , userDto.toString());
    }
}
