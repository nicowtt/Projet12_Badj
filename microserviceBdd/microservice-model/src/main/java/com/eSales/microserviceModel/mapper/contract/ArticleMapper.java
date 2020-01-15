package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.ArticleDto;
import com.eSales.microserviceModel.entity.Article;
import org.springframework.stereotype.Service;

@Service
public interface ArticleMapper {

    Article fromArticleDtoToArticle(ArticleDto articleDto);
}
