package com.eSales.microserviceModel.dto;

import com.eSales.microserviceModel.entity.Address;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class SaleDtoUnitTest {

    @Test
    public void testSaleDtoToString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SaleDto saleDto = new SaleDto();
        Date beginDate = new Date();
        Date endDate = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String beginDateString = "09/03/2020";
        String endDateString = "13/03/2020";
        try {
            beginDate = dateFormated.parse(beginDateString);
            endDate = dateFormated.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Address address = new Address();
        address.setId(1);
        address.setStreet("2 rue de l'ouest");
        address.setPostalCode(31200);
        address.setCity("Toulouse");


        saleDto.setId(1);
        saleDto.setType("Bourse de printemps");
        saleDto.setDescription("Vêtement enfants");
        saleDto.setDateBegin(beginDate);
        saleDto.setDateEnd(endDate);
        saleDto.setNbrArticlesPreRecordForUser(3);
        saleDto.setAddress(address);

        Assert.assertEquals("SaleDto(id=1, type=Bourse de printemps, description=Vêtement enfants, " +
                "dateBegin=Mon Mar 09 00:00:00 UTC 2020, dateEnd=Fri Mar 13 00:00:00 UTC 2020, " +
                "address=Address(id=1, street=2 rue de l'ouest, postalCode=31200, city=Toulouse), " +
                "nbrArticlesPreRecordForUser=3)", saleDto.toString());

    }
}
