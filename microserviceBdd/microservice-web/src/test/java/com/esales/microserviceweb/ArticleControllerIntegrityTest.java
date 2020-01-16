package com.esales.microserviceweb;

import com.esales.microservicemodel.dto.*;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Clothe;
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
//        clothe.setArticleId(4); // todo bleme
        clothe.setSize("L");
        clothe.setColor("blanche");
        clothe.setGender("homme");
        clothe.setMaterial("lin");
        clothe.setComment("marque levis");
        articleDto.setClothe(clothe);

        articleDto.setToy(null);
        articleDto.setBook(null);
        articleDto.setObject(null);


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
    public void testAddObjectArticle() throws Exception {
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
    public void testAddBookArticle() throws Exception {
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

//    @Test
//    public void testRemoveArticleAndGetNewList() throws Exception {
//        // remove new toy
//        String uriRemove = "/RemoveArticleAndGetNewList/bruce.lee@gmail.com";
//        String inputJson = super.mapToJson(articleDto);
//        MvcResult mvcResultRemove = mvc.perform(MockMvcRequestBuilders.post(uriRemove)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResultRemove.getResponse().getStatus();
////        assertEquals(200, status);
//
//        String content = mvcResultRemove.getResponse().getContentAsString();
//        Article[] articleslist = super.mapFromJson(content, Article[].class);
//        long nbrArticlesList = Arrays.stream(articleslist).count();
//
//        assertTrue(nbrArticlesList == 0);
//    }

//    @Test
//    public void testAddToyArticle() throws Exception {
//        String uri = "/NewToyArticle";
//
//        String inputJson = super.mapToJson(articleToyDto);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//    }

//    @Test
//    public void testAddClotheArticle() throws Exception {
//        String uri = "/NewClotheArticle";
//
//        String inputJson = super.mapToJson(articleClotheDto);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//    }
}
