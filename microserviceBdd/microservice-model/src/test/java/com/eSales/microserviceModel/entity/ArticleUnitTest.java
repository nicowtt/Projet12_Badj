package com.eSales.microserviceModel.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class ArticleUnitTest {

    static private final Log logger = LogFactory.getLog(ArticleUnitTest.class);

    @Test
    public void testToString() {
        Article article = new Article();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String todayDateString = "2019-12-09 17:31:25";

        article.setId(0);
        article.setCategory("vêtement");
        article.setType("jeans");
        article.setSaleNumber(1);
        article.setPrice(10);
        try {
            Date todayDate = formatter.parse(todayDateString);
            article.setDateRecord(todayDate);
        } catch (ParseException e) {
            logger.info("fail to convert string on date");
        }
        article.setValidateToSell(false);
        article.setStolen(false);
        article.setReturnOwner(false);

        System.out.println(article.toString());
        Assert.assertEquals("Article{id=0, category='vêtement', type='jeans', saleNumber=1, price=10.0," +
                        " dateRecord=Wed Jan 09 17:31:25 CET 2019, isValidateToSell=false, isSold=false, isStolen=false," +
                        " isReturnOwner=false, user=null, sale=null, clothe=null, toy=null, book=null, object=null}"
                , article.toString());
    }
}
