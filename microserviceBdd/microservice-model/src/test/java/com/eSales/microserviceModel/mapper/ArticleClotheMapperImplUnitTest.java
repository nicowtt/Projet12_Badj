package com.eSales.microserviceModel.mapper;

import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.entity.*;
import com.eSales.microserviceModel.mapper.impl.ArticleClotheMapperImpl;
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
public class ArticleClotheMapperImplUnitTest {

    ArticleClotheMapperImpl articleClotheMapperImpl;

    /** Jeu de données **/
    private ArticleClotheDto articleClotheDto;
    private Article article;
    private User user;
    private Sale sale;
    private Clothe clothe;

    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        articleClotheMapperImpl = new ArticleClotheMapperImpl();
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
        articleClotheDto.setColor("bleu");
        articleClotheDto.setComment("marque levis");
        articleClotheDto.setRecordDate(recordDate);
        articleClotheDto.setValidateToSell(false);
        articleClotheDto.setSold(false);
        articleClotheDto.setStolen(false);
        articleClotheDto.setReturnOwner(false);
        articleClotheDto.setUserEmail("bruce.lee@gmail.com");

        article = new Article();
        article.setCategory("Vêtements");
        article.setType("pantalon");
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
        article.setBook(null);
        article.setObject(null);

        clothe = new Clothe();
        clothe.setMaterial("jeans");
        clothe.setSize("32");
        clothe.setGender("Homme");
        clothe.setComment("marque levis");
        clothe.setColor("bleu");
        article.setClothe(clothe);
    }

    @Test
    public void testFromArticleClotheDtoToArticle() {
        Article article = articleClotheMapperImpl.fromArticleClotheDtoToArticle(articleClotheDto, 1, 3);

        Assert.assertEquals("Article{id=0, category='Vêtements', type='pantalon', saleNumber=3, price=5.0, " +
                "dateRecord=Fri Jan 10 00:00:00 UTC 2020, isValidateToSell=false, isSold=false, isStolen=false, " +
                "isReturnOwner=false, user=User{id=1, name='null', lastName='null', password='null', email='null', " +
                "phone='null', isVoluntary=false, isResponsible=false, address=null}, sale=Sale{id=1, type='null', " +
                "description='null', dateBegin=null, dateEnd=null, address=null}, clothe=null, toy=null, book=null, " +
                "object=null}", article.toString());
    }

    @Test
    public void testFromArticleClotheDtoToClothe() {
        Clothe clothe = articleClotheMapperImpl.fromArticleClotheDtoToClothe(articleClotheDto);

        Assert.assertEquals("Clothe{articleId=0, size='32', gender='Homme', material='jeans', color='bleu', " +
                "comment='marque levis', article=null}", clothe.toString());
    }
}
