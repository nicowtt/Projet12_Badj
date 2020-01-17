package com.esales.microservicemodel.dto;

import com.esales.microservicemodel.dto.security.JwtRequest;
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
