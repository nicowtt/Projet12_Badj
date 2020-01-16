package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.ArticleToyDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Toy;
import org.springframework.stereotype.Service;

@Service
public interface ArticleToyMapper {

    Article fromArticleToyDtoToArticle(ArticleToyDto articleToyDto, int userId, int nextNumberOfArticlesSale);
    Toy fromArticleToyDtoToToy(ArticleToyDto articleToyDto);
}
