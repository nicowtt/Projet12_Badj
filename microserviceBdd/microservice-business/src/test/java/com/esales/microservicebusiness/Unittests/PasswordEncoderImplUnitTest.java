package com.esales.microservicebusiness.Unittests;

import com.esales.microservicebusiness.impl.PasswordEncoderImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PasswordEncoderImplUnitTest {

    @InjectMocks
    PasswordEncoderImpl passwordEncoderImpl;

    @Test
    public void testHashPassword() {
        String password = "mdp";
        String hashingPassword = passwordEncoderImpl.hashPassword(password);
        boolean passwordToTest = passwordEncoderImpl.checkPassword(password, hashingPassword);
        Assert.assertTrue(passwordToTest);
    }

    @Test
    public void testCheckPassword() {
        boolean testPassword = passwordEncoderImpl.checkPassword("mdp",
                "$2a$10$ZrNev/FCEyfKp3.Zc/irx.OrtFuqL7X6t.tJytIOiYLQ458k2jasO");
        Assert.assertTrue(testPassword);
    }
}
