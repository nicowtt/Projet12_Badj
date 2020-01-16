package com.esales.microservicemodel.mapper.impl;

import com.esales.microservicemodel.dto.ArticleToyDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.Toy;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.ArticleToyMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleToyMapperImpl implements ArticleToyMapper {

    /**
     * from ArticleToyDto To Article
     * @param articleToyDto -> input
     * @param userId -> user who want to create article
     * @param nextNumberOfArticlesSale -> to write during the sale
     * @return article to persist on bdd
     */
    @Override
    public Article fromArticleToyDtoToArticle(ArticleToyDto articleToyDto, int userId, int nextNumberOfArticlesSale) {
        Article toyArticle = new Article();
        User userConcerned = new User();
        Sale saleConcerned = new Sale();

        toyArticle.setCategory(articleToyDto.getCategory());
        toyArticle.setType(articleToyDto.getType());
        toyArticle.setSaleNumber(nextNumberOfArticlesSale); // new next article number
        toyArticle.setPrice(articleToyDto.getPrice());
        toyArticle.setDateRecord(articleToyDto.getRecordDate());
        toyArticle.setValidateToSell(articleToyDto.isValidateToSell());
        toyArticle.setSold(articleToyDto.isSold());
        toyArticle.setStolen(articleToyDto.isStolen());
        toyArticle.setReturnOwner(articleToyDto.isReturnOwner());
        // for sale
        saleConcerned.setId(articleToyDto.getSaleId());
        toyArticle.setSale(saleConcerned);
        // for user
        userConcerned.setId(userId);
        toyArticle.setUser(userConcerned);

        return toyArticle;
    }

    /**
     * from ArticleToyDto To Toy
     * @param articleToyDto -> input
     * @return toy to persist on bdd
     */
    @Override
    public Toy fromArticleToyDtoToToy(ArticleToyDto articleToyDto) {
        Toy toy = new Toy();
        toy.setBrand(articleToyDto.getBrand());
        toy.setColor(articleToyDto.getColor());
        toy.setComment(articleToyDto.getComment());

        return toy;
    }

}
