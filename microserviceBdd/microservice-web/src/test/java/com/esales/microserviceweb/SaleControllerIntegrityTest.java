package com.esales.microserviceweb;

import com.esales.microservicebusiness.contract.AddressManager;
import com.esales.microservicebusiness.impl.SaleManagerImpl;
import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.Sale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class SaleControllerIntegrityTest extends AbstractTest{

    private SaleDto saleDtoTest;
    private Address addressTest;
    private Sale oldSaleTest;
    private Optional<Address> oldAddressTest;
    private Address address;

    @Autowired
    private SaleManagerImpl saleManagerImpl;

    @Autowired
    private AddressManager addressManager;

    static final Log logger = LogFactory.getLog(SaleManagerImpl.class);


    @Override
    @Before
    public void setUp() {
        super.setUp();

        saleDtoTest = new SaleDto();
        saleDtoTest.setId(-1);
        Date testDateBegin = new Date();
        Date testDateEnd = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateBeginRecordString = "10/01/2100";
        String dateEndRecordString = "17/01/2100";
        try {
            testDateBegin = dateFormated.parse(dateBeginRecordString);
            testDateEnd = dateFormated.parse(dateEndRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        saleDtoTest.setDateBegin(testDateBegin);
        saleDtoTest.setDateEnd(testDateEnd);
        saleDtoTest.setDescription("saleTest");
        saleDtoTest.setType("saleTest");
        saleDtoTest.setNbrArticlesPreRecordForUser(1);
        addressTest = new Address();
        addressTest.setId(-1);
        addressTest.setStreet("ici");
        addressTest.setPostalCode(31);
        addressTest.setCity("Toulouse");
        saleDtoTest.setAddress(addressTest);

        // check if old test is on bdd
        List<Sale> listSaleOnBddBeforeTest = saleManagerImpl.getSalesByDateEndAfterToday();
        if (listSaleOnBddBeforeTest != null) {
            Sale saleResult = listSaleOnBddBeforeTest.stream()
                    .filter(x -> "saleTest".equals(x.getType()))
                    .findAny()
                    .orElse(null);
            logger.info(" old sale test is present: " + saleResult);

            if (saleResult != null) {
                // deleting adress -> cascade for delete sale
                oldSaleTest = new Sale();

                oldSaleTest = saleManagerImpl.getSale("2100-01-10");
                oldAddressTest = addressManager.getAddressById(oldSaleTest.getAddress().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old sale test removed ");
            }
        }
    }

    @After
    public void cleanAfter() {
        // check if old test is on bdd
        List<Sale> listSaleOnBddBeforeTest = saleManagerImpl.getSalesByDateEndAfterToday();
        if (listSaleOnBddBeforeTest != null) {
            Sale saleResult = listSaleOnBddBeforeTest.stream()
                    .filter(x -> "saleTest".equals(x.getType()))
                    .findAny()
                    .orElse(null);
            logger.info(" old sale test is present: " + saleResult);

            if (saleResult != null) {
                // deleting adress -> cascade for delete sale
                oldSaleTest = new Sale();
                oldSaleTest = saleManagerImpl.getSale("2100-01-10");
                oldAddressTest = addressManager.getAddressById(oldSaleTest.getAddress().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old sale test removed ");
            }
        }
    }


    @Test
    public void testgetAllSales() throws Exception{
        String uri = "/AllSales";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Sale[] salelist = super.mapFromJson(content, Sale[].class);
        assertTrue(salelist.length > 0);
    }

    @Test
    public void testgetAfterTodaySales() throws Exception{
        String uri = "/AfterTodaySales";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Sale[] salelist = super.mapFromJson(content, Sale[].class);
        assertTrue(salelist.length > 0);
    }

    @Test
    public void testgetAfterTodaySalesPersonalizedWithEmail() throws Exception{
        String uri = "/AfterTodaySales/jason.statam@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        SaleDto[] saleDtolist = super.mapFromJson(content, SaleDto[].class);
        assertTrue(saleDtolist.length > 0);
    }

    @Test
    public void testGetOneSale() throws Exception {
        String uri = "/OneSale/0";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testAddNewSale() throws Exception {
        String uri ="/NewSale";
        String inputJson = super.mapToJson(saleDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    public void testDeleteSale() throws Exception {
        // add sale
        saleManagerImpl.addSale(saleDtoTest);
        // test delete sale
        String uri ="/RemoveSale";
        String inputJson = super.mapToJson(saleDtoTest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
