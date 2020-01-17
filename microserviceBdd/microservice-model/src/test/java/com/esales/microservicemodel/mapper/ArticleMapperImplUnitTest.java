package com.esales.microservicemodel.mapper;

import com.esales.microservicemodel.dto.ArticleDto;
import com.esales.microservicemodel.entity.Article;
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

    @Before
    public void setUp() {
        articleMapperImpl = new ArticleMapperImpl();
        articleDto = new ArticleDto();
        articleDto.setId(4);
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
        articleDto.setToy(null);
        articleDto.setBook(null);
        articleDto.setObject(null);
    }

    @Test
    public void testFromArticleDtoToArticle() {
        Article article = articleMapperImpl.fromArticleDtoToArticle(articleDto);

        Assert.assertEquals("Article(id=4, category=Vêtement, type=chemise, saleNumber=1, price=5.0, " +
                "dateRecord=null, isValidateToSell=false, isSold=false, isStolen=false, isReturnOwner=false, " +
                "user=null, sale=null, clothe=null, toy=null, book=null, object=null)", article.toString());
    }
}
