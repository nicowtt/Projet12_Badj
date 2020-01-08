package com.eSales.microserviceModel.mapper.impl;

import com.eSales.microserviceModel.dto.ArticleBookDto;
import com.eSales.microserviceModel.entity.Article;
import com.eSales.microserviceModel.entity.Book;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.contract.ArticleBookMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleBookMapperImpl implements ArticleBookMapper {

    /**
     * from ArticleBookDto To Article
     * @param articleBookDto -> input
     * @param userId -> user who create article
     * @param nextNumberOfArticlesSale -> for write on article during the sale
     * @return article to persist on bdd
     */
    @Override
    public Article fromArticleBookDtoToArticle(ArticleBookDto articleBookDto, int userId, int nextNumberOfArticlesSale) {
        Article bookArticle = new Article();
        User userConcerned = new User();
        Sale saleConcerned = new Sale();

        bookArticle.setCategory(articleBookDto.getCategory());
        bookArticle.setType(articleBookDto.getType());
        bookArticle.setSaleNumber(nextNumberOfArticlesSale); // new next article number
        bookArticle.setPrice(articleBookDto.getPrice());
        bookArticle.setDateRecord(articleBookDto.getRecordDate());
        bookArticle.setValidateToSell(articleBookDto.isValidateToSell());
        bookArticle.setSold(articleBookDto.isSold());
        bookArticle.setStolen(articleBookDto.isStolen());
        bookArticle.setReturnOwner(articleBookDto.isReturnOwner());
        // for sale
        saleConcerned.setId(articleBookDto.getSaleId());
        bookArticle.setSale(saleConcerned);
        // for user
        userConcerned.setId(userId);
        bookArticle.setUser(userConcerned);

        return bookArticle;
    }

    /**
     * from ArticleBookDto To Book
     * @param articleBookDto -> input
     * @return book to persist on bdd
     */
    @Override
    public Book fromArticleBookDtoToBook(ArticleBookDto articleBookDto) {
        Book book = new Book();
        book.setName(articleBookDto.getName());
        book.setAuthor(articleBookDto.getAuthor());
        book.setComment(articleBookDto.getComment());

        return book;
    }
}
