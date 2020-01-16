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
public class ArticleToyDtoUnitTest {

    @Test
    public void testArticleToyDtoToString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ArticleToyDto articleToyDto = new ArticleToyDto();
        Date recordDate = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormated.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleToyDto.setBrand("bandai");
        articleToyDto.setCategory("Jouet");
        articleToyDto.setType("jouet éléctronique");
        articleToyDto.setColor("bleu");
        articleToyDto.setComment("Rayure derrière");
        articleToyDto.setPrice(5);
        articleToyDto.setRecordDate(recordDate);
        articleToyDto.setReturnOwner(false);
        articleToyDto.setSaleId(1);
        articleToyDto.setSold(false);
        articleToyDto.setStolen(false);
        articleToyDto.setUserEmail("bruce.lee@gmail.com");
        articleToyDto.setValidateToSell(false);

        Assert.assertEquals("ArticleToyDto(category=Jouet, type=jouet éléctronique, saleId=1, " +
                "brand=bandai, color=bleu, price=5.0, comment=Rayure derrière, " +
                "recordDate=Fri Jan 10 00:00:00 UTC 2020, isValidateToSell=false, isSold=false, isStolen=false, " +
                "isReturnOwner=false, userEmail=bruce.lee@gmail.com)", articleToyDto.toString());
    }
}
