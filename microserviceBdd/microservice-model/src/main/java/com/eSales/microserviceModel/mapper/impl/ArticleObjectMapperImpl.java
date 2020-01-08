package com.eSales.microserviceModel.mapper.impl;

import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Object;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.contract.ArticleObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleObjectMapperImpl implements ArticleObjectMapper {

    /**
     * from ArticleObjectDto To Article
     * @param articleObjectDto -> article input
     * @param userId -> user who want to create article
     * @param nextNumberOfArticlesSale -> for write during the sale
     * @return article
     */
    @Override
    public Article fromArticleObjectDtoToArticle(ArticleObjectDto articleObjectDto, int userId, int nextNumberOfArticlesSale) {
        Article objectArticle = new Article();
        User userConcerned = new User();
        Sale saleConcerned = new Sale();

        objectArticle.setCategory(articleObjectDto.getCategory());
        objectArticle.setType(articleObjectDto.getType());
        objectArticle.setSaleNumber(nextNumberOfArticlesSale); // new next article number
        objectArticle.setPrice(articleObjectDto.getPrice());
        objectArticle.setDateRecord(articleObjectDto.getRecordDate());
        objectArticle.setValidateToSell(articleObjectDto.isValidateToSell());
        objectArticle.setSold(articleObjectDto.isSold());
        objectArticle.setStolen(articleObjectDto.isStolen());
        objectArticle.setReturnOwner(articleObjectDto.isReturnOwner());
        // for sale
        saleConcerned.setId(articleObjectDto.getSaleId());
        objectArticle.setSale(saleConcerned);
        // for user
        userConcerned.setId(userId);
        objectArticle.setUser(userConcerned);

        return objectArticle;
    }

    /**
     * from ArticleObjectDto To Object
     * @param articleObjectDto -> input
     * @return object to persist on bdd
     */
    @Override
    public Object fromArticleObjectDtoToObject(ArticleObjectDto articleObjectDto) {
        Object object = new Object();

        object.setBrand(articleObjectDto.getBrand());
        object.setColor(articleObjectDto.getColor());
        object.setComment(articleObjectDto.getComment());

        return object;
    }
}
