package com.eSales.microserviceBusiness.IntegrationTests;

import com.eSales.microserviceBusiness.config.TestContextConf;
import com.eSales.microserviceBusiness.contract.AddressManager;
import com.eSales.microserviceBusiness.contract.SaleManager;
import com.eSales.microserviceBusiness.impl.SaleManagerImpl;
import com.eSales.microserviceBusiness.impl.UserManagerImpl;
import com.eSales.microserviceDao.AddressDao;
import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.impl.SaleMapperImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestContextConf.class)
public class SaleManagerImplIntegrationTest {

    private Date testDateBegin;
    private Date testDateEnd;
    private SaleDto saleDtoTest;
    private Address address;
    private Sale oldSaleTest;
    private Optional<Address> oldAddressTest;

    @Autowired
    private SaleManagerImpl saleManagerImpl;

    @Autowired
    private AddressManager addressManager;

    static final Log logger = LogFactory.getLog(SaleMapperImpl.class);

    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        address = new Address();
        address.setStreet("rue du test");
        address.setPostalCode(31200);
        address.setCity("Toulouse");

        testDateBegin = new Date();
        testDateEnd = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateBeginRecordString = "10/01/2100";
        String dateEndRecordString = "17/01/2100";
        try {
            testDateBegin = dateFormated.parse(dateBeginRecordString);
            testDateEnd = dateFormated.parse(dateEndRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        saleDtoTest = new SaleDto();
        saleDtoTest.setDateBegin(testDateBegin);
        saleDtoTest.setDateEnd(testDateEnd);
        saleDtoTest.setType("saleTest");
        saleDtoTest.setDescription("descriptionTest");
        saleDtoTest.setAddress(address);


        // check if old test is on bdd
        List<Sale> listSaleOnBddBeforeTest = saleManagerImpl.getSalesByDateBeginAfterToday();
        if (listSaleOnBddBeforeTest != null) {
            Sale saleResult = listSaleOnBddBeforeTest.stream()
                    .filter(x -> "saleTest".equals(x.getType()))
                    .findAny()
                    .orElse(null);
            logger.info(" old user test is present: " + saleResult);

            if (saleResult != null) {
                // deleting adress -> cascade for delete sale
                oldSaleTest = new Sale();

                oldSaleTest = saleManagerImpl.getSale("2100-01-09");
                oldAddressTest = addressManager.getAddressById(oldSaleTest.getAddress().getId());
                address.setId(oldAddressTest.get().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old sale test removed ");
            }
        }
    }

    @After
    public void cleanAfter() {
        // check if old test is on bdd
        List<Sale> listSaleOnBddBeforeTest = saleManagerImpl.getSalesByDateBeginAfterToday();
        if (listSaleOnBddBeforeTest != null) {
            Sale saleResult = listSaleOnBddBeforeTest.stream()
                    .filter(x -> "saleTest".equals(x.getType()))
                    .findAny()
                    .orElse(null);
            logger.info(" old user test is present: " + saleResult);

            if (saleResult != null) {
                // deleting adress -> cascade for delete sale
                oldSaleTest = new Sale();
                oldSaleTest = saleManagerImpl.getSale("2100-01-09");
                oldAddressTest = addressManager.getAddressById(oldSaleTest.getAddress().getId());
                address.setId(oldAddressTest.get().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old sale test removed ");

                // deleting address ( + user with CASCADE )
                oldUserTest = userManager.findUserByMail(userDtoTest.getEmail());
                oldAddressTest = addressManager.getAddressById(oldUserTest.getAddress().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old user test removed ");
            }
        }
    }

    @Test
    public void testGetSalesByDateBeginAfterToday() {
        // count how many sale before test
        List<Sale> listOfSaleBeforeTest = saleManagerImpl.getSalesByDateBeginAfterToday();
        long nbrOfSaleBeforeTest = listOfSaleBeforeTest.stream().count();
        // add saleTest on bdd
        saleManagerImpl.addSale(saleDtoTest);
        List<Sale> listOfSaleAfter = saleManagerImpl.getSalesByDateBeginAfterToday();
        long nbrOfSaleAfter = listOfSaleAfter.stream().count();

        Assert.assertTrue("Add sale must return number of sale + 1",
                nbrOfSaleAfter == nbrOfSaleBeforeTest + 1);

    }

}
