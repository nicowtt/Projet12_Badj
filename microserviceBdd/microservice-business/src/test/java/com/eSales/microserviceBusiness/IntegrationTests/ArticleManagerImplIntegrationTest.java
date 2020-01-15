package com.eSales.microserviceBusiness.IntegrationTests;

import com.eSales.microserviceBusiness.config.TestContextConf;
import com.eSales.microserviceBusiness.contract.AddressManager;
import com.eSales.microserviceBusiness.impl.ArticleManagerImpl;
import com.eSales.microserviceBusiness.impl.SaleManagerImpl;
import com.eSales.microserviceBusiness.impl.UserManagerImpl;
import com.eSales.microserviceModel.dto.*;
import com.eSales.microserviceModel.entity.Address;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestContextConf.class)
public class ArticleManagerImplIntegrationTest {

    private SaleDto saleDtoTest;
    private Address address;
    private Date testDateBegin;
    private Date testDateEnd;
    private Sale oldSaleTest;
    private Optional<Address> oldAddressTest;
    private UserDto userDtoTest;

    @Autowired
    private SaleManagerImpl saleManagerImpl;

    @Autowired
    private AddressManager addressManager;

    @Autowired
    private ArticleManagerImpl articleManagerImpl;

    @Autowired
    private UserManagerImpl userManagerImpl;

    static final Log logger = LogFactory.getLog(ArticleManagerImplIntegrationTest.class);

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

        userDtoTest = new UserDto();
        userDtoTest = new UserDto();
        userDtoTest.setName("nico");
        userDtoTest.setLastName("bod");
        userDtoTest.setPassword("pass");
        userDtoTest.setEmail("test@test.com");
        userDtoTest.setPhone("0612121212");
        userDtoTest.setStreet("rue du test");
        userDtoTest.setPostalCode(31200);
        userDtoTest.setCity("Toulouse");

        // check if old test is on bdd
        // for sale
        List<Sale> listSaleOnBddBeforeTest = saleManagerImpl.getSalesByDateBeginAfterToday();
        if (listSaleOnBddBeforeTest != null) {
            Sale saleResult = listSaleOnBddBeforeTest.stream()
                    .filter(x -> "saleTest".equals(x.getType()))
                    .findAny()
                    .orElse(null);


            if (saleResult != null) {
                logger.info(" old user test is present: " + saleResult);
                // deleting adress -> cascade for delete sale
                oldSaleTest = new Sale();

                oldSaleTest = saleManagerImpl.getSale("2100-01-10");
                oldAddressTest = addressManager.getAddressById(oldSaleTest.getAddress().getId());
                address.setId(oldAddressTest.get().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old sale test removed ");
            }
        }
        // for Article
        User userArticle = userManagerImpl.findUserByMail("test@test.com");
        if (userArticle != null) {
            List<Article> listArticleForOneUser = articleManagerImpl.getAllArticlesForOneUser(userArticle.getId());
            for (Article article: listArticleForOneUser) {
                articleManagerImpl.removeArticle(article);
            }
        }
        // for User
        User user = userManagerImpl.findUserByMail("test@test.com");
        if (user != null) {
            // remove address
            oldAddressTest = addressManager.getAddressById(user.getAddress().getId());
            address.setId(oldAddressTest.get().getId());
            oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
        }
    }

    @After
    public void cleanAfter() {
        // check if old test is on bdd
        // for sale
        List<Sale> listSaleOnBddBeforeTest = saleManagerImpl.getSalesByDateBeginAfterToday();
        if (listSaleOnBddBeforeTest != null) {
            Sale saleResult = listSaleOnBddBeforeTest.stream()
                    .filter(x -> "saleTest".equals(x.getType()))
                    .findAny()
                    .orElse(null);

            if (saleResult != null) {
                logger.info(" old user test is present: " + saleResult);
                // deleting adress -> cascade for delete sale
                oldSaleTest = new Sale();

                oldSaleTest = saleManagerImpl.getSale("2100-01-10");
                oldAddressTest = addressManager.getAddressById(oldSaleTest.getAddress().getId());
                address.setId(oldAddressTest.get().getId());
                oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
                logger.info(" old sale test removed ");
            }
        }
        // for Article
        User userArticle = userManagerImpl.findUserByMail("test@test.com");
        if (userArticle != null) {
            List<Article> listArticleForOneUser = articleManagerImpl.getAllArticlesForOneUser(userArticle.getId());
            for (Article article: listArticleForOneUser) {
                articleManagerImpl.removeArticle(article);
            }
        }
        // for User
        User user = userManagerImpl.findUserByMail("test@test.com");
        if (user != null) {
            // remove address -> cascade for user
            oldAddressTest = addressManager.getAddressById(user.getAddress().getId());
            address.setId(oldAddressTest.get().getId());
            oldAddressTest.ifPresent(address -> addressManager.removeAddress(address));
        }
    }

