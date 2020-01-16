package com.eSales.microserviceModel.mapper;

import com.eSales.microserviceModel.dto.ArticleToyDto;
import com.eSales.microserviceModel.entity.*;
import com.eSales.microserviceModel.mapper.impl.ArticleToyMapperImpl;
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
public class ArticleToyMapperUnitTest {
    private ArticleToyMapperImpl articleToyMapperImpl;

    /** Jeu de données **/
    private ArticleToyDto articleToyDto;
    private Article article;
    private User user;
    private Address address;
    private Sale sale;
    private Toy toy;

    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        articleToyMapperImpl = new ArticleToyMapperImpl();
        articleToyDto = new ArticleToyDto();
        Date recordDate = new Date();
        DateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormatted.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        article = new Article();
        article.setCategory("Jouet");
        article.setType("Jouet éléctronique");
        article.setSaleNumber(1);
        article.setPrice(5);
        article.setDateRecord(recordDate);
        article.setValidateToSell(false);
        article.setSold(false);
        article.setStolen(false);
        article.setReturnOwner(false);

        user = new User();
        address = new Address();
        user.setId(1);
        article.setUser(user);

        sale = new Sale();
        sale.setId(1);
        article.setSale(sale);

        article.setClothe(null);

        toy = new Toy();
        toy.setBrand("bandai");
        toy.setColor("bleu");
        toy.setComment("Rayure derrière");
        article.setToy(toy);

        article.setBook(null);
        article.setObject(null);
    }

    @Test
    public void testFromArticleToyDtoToArticle() {
        Article article = articleToyMapperImpl.fromArticleToyDtoToArticle(articleToyDto, 1, 3);

        Assert.assertEquals("Article(id=0, category=Jouet, type=jouet éléctronique, saleNumber=3, " +
                "price=5.0, dateRecord=Fri Jan 10 00:00:00 UTC 2020, isValidateToSell=false, isSold=false, " +
                "isStolen=false, isReturnOwner=false, user=User(id=1, name=null, lastName=null, password=null, " +
                "email=null, phone=null, isVoluntary=false, isResponsible=false, address=null), sale=Sale(id=1, " +
                "type=null, description=null, dateBegin=null, dateEnd=null, address=null), clothe=null, toy=null, " +
                "book=null, object=null)", article.toString());
    }

    @Test
    public void testFromArticleToyDtoToToy() {
        Toy toy = articleToyMapperImpl.fromArticleToyDtoToToy(articleToyDto);

        Assert.assertEquals("Toy(articleId=0, brand=bandai, color=bleu, comment=Rayure derrière, " +
                "article=null)", toy.toString());
    }
}
