package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.ArticleClotheDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Clothe;
import org.springframework.stereotype.Service;

@Service
public interface ArticleClotheMapper {

    Article fromArticleClotheDtoToArticle(ArticleClotheDto articleClotheDto, int userId, int nextNumberOfArticlesSale);
    Clothe fromArticleClotheDtoToClothe(ArticleClotheDto articleClotheDto);
}