    @Test
    public void testFindNextArticleNumberOnOneSale() {
        Sale newSaleTest = saleManagerImpl.addSale(saleDtoTest);
        Assert.assertTrue(articleManagerImpl.findNextArticleNumberOnOneSale(newSaleTest.getId()) == 1);
    }

    @Test
    public void testAddNewBookArticle() {
        ArticleBookDto articleBookDto = new ArticleBookDto();
        articleBookDto.setCategory("Livre");
        articleBookDto.setType("poche");
        articleBookDto.setSaleId(1);
        articleBookDto.setName("Le signal");
        articleBookDto.setAuthor("Maxime Chattam");
        articleBookDto.setPrice(5);
        articleBookDto.setComment("/");
        articleBookDto.setRecordDate(testDateBegin);
        articleBookDto.setValidateToSell(false);
        articleBookDto.setSold(false);
        articleBookDto.setStolen(false);
        articleBookDto.setReturnOwner(false);
        articleBookDto.setUserEmail("test@test.com");

        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewBookArticle(articleBookDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);

    }

    @Test
    public void testAddNewObjectArticle() {
        ArticleObjectDto articleObjectDto = new ArticleObjectDto();
        articleObjectDto.setCategory("Objet de décoration");
        articleObjectDto.setType("lit");
        articleObjectDto.setSaleId(1);
        articleObjectDto.setPrice(5);
        articleObjectDto.setBrand("laCroix");
        articleObjectDto.setColor("blanc");
        articleObjectDto.setColor("/");
        articleObjectDto.setRecordDate(testDateBegin);
        articleObjectDto.setValidateToSell(false);
        articleObjectDto.setSold(false);
        articleObjectDto.setStolen(false);
        articleObjectDto.setReturnOwner(false);
        articleObjectDto.setUserEmail("test@test.com");

        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewObjectArticle(articleObjectDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);
    }

    @Test
    public void testAddNewClotheArticle() {
        ArticleClotheDto articleClotheDto = new ArticleClotheDto();
        articleClotheDto.setCategory("Vêtements");
        articleClotheDto.setType("pantalon");
        articleClotheDto.setSaleId(1);
        articleClotheDto.setPrice(5);
        articleClotheDto.setSize("32");
        articleClotheDto.setGender("Homme");
        articleClotheDto.setMaterial("jeans");
        articleClotheDto.setColor("blue");
        articleClotheDto.setComment("marque levis");
        articleClotheDto.setRecordDate(testDateBegin);
        articleClotheDto.setValidateToSell(false);
        articleClotheDto.setSold(false);
        articleClotheDto.setStolen(false);
        articleClotheDto.setReturnOwner(false);
        articleClotheDto.setUserEmail("test@test.com");

        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewClotheArticle(articleClotheDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);
    }

    @Test
    public void testAddNewToyArticle() {
        ArticleToyDto articleToyDto = new ArticleToyDto();
        articleToyDto.setBrand("bandai");
        articleToyDto.setCategory("Jouet");
        articleToyDto.setType("jouet éléctronique");
        articleToyDto.setColor("bleu");
        articleToyDto.setComment("Rayure derrière");
        articleToyDto.setPrice(5);
        articleToyDto.setRecordDate(testDateBegin);
        articleToyDto.setReturnOwner(false);
        articleToyDto.setSaleId(1);
        articleToyDto.setSold(false);
        articleToyDto.setStolen(false);
        articleToyDto.setUserEmail("test@test.com");
        articleToyDto.setValidateToSell(false);

        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewToyArticle(articleToyDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);
    }

    //todo test delete article

}
