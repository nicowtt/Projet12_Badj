package com.esales.microservicemodel.mapper.impl;

import com.esales.microservicemodel.dto.*;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.ArticleMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    private User userFromArticleDto;
    private Sale saleFromArticleDto;


    /**
     * from ArticleDto To Article
     * @param articleDto -> input
     * @return Article
     */
    @Override
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

        userFromArticleDto = new User();
        userFromArticleDto.setId(articleDto.getUser().getId());
        article.setUser(userFromArticleDto);

        saleFromArticleDto = new Sale();
        saleFromArticleDto.setId(articleDto.getSale().getId());
        article.setSale(saleFromArticleDto);

        return article;
    }

    @Override
    public ArticleDto fromArticleToArticleDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setCategory(article.getCategory());
        articleDto.setType(article.getType());
        articleDto.setSaleNumber(article.getSaleNumber());
        articleDto.setPrice(article.getPrice());
        articleDto.setDateRecord(article.getDateRecord());
        articleDto.setValidateToSell(article.isValidateToSell());
        articleDto.setSold(article.isSold());
        articleDto.setStolen(article.isStolen());
        articleDto.setReturnOwner(article.isReturnOwner());
        articleDto.setClothe(article.getClothe());
        articleDto.setObject(article.getObject());
        articleDto.setBook(article.getBook());
        articleDto.setToy(article.getToy());

        User user = new User();
        user.setId(article.getUser().getId());
        articleDto.setUser(user);

        Sale sale = new Sale();
        sale.setId(article.getSale().getId());
        articleDto.setSale(sale);

        return articleDto;
    }
}
