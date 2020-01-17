package com.esales.microservicemodel.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class ArticleBookDtoUnitTest {

    @Test
    public void testArticleBookDtoToString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ArticleBookDto articleBookDto = new ArticleBookDto();
        Date recordDate = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormated.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleBookDto.setCategory("Livre");
        articleBookDto.setType("poche");
        articleBookDto.setSaleId(1);
        articleBookDto.setName("Le signal");
        articleBookDto.setAuthor("Maxime Chattam");
        articleBookDto.setPrice(5);
        articleBookDto.setComment("/");
        articleBookDto.setRecordDate(recordDate);
        articleBookDto.setValidateToSell(false);
        articleBookDto.setSold(false);
        articleBookDto.setStolen(false);
        articleBookDto.setReturnOwner(false);
        articleBookDto.setUserEmail("bruce.lee@gmail.com");

        Assert.assertEquals("ArticleBookDto(category=Livre, type=poche, saleId=1, name=Le signal, " +
                "author=Maxime Chattam, price=5.0, comment=/, recordDate=Fri Jan 10 00:00:00 UTC 2020, " +
                "isValidateToSell=false, isSold=false, isStolen=false, isReturnOwner=false, " +
                "userEmail=bruce.lee@gmail.com)", articleBookDto.toString());
    }
}
