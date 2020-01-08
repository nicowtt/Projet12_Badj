package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.ArticleObjectDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Object;
import org.springframework.stereotype.Service;

@Service
public interface ArticleObjectMapper {

    Article fromArticleObjectDtoToArticle(ArticleObjectDto articleObjectDto, int userId, int nextNumberOfArticlesSale);
    Object fromArticleObjectDtoToObject(ArticleObjectDto articleObjectDto);

}
