package com.eSales.microserviceModel.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleDtoUnitTest {

    @Test
    public void testArticleDtoToString() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(10000);
        articleDto.setCategory("Vêtement");
        articleDto.setType("pantalon");
        articleDto.setSaleNumber(1);
        articleDto.setPrice(5);
        articleDto.setDateRecord(null);
        articleDto.setValidateToSell(false);
        articleDto.setSold(false);
        articleDto.setStolen(false);
        articleDto.setReturnOwner(false);
        articleDto.setUser(null);
        articleDto.setSale(null);
        articleDto.setClothe(null);
        articleDto.setToy(null);
        articleDto.setBook(null);
        articleDto.setObject(null);

        Assert.assertEquals("ArticleDto{id=10000, category='Vêtement', type='pantalon', saleNumber=1, " +
                "price=5, dateRecord=null, isValidateToSell=false, isSold=false, isStolen=false, isReturnOwner=false, " +
                "user=null, sale=null, clothe=null, toy=null, book=null, object=null}", articleDto.toString());

    }
}
