package com.eSales.microserviceModel.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class ArticleClotheDtoUnitTest {

    @Test
    public void testArticleClotheDtoToString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ArticleClotheDto articleClotheDto = new ArticleClotheDto();
        Date recordDate = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormated.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleClotheDto.setCategory("Vêtements");
        articleClotheDto.setType("pantalon");
        articleClotheDto.setSaleId(1);
        articleClotheDto.setPrice(5);
        articleClotheDto.setSize("32");
        articleClotheDto.setGender("Homme");
        articleClotheDto.setMaterial("jeans");
        articleClotheDto.setColor("blue");
        articleClotheDto.setComment("marque levis");
        articleClotheDto.setRecordDate(recordDate);
        articleClotheDto.setValidateToSell(false);
        articleClotheDto.setSold(false);
        articleClotheDto.setStolen(false);
        articleClotheDto.setReturnOwner(false);
        articleClotheDto.setUserEmail("bruce.lee@gmail.com");

        Assert.assertEquals("ArticleClotheDto{category='Vêtements', type='pantalon', saleId=1, price=5.0, " +
                "size='32', gender='Homme', material='jeans', color='blue', comment='marque levis'," +
                " recordDate=Fri Jan 10 00:00:00 UTC 2020, isValidateToSell=false, isSold=false, isStolen=false, " +
                "isReturnOwner=false, userEmail='bruce.lee@gmail.com'}", articleClotheDto.toString());
    }
}

