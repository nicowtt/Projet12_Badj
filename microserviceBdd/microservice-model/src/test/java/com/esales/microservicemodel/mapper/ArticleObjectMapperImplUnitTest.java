package com.esales.microservicemodel.mapper;

import com.esales.microservicemodel.dto.ArticleObjectDto;
import com.esales.microservicemodel.entity.*;
import com.esales.microservicemodel.entity.Object;
import com.esales.microservicemodel.mapper.impl.ArticleObjectMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class ArticleObjectMapperImplUnitTest {

    ArticleObjectMapperImpl articleObjectMapperImpl;

    /** Jeu de données **/
    private ArticleObjectDto articleObjectDto;
    private Article article;
    private User user;
    private Sale sale;
    private Object object;


    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        articleObjectMapperImpl = new ArticleObjectMapperImpl();
        articleObjectDto = new ArticleObjectDto();
        Date recordDate = new Date();
        DateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormatted.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleObjectDto.setCategory("Objet de décoration");
        articleObjectDto.setType("lit");
        articleObjectDto.setSaleId(1);
        articleObjectDto.setPrice(5);
        articleObjectDto.setBrand("laCroix");
        articleObjectDto.setColor("blanc");
        articleObjectDto.setComment("/");
        articleObjectDto.setRecordDate(recordDate);
        articleObjectDto.setValidateToSell(false);
        articleObjectDto.setSold(false);
        articleObjectDto.setStolen(false);
        articleObjectDto.setReturnOwner(false);
        articleObjectDto.setUserEmail("bruce.lee@gmail.com");

        article = new Article();
        article.setCategory("Objet de décoration");
        article.setType("lit");
        article.setSaleNumber(1);
        article.setPrice(5);
        article.setDateRecord(recordDate);
        article.setValidateToSell(false);
        article.setSold(false);
        article.setStolen(false);
        article.setReturnOwner(false);

        user = new User();
        user.setId(1);
        article.setUser(user);

        sale = new Sale();
        sale.setId(1);
        article.setSale(sale);

        article.setClothe(null);
        article.setToy(null);
        article.setBook(null);

        object = new Object();
        object.setBrand("laCroix");
        object.setColor("blanc");
        object.setComment("/");
        article.setObject(object);
    }

    @Test
    public void testFromArticleObjectDtoToArticle() {
        Article article = articleObjectMapperImpl.fromArticleObjectDtoToArticle(articleObjectDto, 1,3);

        Assert.assertEquals("Article(id=0, category=Objet de décoration, type=lit, saleNumber=3, price=5.0, " +
                "dateRecord=Fri Jan 10 00:00:00 UTC 2020, isValidateToSell=false, " +
                "isSold=false, isStolen=false, isReturnOwner=false, " +
                "user=User(id=1, name=null, lastName=null, password=null, email=null, phone=null, " +
                "isVoluntary=false, isResponsible=false, address=null), sale=Sale(id=1, type=null, " +
                "description=null, dateBegin=null, dateEnd=null, address=null), clothe=null, toy=null, " +
                "book=null, object=null)", article.toString());
    }

    @Test
    public void testFromArticleObjectDtoToObject() {
        Object object = articleObjectMapperImpl.fromArticleObjectDtoToObject(articleObjectDto);

        Assert.assertEquals("Object(articleId=0, brand=laCroix, color=blanc, comment=/)", object.toString());
    }
}
