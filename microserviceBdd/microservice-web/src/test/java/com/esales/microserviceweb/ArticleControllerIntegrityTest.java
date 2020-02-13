package com.esales.microserviceweb;

import com.esales.microservicemodel.dto.*;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Clothe;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class ArticleControllerIntegrityTest extends AbstractTest{

    /** Jeu de données **/
    private ArticleClotheDto articleClotheDto;
    private ArticleBookDto articleBookDto;
    private ArticleObjectDto articleObjectDto;
    private ArticleToyDto articleToyDto;
    private ArticleDto articleDto;
    private Clothe clothe;
    private User user;
    private Sale sale;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        articleClotheDto = new ArticleClotheDto();
        Date recordDate = new Date();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormated.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleClotheDto.setCategory("Vêtements");
        articleClotheDto.setType("chemise");
        articleClotheDto.setSaleId(1);
        articleClotheDto.setPrice(5);
        articleClotheDto.setSize("L");
        articleClotheDto.setGender("Homme");
        articleClotheDto.setMaterial("lin");
        articleClotheDto.setColor("blanche");
        articleClotheDto.setComment("marque levis");
        articleClotheDto.setRecordDate(recordDate);
        articleClotheDto.setValidateToSell(false);
        articleClotheDto.setSold(false);
        articleClotheDto.setStolen(false);
        articleClotheDto.setReturnOwner(false);
        articleClotheDto.setUserEmail("bruce.lee@gmail.com");



        articleBookDto = new ArticleBookDto();
        articleBookDto.setCategory("Livre");
        articleBookDto.setType("poche");
        articleBookDto.setSaleId(1);
        articleBookDto.setName("Le signal");
        articleBookDto.setAuthor("Maxime Chattam");
        articleBookDto.setPrice(5);
        articleBookDto.setComment("/");
        articleBookDto.setRecordDate(recordDate);
        articleBookDto.setValidateToSell(false);
        articleBookDto.setSold(false);
        articleBookDto.setStolen(false);
        articleBookDto.setReturnOwner(false);
        articleBookDto.setUserEmail("bruce.lee@gmail.com");



        articleObjectDto = new ArticleObjectDto();
        articleObjectDto.setCategory("Objet de décoration");
        articleObjectDto.setType("lit");
        articleObjectDto.setSaleId(1);
        articleObjectDto.setPrice(5);
        articleObjectDto.setBrand("laCroix");
        articleObjectDto.setColor("blanc");
        articleObjectDto.setColor("/");
        articleObjectDto.setRecordDate(recordDate);
        articleObjectDto.setValidateToSell(false);
        articleObjectDto.setSold(false);
        articleObjectDto.setStolen(false);
        articleObjectDto.setReturnOwner(false);
        articleObjectDto.setUserEmail("bruce.lee@gmail.com");

        articleToyDto = new ArticleToyDto();
        articleToyDto.setBrand("bandai");
        articleToyDto.setCategory("Jouet");
        articleToyDto.setType("jouet éléctronique");
        articleToyDto.setColor("bleu");
        articleToyDto.setComment("Rayure derrière");
        articleToyDto.setPrice(5);
        articleToyDto.setRecordDate(recordDate);
        articleToyDto.setReturnOwner(false);
        articleToyDto.setSaleId(1);
        articleToyDto.setSold(false);
        articleToyDto.setStolen(false);
        articleToyDto.setUserEmail("bruce.lee@gmail.com");
        articleToyDto.setValidateToSell(false);

        articleDto = new ArticleDto();
//        articleDto.setId(4); // todo bleme
        articleDto.setCategory("Vêtement");
        articleDto.setType("chemise");
        articleDto.setSaleNumber(1);
        articleDto.setPrice(5);
        articleDto.setDateRecord(null);
        articleDto.setValidateToSell(false);
        articleDto.setSold(false);
        articleDto.setStolen(false);
        articleDto.setReturnOwner(false);
        articleDto.setUser(null);
        articleDto.setSale(null);
        articleDto.setClothe(null);
        clothe = new Clothe();
        //clothe.setArticleId(0); 
        clothe.setSize("L");
        clothe.setColor("blanche");
        clothe.setGender("homme");
        clothe.setMaterial("lin");
        clothe.setComment("marque levis");
        articleDto.setClothe(clothe);

        articleDto.setToy(null);
        articleDto.setBook(null);
        articleDto.setObject(null);

        user = new User();
        user.setId(0);
        articleDto.setUser(user);

        sale = new Sale();
        sale.setId(1);
        articleDto.setSale(sale);


    }

    @Test
    public void testGetAllArticles() throws Exception{
        String uri = "/AllArticles";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Article[] articleslist = super.mapFromJson(content, Article[].class);
        assertTrue(articleslist.length > 0);
    }

    @Test
    public void testAddClotheArticleWrongAdd() throws Exception {
        String uri = "/NewClotheArticle";
        // wrong add
        articleClotheDto.setCategory(null);

        String inputJson = super.mapToJson(articleClotheDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
    }

    @Test
    public void testAddObjectArticleWrongAdd() throws Exception {
        String uri = "/NewObjectArticle";
        //wrong add
        articleObjectDto.setCategory(null);

        String inputJson = super.mapToJson(articleObjectDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
    }

    @Test
    public void testAddToyArticleWrongAdd() throws Exception {
        String uri = "/NewToyArticle";
        // wrong add
        articleToyDto.setCategory(null);

        String inputJson = super.mapToJson(articleToyDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
    }

    @Test
    public void testAddBookArticleWrongAdd() throws Exception {
        String uri = "/NewBookArticle";
        // wrong add
        articleBookDto.setCategory(null);

        String inputJson = super.mapToJson(articleBookDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
    }

    @Test
    public void testGetAllArticleForOneId() throws Exception {
        String uri = "/AllArticlesForId/jason.statam@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Article[] articleslist = super.mapFromJson(content, Article[].class);
        assertTrue(articleslist.length > 0);
    }

    @Test
    public void testGetAllArticleForOneSale() throws Exception {
        String uri = "/AllArticlesForSale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Article[] articleslist = super.mapFromJson(content, Article[].class);
        assertTrue(articleslist.length > 0);
    }

    @Test
    public void testForUpdateArticleWrongUpdate() throws Exception {
        String uri = "/UpdateArticle";
        // wrong update
        String inputJson = super.mapToJson(articleDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
    }

    @Test
    public void testGetOneArticleWithArticleId() throws  Exception {
        String uri = "/getOneArticle/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testRemoveArticle() throws Exception {
        String uri = "/RemoveArticle";
        String inputJson = super.mapToJson(articleDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testGetOneArticleWithSaleNumberAndSaleId() throws Exception {
        String uri = "/getOneArticleWithSaleNumberAndSaleId/1/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
