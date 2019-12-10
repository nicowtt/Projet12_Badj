package com.eSales.microserviceModel.mapper;

import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.impl.UserMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

        userDto.setName("nico");
        userDto.setLastName("bod");
        userDto.setEmail("test@test.com");
        userDto.setPhone("06121212");
        userDto.setPassword("pass");
        userDto.setStreet("une rue");
        userDto.setPostalCode(31200);
        userDto.setCity("Toulouse");
        userDto.setToken("123");

        user.setName("Jean-claude");
        user.setLastName("Vandamme");
        user.setEmail("jean-claude.vandamme@gmail.com");
        user.setPassword("secret");
        user.setVoluntary(false);
        user.setResponsible(false);

        address.setStreet("rue du test");
        address.setPostalCode(31200);
        address.setCity("Toulouse");

        user.setAddress(address);

    }


    @Test
    public void testfromDtoToUserWithoutAddress() {

        User userTest = userMapper.fromDtoToUserWithoutAddress(userDto);

        Assert.assertEquals("User{id=0, name='nico', lastName='bod', password='pass', email='test@test.com', " +
                "phone='06121212', isVoluntary=false, isResponsible=false, address=null}", userTest.toString());
    }

    @Test
    public void testfromUserToDto() {

        UserDto userDtoTest = userMapper.fromUserToDto(user);

        Assert.assertEquals("UserDto{name='Jean-claude', lastName='Vandamme', password='secret', " +
                "email='jean-claude.vandamme@gmail.com', phone='null', street='rue du test', postalCode=31200," +
                " city='Toulouse', token='null'}", userDtoTest.toString());
    }

    @Test
    public void testfromUserDtoToAddress() {
        Address addressTest = userMapper.fromUserDtoToAddress(userDto);

        Assert.assertEquals("Address{id=0, street='une rue', postalCode=31200, city='Toulouse', user=null}"
                , addressTest.toString());
    }


}
