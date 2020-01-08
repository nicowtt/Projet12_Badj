package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.ArticleToyDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Toy;
import org.springframework.stereotype.Service;

@Service
public interface ArticleToyMapper {

    Article fromArticleToyDtoToArticle(ArticleToyDto articleToyDto, int userId, int nextNumberOfArticlesSale);
    Toy fromArticleToyDtoToToy(ArticleToyDto articleToyDto);
}
