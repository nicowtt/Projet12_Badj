package com.eSales.microserviceWeb;

import com.eSales.microserviceModel.entity.Sale;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class SaleControllerIntegrityTest extends AbstractTest{


    @Override
    @Before
    public void setUp() {
        super.setUp();
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
}