package com.esales.microservicemodel.entity;

import org.junit.Assert;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class bookUnitTest {

    @Test
    public void testToString() {
        Book book = new Book();
        book.setName("Le signal");
        book.setAuthor("Maxime Chattam");
        book.setComment("erraflures sur tranche");

        Assert.assertEquals("Book(articleId=0, name=Le signal, author=Maxime Chattam, " +
                "comment=erraflures sur tranche)", book.toString());
    }

}
