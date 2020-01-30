package com.esales.microservicebusiness.Integrationtests;

import com.esales.microservicebusiness.config.TestContextConf;
import com.esales.microservicebusiness.contract.AddressManager;
import com.esales.microservicebusiness.impl.ArticleManagerImpl;
import com.esales.microservicebusiness.impl.SaleManagerImpl;
import com.esales.microservicebusiness.impl.UserManagerImpl;
import com.esales.microservicemodel.dto.*;
import com.esales.microservicemodel.entity.*;
import com.esales.microservicemodel.entity.Object;
import com.esales.microservicemodel.mapper.contract.ArticleMapper;
import com.esales.microservicemodel.mapper.contract.UserMapper;
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
    private Address addressDtoTest;
    private ArticleBookDto articleBookDto;
    private ArticleObjectDto articleObjectDto;
    private ArticleClotheDto articleClotheDto;
    private ArticleToyDto articleToyDto;
    private ArticleDto articleDto;
    private User userTest;
    private Book bookTest;
    private Clothe clotheTest;
    private Toy toyTest;
    private Object objectTest;

    @Autowired
    private SaleManagerImpl saleManagerImpl;

    @Autowired
    private AddressManager addressManager;

    @Autowired
    private ArticleManagerImpl articleManagerImpl;

    @Autowired
    private UserManagerImpl userManagerImpl;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

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
        addressDtoTest = new Address();
        userDtoTest.setName("nico");
        userDtoTest.setLastName("bod");
        userDtoTest.setPassword("pass");
        userDtoTest.setEmail("test@test.com");
        userDtoTest.setPhone("0612121212");
        addressDtoTest.setStreet("rue du test");
        addressDtoTest.setPostalCode(31200);
        addressDtoTest.setCity("Toulouse");
        userDtoTest.setAddress(addressDtoTest);

        articleBookDto = new ArticleBookDto();
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

        articleObjectDto = new ArticleObjectDto();
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

        articleClotheDto = new ArticleClotheDto();
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

        articleToyDto = new ArticleToyDto();
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

        articleDto = new ArticleDto();
        userTest = userMapper.fromDtoToUserWithoutAddress(userDtoTest);
        userTest.setAddress(address);
        articleDto.setUser(userTest);
        articleDto.setCategory("vêtement");
        articleDto.setType("jeans");
        articleDto.setSaleNumber(1);
        articleDto.setPrice(10);
        articleDto.setDateRecord(null);
        articleDto.setValidateToSell(false);
        articleDto.setStolen(false);
        articleDto.setReturnOwner(false);

        bookTest = new Book();
        bookTest.setName("Le signal");
        bookTest.setAuthor("Maxime Chattam");
        bookTest.setComment("erraflures sur tranche");

        clotheTest = new Clothe();
        clotheTest.setSize("32");
        clotheTest.setGender("homme");
        clotheTest.setMaterial("jean");
        clotheTest.setColor("blue");
        clotheTest.setComment(null);

        objectTest= new Object();
        objectTest.setBrand("Seiko");
        objectTest.setColor("noir");
        objectTest.setComment(null);

        toyTest = new Toy();
        toyTest.setBrand("sony");
        toyTest.setBrand("rouge");
        toyTest.setComment(null);

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
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewBookArticle(articleBookDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);

    }

    @Test
    public void testAddNewObjectArticle() {
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewObjectArticle(articleObjectDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);
    }

    @Test
    public void testAddNewClotheArticle() {
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewClotheArticle(articleClotheDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);
    }

    @Test
    public void testAddNewToyArticle() {
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");

        articleManagerImpl.addNewToyArticle(articleToyDto, user.getId());

        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        long nbrOfArticle = listArticlesForUserTest.stream().count();

        Assert.assertTrue(nbrOfArticle == 1);
    }

    @Test
    public void testGetAllArticlesForOneSale() {
        List<Article> articleList = articleManagerImpl.getAllArticlesForOneSale(1);
        double countNbrOfArticle = articleList.stream().count();
        Assert.assertTrue(countNbrOfArticle > 0);
    }

    @Test
    public void testUpdateClotheArticle() {
        // add new clothe
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");
        articleManagerImpl.addNewClotheArticle(articleClotheDto, user.getId());
        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        // update clothe article
        Article clotheArticleToUpdate = listArticlesForUserTest.get(0);
        clotheArticleToUpdate.getClothe().setColor("red");
        ArticleDto articleDtoToUpdate = articleMapper.fromArticleToArticleDto(clotheArticleToUpdate);
        articleManagerImpl.updateArticle(articleDtoToUpdate);
        // check if article is updated
        List<Article> newListArticles = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        Article updatedClotheArticle = newListArticles.get(0);

        Assert.assertTrue(updatedClotheArticle.getClothe().getColor().equals("red"));
    }

    @Test
    public void testUpdateBookArticle() {
        // add new Book
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");
        articleManagerImpl.addNewBookArticle(articleBookDto, user.getId());
        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        // update book article
        Article bookArticleToUpdate = listArticlesForUserTest.get(0);
        bookArticleToUpdate.getBook().setAuthor("Maxime");
        ArticleDto articleDtoToUpdate = articleMapper.fromArticleToArticleDto(bookArticleToUpdate);
        articleManagerImpl.updateArticle(articleDtoToUpdate);
        // check if article is updated
        List<Article> newListArticles = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        Article updatedBookArticle = newListArticles.get(0);

        Assert.assertTrue(updatedBookArticle.getBook().getAuthor().equals("Maxime"));
    }

    @Test
    public void testUpdateObjectArticle() {
        // add new Object
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");
        articleManagerImpl.addNewObjectArticle(articleObjectDto, user.getId());
        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        // update Object article
        Article objectArticleToUpdate = listArticlesForUserTest.get(0);
        objectArticleToUpdate.getObject().setColor("blue");
        ArticleDto articleDtoToUpdate = articleMapper.fromArticleToArticleDto(objectArticleToUpdate);
        articleManagerImpl.updateArticle(articleDtoToUpdate);
        // check if article is updated
        List<Article> newListArticles = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        Article updatedObjectArticle = newListArticles.get(0);

        Assert.assertTrue(updatedObjectArticle.getObject().getColor().equals("blue"));
    }

    @Test
    public void testUpdateToyArticle() {
        // add new Toy
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");
        articleManagerImpl.addNewToyArticle(articleToyDto, user.getId());
        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        // update toy article
        Article toyArticleToUpdate = listArticlesForUserTest.get(0);
        toyArticleToUpdate.getToy().setColor("rouge");
        ArticleDto articleDtoToUpdate = articleMapper.fromArticleToArticleDto(toyArticleToUpdate);
        articleManagerImpl.updateArticle(articleDtoToUpdate);
        // check if article is updated
        List<Article> newListArticles = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        Article updatedToyArticle = newListArticles.get(0);

        Assert.assertTrue(updatedToyArticle.getToy().getColor().equals("rouge"));
    }

    @Test
    public void testGetOneArticleWithSaleNumberAndSaleId() {
        // add new Toy
        userManagerImpl.addUser(userDtoTest);
        User user = userManagerImpl.findUserByMail("test@test.com");
        articleManagerImpl.addNewToyArticle(articleToyDto, user.getId());
        List<Article> listArticlesForUserTest = articleManagerImpl.getAllArticlesForOneUser(user.getId());
        int articleSaleId = listArticlesForUserTest.get(0).getSale().getId();
        int articleSaleNumber = listArticlesForUserTest.get(0).getSaleNumber();
        Optional<Article> articleForTest = articleManagerImpl.getOneArticleWithSaleNumberAndSaleId(articleSaleNumber,articleSaleId);

        Assert.assertTrue(articleForTest.isPresent());

    }


}
