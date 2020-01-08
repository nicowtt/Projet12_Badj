package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface ArticleBookMapper {

    Article fromArticleBookDtoToArticle(ArticleBookDto articleBookDto, int userId, int nextNumberOfArticlesSale);
    Book fromArticleBookDtoToBook(ArticleBookDto articleBookDto);
}
