package com.eSales.microserviceBusiness.UnitTests;

import com.eSales.microserviceBusiness.SecurityToken.JwtUserDetailsService;
import com.eSales.microserviceDao.UserDao;
import com.eSales.microserviceModel.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JwtUserDetailsServiceUnitTest {

    @InjectMocks
    JwtUserDetailsService jwtUserDetailsService;

    @Mock
    UserDao userDaoMock;

    /** Jeu de données **/
    private User userTest;

    @Test
    public void testLoadUserByUsername() {
        UserDetails userDetailsForTest;
        userTest = new User();
        userTest.setName("nico");
        userTest.setLastName("bod");
        userTest.setPassword("pass");
        userTest.setEmail("test@test.com");
        userTest.setPhone("0612121212");
        userTest.setVoluntary(false);
        userTest.setResponsible(false);

        when(userDaoMock.findByEmail(any())).thenReturn(userTest);
        userDetailsForTest = jwtUserDetailsService.loadUserByUsername(userTest.getEmail());
        Assert.assertTrue(userDetailsForTest.getUsername().equals(userTest.getEmail()));
    }
}
