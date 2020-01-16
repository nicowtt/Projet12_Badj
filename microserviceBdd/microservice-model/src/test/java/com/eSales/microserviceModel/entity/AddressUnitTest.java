package com.eSales.microserviceModel.entity;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressUnitTest {

    @Test
    public void testToString() {
        Address address = new Address();
        address.setId(0);
        address.setStreet("2 rue du test");
        address.setPostalCode(31000);
        address.setCity("Toulouse");

        Assert.assertEquals("Address(id=0, street=2 rue du test, postalCode=31000, city=Toulouse)",
                address.toString());
    }

}
