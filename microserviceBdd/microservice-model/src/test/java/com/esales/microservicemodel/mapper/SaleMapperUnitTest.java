package com.esales.microservicemodel.mapper;

import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.mapper.impl.SaleMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class SaleMapperUnitTest {

    private SaleMapperImpl saleMapperImpl;

    /** Jeu de données **/
    private SaleDto saleDto;
    private Sale sale;
    private Address address;


    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        saleMapperImpl = new SaleMapperImpl();
        saleDto = new SaleDto();
        sale = new Sale();

        Date beginDate = new Date();
        Date endDate = new Date();
        DateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
        String beginDateString = "09/03/2020";
        String endDateString = "13/03/2020";
        try {
            beginDate = dateFormatted.parse(beginDateString);
            endDate = dateFormatted.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        address = new Address();
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

        sale = new Sale();
        sale.setType("Bourse de printemps");
        sale.setDescription("Vêtements");
        sale.setDateEnd(null);
        sale.setDateBegin(null);
    }

    @Test
    public void testFromSaleToSaleDto() {
        SaleDto saleDtoTest = saleMapperImpl.fromSaleToSaleDto(sale);

        Assert.assertEquals("SaleDto(id=0, type=Bourse de printemps, description=Vêtements, " +
                "dateBegin=null, dateEnd=null, address=null, nbrArticlesPreRecordForUser=null)", saleDtoTest.toString());
    }

    @Test
    public void testFromSaleDtoToAddress() {
        Address address = saleMapperImpl.fromSaleDtoToAddress(saleDto);
        Assert.assertEquals("Address(id=0, street=2 rue de l'ouest, postalCode=31200, " +
                "city=Toulouse)", address.toString());
    }

    @Test
    public void testFromSaleDtoToSale() {
        Sale sale = saleMapperImpl.fromSaleDtoToSale(saleDto);
        Assert.assertEquals("Sale(id=0, type=Bourse de printemps, description=Vêtement enfants, " +
                "dateBegin=Mon Mar 09 00:00:00 UTC 2020, dateEnd=Fri Mar 13 00:00:00 UTC 2020, address=null)", sale.toString());
    }
}
