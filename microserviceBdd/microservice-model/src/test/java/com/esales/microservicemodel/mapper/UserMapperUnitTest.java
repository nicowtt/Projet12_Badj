package com.esales.microservicemodel.mapper;

import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.impl.UserMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserMapperUnitTest {

    private UserMapperImpl userMapper;

    /** Jeu de données **/
    private UserDto userDto;
    private User user;
    private Address address;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this.getClass());
        userMapper = new UserMapperImpl();
        userDto = new UserDto();
        user = new User();
        address = new Address();

        address.setStreet("rue du test");
        address.setPostalCode(31200);
        address.setCity("Toulouse");

        userDto.setName("nico");
        userDto.setLastName("bod");
        userDto.setEmail("test@test.com");
        userDto.setPhone("06121212");
        userDto.setPassword("pass");
        userDto.setAddress(address);
        userDto.setToken("123");

        user.setName("Jean-claude");
        user.setLastName("Vandamme");
        user.setEmail("jean-claude.vandamme@gmail.com");
        user.setPassword("secret");
        user.setVoluntary(false);
        user.setResponsible(false);



        user.setAddress(address);

    }


    @Test
    public void testFromDtoToUserWithoutAddress() {

        User userTest = userMapper.fromDtoToUserWithoutAddress(userDto);

        Assert.assertEquals("User(id=0, name=nico, lastName=bod, password=pass, email=test@test.com, " +
                "phone=06121212, isVoluntary=false, isResponsible=false, address=null)", userTest.toString());
    }

    @Test
    public void testFromUserToDto() {

        UserDto userDtoTest = userMapper.fromUserToDto(user);

        Assert.assertEquals("UserDto(id=0, name=Jean-claude, lastName=Vandamme, password=secret, " +
                "email=jean-claude.vandamme@gmail.com, phone=null, isVoluntary=false, isResponsible=false, " +
                "token=null, address=Address(id=0, street=rue du test, postalCode=31200, city=Toulouse))", userDtoTest.toString());
    }

    @Test
    public void testFromUserDtoToAddress() {
        Address addressTest = userMapper.fromUserDtoToAddress(userDto);

        Assert.assertEquals("Address(id=0, street=rue du test, postalCode=31200, city=Toulouse)"
                , addressTest.toString());
    }


}
