package com.eSales.microserviceModel.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class SaleUnitTest {

    static private final Log logger = LogFactory.getLog(SaleUnitTest.class);

    @Test
    public void testToString() {
        Sale sale = new Sale();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String todayDateString = "2019-12-10 14:48:25";
        String oneWeekDateString = "2019-12-17 14:48:25";

        sale.setType("Bourse de printemps");
        sale.setDescription("Vêtements");

        try {
            Date todayDate = formatter.parse(todayDateString);
            Date oneWeekDate = formatter.parse(oneWeekDateString);
            sale.setDateBegin(todayDate);
            sale.setDateEnd(oneWeekDate);
        } catch (ParseException e) {
            logger.info("fail to convert string on date");
        }

        Assert.assertEquals("Sale{id=0, type='Bourse de printemps', description='Vêtements', " +
                "dateBegin=Thu Jan 10 14:48:25 CET 2019, dateEnd=Thu Jan 17 14:48:25 CET 2019, address=null}"
                , sale.toString());

    }
}
