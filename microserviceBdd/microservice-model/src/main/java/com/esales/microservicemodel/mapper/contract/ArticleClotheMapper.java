package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.ArticleClotheDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Clothe;
import org.springframework.stereotype.Service;

@Service
public interface ArticleClotheMapper {

    Article fromArticleClotheDtoToArticle(ArticleClotheDto articleClotheDto, int userId, int nextNumberOfArticlesSale);
    Clothe fromArticleClotheDtoToClothe(ArticleClotheDto articleClotheDto);
}
