package com.eSales.microserviceModel.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class ArticleObjectDtoUnitTest {

    @Test
    public void testArticleObjectDtoToString() {
        ArticleObjectDto articleObjectDto = new ArticleObjectDto();
        Date recordDate = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormated.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleObjectDto.setCategory("Objet de décoration");
        articleObjectDto.setType("lit");
        articleObjectDto.setSaleId(1);
        articleObjectDto.setPrice(5);
        articleObjectDto.setBrand("laCroix");
        articleObjectDto.setColor("blanc");
        articleObjectDto.setColor("/");
        articleObjectDto.setRecordDate(recordDate);
        articleObjectDto.setValidateToSell(false);
        articleObjectDto.setSold(false);
        articleObjectDto.setStolen(false);
        articleObjectDto.setReturnOwner(false);
        articleObjectDto.setUserEmail("bruce.lee@gmail.com");

        Assert.assertEquals("ArticleObjectDto{category='Objet de décoration', type='lit', " +
                "saleId=1, price=5.0, brand='laCroix', color='/', comment='null', " +
                "recordDate=Fri Jan 10 00:00:00 CET 2020, isValidateToSell=false, isSold=false, isStolen=false, " +
                "isReturnOwner=false, userEmail='bruce.lee@gmail.com'}", articleObjectDto.toString());
    }
}
