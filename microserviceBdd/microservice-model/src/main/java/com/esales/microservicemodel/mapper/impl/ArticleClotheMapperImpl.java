package com.esales.microservicemodel.mapper.impl;

import com.esales.microservicemodel.dto.ArticleClotheDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Clothe;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.ArticleClotheMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleClotheMapperImpl implements ArticleClotheMapper {

    /**
     * from ArticleClotheDto To Article
     * @param articleClotheDto -> input
     * @param userId -> user who want to create new article
     * @param nextNumberOfArticlesSale -> to write on article during the sale
     * @return article to persist on bdd
     */
    @Override
    public Article fromArticleClotheDtoToArticle(ArticleClotheDto articleClotheDto, int userId, int nextNumberOfArticlesSale) {
        Article clotheArticle = new Article();
        User userConcerned = new User();
        Sale saleConcerned = new Sale();

        clotheArticle.setCategory(articleClotheDto.getCategory());
        clotheArticle.setType(articleClotheDto.getType());
        clotheArticle.setSaleNumber(nextNumberOfArticlesSale); // new next article number
        clotheArticle.setPrice(articleClotheDto.getPrice());
        clotheArticle.setDateRecord(articleClotheDto.getRecordDate());
        clotheArticle.setValidateToSell(articleClotheDto.isValidateToSell());
        clotheArticle.setSold(articleClotheDto.isSold());
        clotheArticle.setStolen(articleClotheDto.isStolen());
        clotheArticle.setReturnOwner(articleClotheDto.isReturnOwner());
        // for sale
        saleConcerned.setId(articleClotheDto.getSaleId());
        clotheArticle.setSale(saleConcerned);
        // for user
        userConcerned.setId(userId);
        clotheArticle.setUser(userConcerned);

        return clotheArticle;
    }

    /**
     * from ArticleClotheDto To Clothe
     * @param articleClotheDto -> input
     * @return clothe to persist on bdd
     */
    @Override
    public Clothe fromArticleClotheDtoToClothe(ArticleClotheDto articleClotheDto) {
        Clothe clothe = new Clothe();

        clothe.setMaterial(articleClotheDto.getMaterial());
        clothe.setColor(articleClotheDto.getColor());
        clothe.setGender(articleClotheDto.getGender());
        clothe.setSize(articleClotheDto.getSize());
        clothe.setComment(articleClotheDto.getComment());

        return clothe;
    }
}
