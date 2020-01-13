package com.eSales.microserviceWeb;

import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.entity.Article;
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

    @Override
    @Before
    public void setUp() {
        super.setUp();

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        articleClotheDto = new ArticleClotheDto();
        Date recordDate = new Date();
        DateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormatted.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleClotheDto.setCategory("Vêtements");
        articleClotheDto.setType("pantalon");
        articleClotheDto.setSaleId(1);
        articleClotheDto.setPrice(5);
        articleClotheDto.setSize("32");
        articleClotheDto.setGender("Homme");
        articleClotheDto.setMaterial("jeans");
        articleClotheDto.setColor("blue");
        articleClotheDto.setComment("test");
        articleClotheDto.setRecordDate(recordDate);
        articleClotheDto.setValidateToSell(false);
        articleClotheDto.setSold(false);
        articleClotheDto.setStolen(false);
        articleClotheDto.setReturnOwner(false);
        articleClotheDto.setUserEmail("bruce.lee@gmail.com");
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

//    @Test
//    public void testAddClotheArticle() throws Exception {
//        String uri = "/NewClotheArticle";
//        String inputJson = super.mapToJson(articleClotheDto);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        // correct add
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//
//        // todo suppression de l'ajout en BDD
//        String uriForDelete = "/removeClotheArticle";
//        String inputJsonForDelete = super.mapToJson(articleClotheDto);
//        MvcResult mvcResultForDelete = mvc.perform(MockMvcRequestBuilders.post(uriForDelete)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJsonForDelete)).andReturn();
//
//
//        int statusForRemove = mvcResultForDelete.getResponse().getStatus();
//        assertEquals(200, statusForRemove);
//
//    }
}
