package com.esales.microservicebusiness.Unittests;
import com.esales.microservicebusiness.contract.PasswordEncoder;
import com.esales.microservicebusiness.impl.UserManagerImpl;
import com.esales.microservicedao.AddressDao;
import com.esales.microservicedao.UserDao;
import com.esales.microservicemodel.dto.UserDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplUnitTest {

    @Mock
    private UserDao userDaoMock;

    @Mock
    private AddressDao addressDaoMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private UserMapper userMapperMock;

    @InjectMocks
    private UserManagerImpl userManagerImpl;


    /** Jeu de données **/
    private User userTest;
    private Address addressTest;
    private UserDto userDto;

    @Before
    public void setUp() {
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

        userDto = new UserDto();
        userDto.setStreet("rue du test dto");
        userDto.setPostalCode(34500);
        userDto.setCity("Beziers");


    }

    @Test
    public void testCheckIfMailExist() {
        when(userDaoMock.findByEmail(userTest.getEmail())).thenReturn(userTest);
        boolean resultWithMailOk = userManagerImpl.checkIfMailExist(userTest.getEmail());
        Assert.assertTrue("boolean feedback must be true!", resultWithMailOk);

        boolean resultWithMailNok = userManagerImpl.checkIfMailExist("otherMail@gmail.com");
        Assert.assertFalse("boolean feedback must be false!", resultWithMailNok);
    }


    @Test
    public void testCheckIfUserMailAndPasswordIsOk() {
        when(userDaoMock.findByEmail(userTest.getEmail())).thenReturn(userTest);
        when(passwordEncoderMock.checkPassword(any(),any())).thenReturn(true);
        boolean resultWithPasswordOk = userManagerImpl.checkIfUserMailAndPasswordIsOk(userTest);
        Assert.assertTrue("boolean feedback must be true!", resultWithPasswordOk);

        when(passwordEncoderMock.checkPassword(any(),any())).thenReturn(false);
        boolean resultWithPAsswordNok = userManagerImpl.checkIfUserMailAndPasswordIsOk(userTest);
        Assert.assertFalse("boolean feedback must be false!", resultWithPAsswordNok);
    }

    @Test
    public void testAddUser() {
        when(userMapperMock.fromUserDtoToAddress(any())).thenReturn(addressTest);
        when(addressDaoMock.save(any())).thenReturn(addressTest);
        when(userMapperMock.fromDtoToUserWithoutAddress(any())).thenReturn(userTest);
        when(passwordEncoderMock.hashPassword(any())).thenReturn("passHashed");
        boolean resultWithUserCreated = userManagerImpl.addUser(userDto);
        Assert.assertTrue("boolean feedback must be true!", resultWithUserCreated);

        Assert.assertTrue("user password is not hashed", userTest.getPassword().equals("passHashed"));
    }

    @Test
    public void testAddUserWithSameEmailOnBdd() {
        when(userMapperMock.fromUserDtoToAddress(any())).thenReturn(addressTest);
        when(addressDaoMock.save(any())).thenReturn(addressTest);
        when(userMapperMock.fromDtoToUserWithoutAddress(any())).thenReturn(userTest);
        when(passwordEncoderMock.hashPassword(any())).thenReturn("passHashed");
        when(userDaoMock.save(any())).thenThrow(DataIntegrityViolationException.class);
        boolean resultWhenSameEmailExistOnBdd = userManagerImpl.addUser(userDto);
        Assert.assertFalse(resultWhenSameEmailExistOnBdd);
    }


}
