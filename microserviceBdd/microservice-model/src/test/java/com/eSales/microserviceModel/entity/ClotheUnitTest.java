package com.eSales.microserviceModel.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClotheUnitTest {

    @Test
    public void testToString() {
        Clothe clothe = new Clothe();
        clothe.setSize("32");
        clothe.setGender("homme");
        clothe.setMaterial("jean");
        clothe.setColor("blue");
        clothe.setComment(null);

        Assert.assertEquals("Clothe{articleId=0, size='32', gender='homme', material='jean', color='blue'," +
                " comment='null', article=null}", clothe.toString());
    }
}
