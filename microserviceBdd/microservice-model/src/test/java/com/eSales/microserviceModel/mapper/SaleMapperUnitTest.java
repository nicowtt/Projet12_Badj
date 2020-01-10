package com.eSales.microserviceModel.mapper;

import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.mapper.impl.SaleMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class SaleMapperUnitTest {

    private SaleMapperImpl saleMapperImpl;

    /** Jeu de données **/
    private SaleDto saleDto;
    private Sale sale;
    private Address address;


    @Before
    public void setUp() {
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
    public void testfromSaleToSaleDto() {
        SaleDto saleDtoTest = saleMapperImpl.fromSaleToSaleDto(sale);

        Assert.assertEquals("SaleDto{id=0, type='Bourse de printemps', description='Vêtements', dateBegin=null, " +
                "dateEnd=null, address=null, nbrArticlesPreRecordForUser=null}", saleDtoTest.toString());
    }
}
