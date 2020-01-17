package com.esales.microservicemodel.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ArticleUnitTest {



    @Test
    public void testToString() {
        Article article = new Article();

        article.setId(0);
        article.setCategory("vêtement");
        article.setType("jeans");
        article.setSaleNumber(1);
        article.setPrice(10);
        article.setDateRecord(null);
        article.setValidateToSell(false);
        article.setStolen(false);
        article.setReturnOwner(false);

        System.out.println(article.toString());
        Assert.assertEquals("Article(id=0, category=vêtement, type=jeans, saleNumber=1, price=10.0, " +
                        "dateRecord=null, isValidateToSell=false, isSold=false, isStolen=false, isReturnOwner=false, " +
                        "user=null, sale=null, clothe=null, toy=null, book=null, object=null)"
                , article.toString());
    }
}
