package com.esales.microservicemodel.mapper;

import com.esales.microservicemodel.dto.ArticleBookDto;
import com.esales.microservicemodel.entity.*;
import com.esales.microservicemodel.mapper.impl.ArticleBookMapperImpl;
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
public class ArticleBookDtoMapperUnitTest {

    ArticleBookMapperImpl articleBookMapperImpl;

    /** Jeu de données **/
    private ArticleBookDto articleBookDto;
    private Article article;
    private User user;
    private Sale sale;
    private Book book;

    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        articleBookMapperImpl = new ArticleBookMapperImpl();
        articleBookDto = new ArticleBookDto();
        Date recordDate = new Date();
        DateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
        String dateRecordString = "10/01/2020";
        try {
            recordDate = dateFormatted.parse(dateRecordString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        article = new Article();
        article.setCategory("Livre");
        article.setType("poche");
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


        article.setToy(null);
        article.setObject(null);
        article.setClothe(null);

        book = new Book();
        book.setName("Le signal");
        book.setAuthor("Maxime Chattam");
        book.setComment("/");
        article.setBook(book);
    }

    @Test
    public void testFromArticleBookDtoToArticle() {
        Article article = articleBookMapperImpl.fromArticleBookDtoToArticle(articleBookDto, 1, 3);

        Assert.assertEquals("Article(id=0, category=Livre, type=poche, saleNumber=3, price=5.0, " +
                "dateRecord=Fri Jan 10 00:00:00 UTC 2020, isValidateToSell=false, isSold=false, isStolen=false, " +
                "isReturnOwner=false, user=User(id=1, name=null, lastName=null, password=null, email=null, phone=null, " +
                "isVoluntary=false, isResponsible=false, address=null), sale=Sale(id=1, type=null, description=null, " +
                "dateBegin=null, dateEnd=null, address=null), clothe=null, toy=null, book=null, object=null)", article.toString());
    }

    @Test
    public void testFromArticleBookDtoToBook() {
        Book book = articleBookMapperImpl.fromArticleBookDtoToBook(articleBookDto);

        Assert.assertEquals("Book(articleId=0, name=Le signal, author=Maxime Chattam, comment=/, " +
                "article=null)", book.toString());
    }



}
