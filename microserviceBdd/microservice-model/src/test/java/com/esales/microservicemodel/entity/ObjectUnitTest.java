package com.esales.microservicemodel.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObjectUnitTest {

    @Test
    public void testToString() {
        Object object= new Object();
        object.setBrand("Seiko");
        object.setColor("noir");
        object.setComment(null);

        Assert.assertEquals("Object(articleId=0, brand=Seiko, color=noir, comment=null, article=null)", object.toString());

    }
}
