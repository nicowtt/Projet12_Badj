package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.ArticleObjectDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Object;
import org.springframework.stereotype.Service;

@Service
public interface ArticleObjectMapper {

    Article fromArticleObjectDtoToArticle(ArticleObjectDto articleObjectDto, int userId, int nextNumberOfArticlesSale);
    Object fromArticleObjectDtoToObject(ArticleObjectDto articleObjectDto);

}
