package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.ArticleDto;
import com.esales.microservicemodel.entity.Article;
import org.springframework.stereotype.Service;

@Service
public interface ArticleMapper {

    Article fromArticleDtoToArticle(ArticleDto articleDto);
}
