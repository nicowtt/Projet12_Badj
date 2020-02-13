package com.esales.microservicemodel.mapper;

import com.esales.microservicemodel.dto.ArticleDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.impl.ArticleMapperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleMapperImplUnitTest {

    ArticleMapperImpl articleMapperImpl;

    /** Jeu de données **/
    ArticleDto articleDto;
    Article article;

    @Before
    public void setUp() {
        articleMapperImpl = new ArticleMapperImpl();
        articleDto = new ArticleDto();
        articleDto.setId(1);
        articleDto.setCategory("Vêtement");
        articleDto.setType("chemise");
        articleDto.setSaleNumber(1);
        articleDto.setPrice(5);
        articleDto.setDateRecord(null);
        articleDto.setValidateToSell(false);
        articleDto.setSold(false);
        articleDto.setStolen(false);
        articleDto.setReturnOwner(false);
        articleDto.setClothe(null);
        articleDto.setToy(null);
        articleDto.setBook(null);
        articleDto.setObject(null);

        User user = new User();
        user.setId(1);
        articleDto.setUser(user);

        Sale sale = new Sale();
        sale.setId(1);
        articleDto.setSale(sale);

        article = new Article();
        article.setId(1);
        article.setCategory("Vêtement");
        article.setType("chemise");
        article.setSaleNumber(1);
        article.setPrice(5);
        article.setDateRecord(null);
        article.setValidateToSell(false);
        article.setSold(false);
        article.setStolen(false);
        article.setReturnOwner(false);
        article.setClothe(null);
        article.setToy(null);
        article.setBook(null);
        article.setObject(null);

        article.setUser(user);
        article.setSale(sale);


    }

    @Test
    public void testFromArticleDtoToArticle() {
        Article article = articleMapperImpl.fromArticleDtoToArticle(articleDto);

        Assert.assertEquals("Article(id=1, category=Vêtement, type=chemise, saleNumber=1, price=5.0, " +
                "dateRecord=null, isValidateToSell=false, isSold=false, isStolen=false, isReturnOwner=false, " +
                "user=User(id=1, name=null, lastName=null, password=null, email=null, phone=null, isVoluntary=false, " +
                "isResponsible=false, address=null), sale=Sale(id=1, type=null, description=null, dateBegin=null, " +
                "dateEnd=null, address=null), clothe=null, toy=null, book=null, object=null)", article.toString());
    }

    @Test
    public void testFromArticleToArticleDto() {
        ArticleDto articleDto = articleMapperImpl.fromArticleToArticleDto(article);

        Assert.assertEquals("ArticleDto(id=1, category=Vêtement, type=chemise, saleNumber=1, " +
                "price=5.0, dateRecord=null, isValidateToSell=false, isSold=false, isStolen=false, " +
                "isReturnOwner=false, user=User(id=1, name=null, lastName=null, password=null, email=null, " +
                "phone=null, isVoluntary=false, isResponsible=false, address=null), sale=Sale(id=1, type=null, " +
                "description=null, dateBegin=null, dateEnd=null, address=null), clothe=null, toy=null, book=null, " +
                "object=null)", articleDto.toString());
    }
}
