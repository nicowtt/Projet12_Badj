package com.eSales.microserviceModel.mapper.impl;

import com.eSales.microserviceModel.dto.ArticleDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.mapper.contract.ArticleMapper;
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

        return article;
    }
}
