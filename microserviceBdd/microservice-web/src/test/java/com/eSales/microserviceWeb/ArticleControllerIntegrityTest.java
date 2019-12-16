package com.eSales.microserviceWeb;

import com.eSales.microserviceModel.entity.Article;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class ArticleControllerIntegrityTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
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
}
