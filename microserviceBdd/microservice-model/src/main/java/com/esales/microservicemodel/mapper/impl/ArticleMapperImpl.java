package com.esales.microservicemodel.mapper.impl;

import com.esales.microservicemodel.dto.*;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.ArticleMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapperImpl implements ArticleMapper {


    /**
     * from ArticleDto To Article
     * @param articleDto -> input
     * @return Article
     */
    public Article fromArticleDtoToArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCategory(articleDto.getCategory());
        article.setType(articleDto.getType());
        article.setSaleNumber(articleDto.getSaleNumber());
        article.setPrice(articleDto.getPrice());
        article.setDateRecord(articleDto.getDateRecord());
        article.setValidateToSell(articleDto.isValidateToSell());
        article.setSold(articleDto.isSold());
        article.setStolen(articleDto.isStolen());
        article.setReturnOwner(articleDto.isReturnOwner());
        article.setClothe(articleDto.getClothe());
        article.setObject(articleDto.getObject());
        article.setBook(articleDto.getBook());
        article.setToy(articleDto.getToy());

        User user = new User();
        user.setId(articleDto.getUser().getId());
        article.setUser(user);

        Sale sale = new Sale();
        sale.setId(articleDto.getSale().getId());
        article.setSale(sale);

        return article;
    }
}
