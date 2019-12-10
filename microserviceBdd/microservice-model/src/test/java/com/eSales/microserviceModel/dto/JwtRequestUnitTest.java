package com.eSales.microserviceModel.dto;

import com.eSales.microserviceModel.dto.security.JwtRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtRequestUnitTest {

    @Test
    public void testToString() {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("test");
        jwtRequest.setPassword("pass");

        Assert.assertEquals("JwtRequest{username='test', password='pass'}", jwtRequest.toString());
    }
}
