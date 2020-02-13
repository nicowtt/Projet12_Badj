package com.esales.microservicemodel.dto;

import com.esales.microservicemodel.dto.security.JwtResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class jwtResponseUnitTest {

    @Test
    public void testToString() {
        JwtResponse jwtResponse = new JwtResponse("123");

        Assert.assertEquals("123", jwtResponse.getToken());
    }
}
