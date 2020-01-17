package com.esales.microservicemodel.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class SaleUnitTest {

    @Test
    public void testToString() {
        Sale sale = new Sale();

        sale.setType("Bourse de printemps");
        sale.setDescription("Vêtements");
        sale.setDateEnd(null);
        sale.setDateBegin(null);

        Assert.assertEquals("Sale(id=0, type=Bourse de printemps, description=Vêtements, dateBegin=null, " +
                        "dateEnd=null, address=null)"
                , sale.toString());

    }
}
