package com.esales.microservicemodel.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToyUnitTest {

    @Test
    public void testToString() {
        Toy toy = new Toy();
        toy.setBrand("sony");
        toy.setBrand("rouge");
        toy.setComment(null);

        Assert.assertEquals("Toy(articleId=0, brand=rouge, color=null, comment=null)"
                , toy.toString());

    }
}

