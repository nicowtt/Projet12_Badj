package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.ArticleBookDto;
import com.esales.microservicemodel.entity.Article;
import com.esales.microservicemodel.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface ArticleBookMapper {

    Article fromArticleBookDtoToArticle(ArticleBookDto articleBookDto, int userId, int nextNumberOfArticlesSale);
    Book fromArticleBookDtoToBook(ArticleBookDto articleBookDto);
}
