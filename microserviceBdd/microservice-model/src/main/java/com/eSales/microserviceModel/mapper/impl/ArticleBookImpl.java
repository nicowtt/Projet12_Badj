package com.eSales.microserviceModel.mapper.impl;

import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Book;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.contract.ArticleBookMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleBookImpl implements ArticleBookMapper {

    @Override
    public Article fromArticleBookDtoToArticle(ArticleBookDto articleBookDto, int userId, int nextNumberOfArticlesSale) {
        Article article = new Article();
        User userConcerned = new User();
        Sale saleConcerned = new Sale();

        article.setCategory(articleBookDto.getCategory());
        article.setType(articleBookDto.getType());
        article.setSaleNumber(nextNumberOfArticlesSale); // new next article number
        article.setPrice(articleBookDto.getPrice());
        article.setDateRecord(articleBookDto.getRecordDate());
        article.setValidateToSell(articleBookDto.isValidateToSell());
        article.setSold(articleBookDto.isSold());
        article.setStolen(articleBookDto.isStolen());
        article.setReturnOwner(articleBookDto.isReturnOwner());
        // for sale
        saleConcerned.setId(articleBookDto.getSaleNumber());
        article.setSale(saleConcerned);
        // for user
        userConcerned.setId(userId);
        article.setUser(userConcerned);

        return article;
    }

    @Override
    public Book fromArticleBookDtoToBook(ArticleBookDto articleBookDto) {
        Book book = new Book();
        book.setName(articleBookDto.getName());
        book.setAuthor(articleBookDto.getAuthor());
        book.setComment(articleBookDto.getComment());

        return book;
    }
}
